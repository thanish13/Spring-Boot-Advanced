package org.t13.app.adapter;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.t13.app.config.CamelIntegrationProperties;
import org.t13.app.config.RouteDefinition;
import org.t13.app.processor.ProcessorResolver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class ConsumerAdapterRegistry {

    private final CamelIntegrationProperties properties;
    private final ProcessorResolver resolver;

    private final Map<String, ConsumerAdapter> adapterMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void registerAdapters(){
        List<RouteDefinition> routes = properties.getRoutes();

        routes.forEach(
                this::registerAdapter
        );
    }

    private void registerAdapter(RouteDefinition routeDefinition){

        adapterMap.put(routeDefinition.getId(), new DefaultConsumerAdapter(resolver, routeDefinition));
    }

    public ConsumerAdapter resolve(String routeId){
        return adapterMap.get(routeId);
    }
}
