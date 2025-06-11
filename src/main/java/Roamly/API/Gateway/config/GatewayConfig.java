package Roamly.API.Gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Value("${INTERNAL_TOKEN}")
    private String token;

    private final String tokenHeaderName = "X-Internal-Token";

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){

        return builder.routes()

                .route(r -> r.path("/api/location/**")
                        .filters(f -> f
                                .addRequestHeader(tokenHeaderName, token)
                                .rewritePath("/api/location/(?<segment>.*)", "/location/${segment}"))
                        .uri("http://location-and-poi-service.roamly-backend.svc.cluster.local"))

                .route(r -> r.path("/api/poi/**")
                        .filters(f -> f
                                .addRequestHeader(tokenHeaderName, token)
                                .rewritePath("/api/poi/(?<segment>.*)", "/poi/${segment}"))
                        .uri("http://location-and-poi-service.roamly-backend.svc.cluster.local:8001"))

                .route(r -> r.path("/api/group/**")
                        .filters(f -> f
                                .addRequestHeader(tokenHeaderName, token)
                                .rewritePath("/api/group/(?<segment>.*)", "/group/${segment}"))
                                .uri("http://travel-group-service.roamly-backend.svc.cluster.local:8002"))

                .build();
    }
}
