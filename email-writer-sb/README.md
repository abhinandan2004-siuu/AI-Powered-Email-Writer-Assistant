# Smart AI-Powered Email Writer Assistant 🚀

A full-stack AI application and custom Chrome Extension that seamlessly integrates with Gmail to automate the drafting of professional, context-aware email replies. 

This project bridges a custom browser extension with a robust Java Spring Boot backend, leveraging the **Gemini API** to process unstructured email thread data and instantly generate high-quality responses natively within the Gmail UI.

## 🛠️ Tech Stack & Architecture

This project is divided into three core components:

* **Backend (REST API):** Java, Spring Boot, Spring Web
* **AI Integration:** Google Gemini API 
* **Frontend (Browser Injection):** JavaScript, Chrome Extensions API (Manifest V3), HTML/CSS
* **Web Dashboard (Optional UI):** React.js, Vite

## ✨ Key Features

* **Native Gmail Integration:** The custom Chrome Extension dynamically injects an interactive "Generate AI Reply" interface directly into the Gmail DOM, ensuring a seamless user experience.
* **Context-Aware Generation:** Extracts the text of incoming email threads from the browser and securely transmits it to the backend for AI processing.
* **Robust Backend Architecture:** A Spring Boot REST API handles request serialization, cross-origin resource sharing (CORS), and secure communication with the Gemini API endpoints.
* **Instant DOM Manipulation:** Asynchronously retrieves the AI-generated response and instantly populates the user's Gmail draft window.

## ⚙️ How It Works (Data Flow)

1.  **Trigger:** The user opens an email in Gmail and clicks the injected "Generate Reply" button.
2.  **Extraction:** The Chrome Extension runs a content script to scrape the visible email text from the DOM.
3.  **API Request:** A secure `POST` request is sent from the browser to the Spring Boot backend.
4.  **AI Processing:** The backend structures the context and prompts the Gemini API to generate a professional reply.
5.  **Injection:** The response is returned to the extension, which programmatically inserts the text directly into the Gmail compose window.

## 🚀 Setup & Installation

### 1. Backend Setup (Spring Boot)
1. Navigate to the `email-writer-sb` directory.
2. Ensure you have Java 21 installed.
3. Add your Gemini API key to your `application.properties` file:
   `gemini.api.key=YOUR_API_KEY_HERE`
4. Run the Spring Boot application (default port `8080`).

### 2. Chrome Extension Setup
1. Open Google Chrome and navigate to `chrome://extensions/`.
2. Enable **Developer Mode** in the top right corner.
3. Click **Load unpacked** in the top left corner.
4. Select the `email-writer-extension` folder from this repository.
5. Open Gmail and test the integration!

*(Note: If testing on a different machine, the extension's API fetch URL must be updated from `localhost:8080` to your hosted server URL or an active Ngrok tunnel).*