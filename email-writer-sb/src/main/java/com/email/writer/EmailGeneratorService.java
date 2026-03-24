package com.email.writer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
public class EmailGeneratorService {
public  final WebClient webClient;
    @Value("${gemini.api.url}")
    private  String geminiApiURL;
    @Value("${gemini.api.key}")
    private String geminiAPIkey;

    public EmailGeneratorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String generateEmailReply(EmailRequest emailRequest){
        //Build a prompt
        String prompt=buildprompt(emailRequest);

        //Craft a request
        Map<String,Object> requestbody= Map.of(
                "contents",new Object[]{
                        Map.of(
                                "parts", new Object[]{
                                        Map.of(
                                                "text", prompt)

                                })
                    }
        );
        //Do request and get response
        String response=webClient.post().
                uri(geminiApiURL+geminiAPIkey)
                .header("Content-Type","application/json")
                .bodyValue(requestbody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        //Return response
        return extractResponse(response);

    }

    private String extractResponse(String response) {
        try{
            ObjectMapper mapper=new ObjectMapper();
            JsonNode rootNode=mapper.readTree(response);
            return rootNode.path("candidates").get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();
        }catch (Exception e){
            return  "Error Processing request"+e.getMessage();
        }
    }

    private String buildprompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate a professional email reply for the following email content. Please don't generate a subject line. ");

        // Changed gettone() to getTone()
        if (emailRequest.getTone() != null && !emailRequest.getTone().isEmpty()) {
            // Added spaces so the words don't stick together
            prompt.append("Use a ").append(emailRequest.getTone()).append(" tone. ");
        }

        prompt.append("\nOriginal email:\n").append(emailRequest.getEmailContent());

        return prompt.toString();
    }
}
