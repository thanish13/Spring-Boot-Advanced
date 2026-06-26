package org.t13.app.processor;

import org.t13.app.model.RouteResult;

import java.util.Map;

public interface Processor {

    RouteResult process(String routeId, String payload, Map<String,Object> headers);
}
