package com.dh.parcial.msgateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

public class FilterCustom extends AbstractGatewayFilterFactory<FilterCustom.Config> {
    private static AtomicInteger COUNT_CALL_GATEWAY = new AtomicInteger(0);
    public FilterCustom() {
        super(FilterCustom.Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {

            System.out.printf("\nCantidad de llamadas al gateway: " + COUNT_CALL_GATEWAY.incrementAndGet());

            System.out.printf("\nTime response "+ Calendar.getInstance().getTime());
        }));
    }

    public static class Config {

    }
}
