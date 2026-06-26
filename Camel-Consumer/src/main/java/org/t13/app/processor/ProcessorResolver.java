package org.t13.app.processor;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class ProcessorResolver {

    private final Map<String, Processor> processorMap;

    public Processor resolve(String routeId){
        return processorMap.get(routeId);
    }

}

