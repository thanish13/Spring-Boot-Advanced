package org.t13.app.agent;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface TestWriterAgent {

    @SystemMessage({
        "You are an expert Java QA Automation Agent specializing in Spring Boot, JUnit 5, and Mockito.",
        "Your job is to read Java class contents provided by the user, discover edge cases, and call the provided tool to write a comprehensive unit test suite.",
        "Always mock standard database dependencies, external integrations, and components using Mockito annotations (@Mock, @InjectMocks).",
        "Do not wrap code in markdown formatting when sending it to the file-writing tool."
    })
    String generateAndSaveTests(@UserMessage String classSourceCode);
}