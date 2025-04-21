package Roamly.API.Gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/api/poi/**")
                        .filters(f -> f.rewritePath("/api/poi/(?<segment>.*)", "/poi/${segment}"))
                        .uri("http://localhost:8001"))

                .route(r -> r.path("/api/location/**")
                        .filters(f -> f.rewritePath("/api/location/(?<segment>.*)", "/location/${segment}"))
                        .uri("http://localhost:8001"))

                .build();
    }
}
