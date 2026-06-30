package org.t13.app;

import dev.langchain4j.service.SystemMessage;

public interface Assistant {

    @SystemMessage("You are a helpful, professional, and concise software engineering assistant.")
    String chat(String userMessage);
}