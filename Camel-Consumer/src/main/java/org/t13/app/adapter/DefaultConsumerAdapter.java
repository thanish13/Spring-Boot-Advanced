package org.t13.app.adapter;

import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.t13.app.config.CamelIntegrationProperties;
import org.t13.app.config.RouteDefinition;
import org.t13.app.model.RouteResult;
import org.t13.app.processor.ProcessorResolver;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class DefaultConsumerAdapter implements ConsumerAdapter{
    private final ProcessorResolver processorResolver;
    private final RouteDefinition routeDefinition;

    @Override
    public String getRouteId() {
        return routeDefinition.getId();
    }

    @Override
    public void consume(Exchange exchange) {
        String routeId = getRouteId();
        String payload = exchange.getIn().getBody(String.class);
        Map<String, Object> headers = new HashMap<>(exchange.getIn().getHeaders());

        RouteResult result = processorResolver.resolve(routeId).process(routeId, payload, headers);
    }

    public void applySuccess(Exchange exchange, RouteResult result){
        //publish logic
    }
}
