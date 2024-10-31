package com.mydomain.apigatewayservice.config;


import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static com.mydomain.apigatewayservice.config.ServiceUrls.*;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return  builder.routes()
                .route(p -> p.path(EXCHANGE_ROUTE+ALL)
                        .uri(LB+EXCHANGE_SERVICE))
                .route(p -> p.path(CONVERSION_ROUTE+ALL)
                        .uri(LB+CONVERSION_SERVICE))
                .route(p -> p.path(CONVERSION_ROUTE+ALL)
                        .uri(LB+CONVERSION_SERVICE))
                .route(p -> p.path(CONVERSION_ROUTE_NEW+ALL)
                        .filters(f -> f.rewritePath(
                                CONVERSION_ROUTE_NEW+"/(?<segment>.*)",
                                CONVERSION_ROUTE_FEIGN+"/${segment}"))
                        .uri(LB+CONVERSION_SERVICE))
                .build();
    }
}
