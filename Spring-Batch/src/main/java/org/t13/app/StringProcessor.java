package org.t13.app;

import org.springframework.batch.infrastructure.item.ItemProcessor;

public class StringProcessor implements ItemProcessor<String, String> {
    @Override
    public String process(String item) {
        return item.toUpperCase(); // Transform text to uppercase
    }
}