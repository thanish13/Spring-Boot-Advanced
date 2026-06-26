package org.t13.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "camel")
public class CamelIntegrationProperties {

    List<RouteDefinition> routes = new ArrayList<>();
}

