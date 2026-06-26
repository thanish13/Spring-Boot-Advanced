package org.t13.app.adapter;

import org.apache.camel.Exchange;

public interface ConsumerAdapter {

    String getRouteId();

    void consume(Exchange exchange);
}
