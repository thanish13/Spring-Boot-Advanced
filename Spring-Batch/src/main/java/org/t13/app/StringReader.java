package org.t13.app;

import org.springframework.batch.infrastructure.item.ItemReader;

public class StringReader implements ItemReader<String> {
    private final String[] data = {"Spring", "Batch", "Example"};
    private int index = 0;

    @Override
    public String read() {
        return index < data.length ? data[index++] : null;
    }
}