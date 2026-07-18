package org.t13.app;

import dev.langchain4j.model.chat.ChatModel;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {
    private final ChatModel model;
    public GeminiService(ChatModel model) { this.model = model; }
    public String chat(String prompt) {
        return model.chat(prompt);
    }
}
