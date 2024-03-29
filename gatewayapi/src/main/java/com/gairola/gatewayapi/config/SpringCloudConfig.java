package com.gairola.gatewayapi.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

        @Bean
        public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
                return builder.routes()
                                .route(r -> r.path("/main/**")
                                                .uri("http://localhost:8889/"))
                                .route(r -> r.path("/service/**")
                                                .uri("http://localhost:8082/"))
                                .route(r -> r.path("/employee/**")
                                                .uri("http://localhost:8080/"))

                                .build();
        }

}
