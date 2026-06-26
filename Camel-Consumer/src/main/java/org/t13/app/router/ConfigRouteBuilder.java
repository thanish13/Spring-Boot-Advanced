package org.t13.app.router;

import org.apache.camel.builder.RouteBuilder;
import org.t13.app.adapter.ConsumerAdapter;
import org.t13.app.adapter.ConsumerAdapterRegistry;
import org.t13.app.config.CamelIntegrationProperties;
import org.t13.app.config.RouteDefinition;

public class ConfigRouteBuilder extends RouteBuilder {

    private final CamelIntegrationProperties properties;
    private final ConsumerAdapterRegistry adapterRegistry;

    public ConfigRouteBuilder(CamelIntegrationProperties properties, ConsumerAdapterRegistry adapterRegistry) {
        this.properties = properties;
        this.adapterRegistry = adapterRegistry;
    }

    @Override
    public void configure() throws Exception {
        properties.getRoutes().forEach(
                this::registerCamelRoute
        );
    }

    private void registerCamelRoute(RouteDefinition routeDefinition){
        ConsumerAdapter adapter = adapterRegistry.resolve(routeDefinition.getId());

        from(routeDefinition.getInbound())
                .id(routeDefinition.getId())
                .process(adapter::consume)
                .to(routeDefinition.getOutbound());
    }
}
