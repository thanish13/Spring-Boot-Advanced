package org.t13.app;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

public class Main {
    public static void main(String[] args) {
        // 1. Instantiate the language model using your OpenAI API key
        String apiKey = System.getenv("OPENAI_API_KEY");

        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gpt-4o") // Specify your preferred model
                .temperature(0.7)
                .build();

        // 2. Map the model to your custom AI Service interface
        Assistant assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
                .build();

        // 3. Interact with the LLM using standard Java code
        String response = assistant.chat("Explain the difference between an Interface and an Abstract Class in Java.");

        System.out.println("AI Response:\n" + response);
    }
}
