package com.bcopstein;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/estoque/**").uri("lb://estoque"))
                .route(p -> p.path("/backend-vendas/**").uri("lb://backend-vendas"))
                .route(p -> p.path("/nota-fiscal/**").uri("lb://nota-fiscal")).build();
    }
}