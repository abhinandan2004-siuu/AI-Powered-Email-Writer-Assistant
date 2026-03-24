package com.email.writer;

import lombok.Data;

import java.util.Map;

@Data
public class EmailRequest {
    private  String emailContent;
    private  String tone;

}
