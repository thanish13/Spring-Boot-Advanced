package org.t13.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.t13.app.agent.TestWriterAgent;

@RestController
@RequestMapping("/api/ai")
public class TestAutomationController {

    private final TestWriterAgent testWriterAgent;

    public TestAutomationController(TestWriterAgent testWriterAgent) {
        this.testWriterAgent = testWriterAgent;
    }

    @PostMapping("/generate-tests")
    public ResponseEntity<String> generateTests(@RequestBody String sourceCode) {
        // The agent processes code, analyzes dependencies, and dynamically calls the local tool file creator
        String resultMessage = testWriterAgent.generateAndSaveTests(sourceCode);
        return ResponseEntity.ok(resultMessage);
    }
}