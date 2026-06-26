package org.t13.app.model;

import java.util.Map;

public record RouteResult(String payload, Map<String, Object> headers) {

    public static RouteResult passThrough(String payload, Map<String, Object> headers) {
        return new RouteResult(payload, headers);
    }
}
