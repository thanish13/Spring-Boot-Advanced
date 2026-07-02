package org.t13.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeminiController {
    private final GeminiService service;
    public GeminiController(GeminiService service) { this.service = service; }
    @GetMapping("/chat")
    public String chat(@RequestParam String prompt) { return service.chat(prompt); }
}