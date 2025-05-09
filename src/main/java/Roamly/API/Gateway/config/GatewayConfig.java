package Roamly.API.Gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.Properties;

@Configuration
public class GatewayConfig {

    @Value("${INTERNAL_TOKEN}")
    private String token;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){

        return builder.routes()

                .route(r -> r.path("/api/location/**")
                        .filters(f -> f
                                .addRequestHeader("X-Internal-Token", token)
                                .rewritePath("/api/location/(?<segment>.*)", "/location/${segment}"))
                        .uri("lb://LOCATION-AND-POI-SERVICE"))

                .route(r -> r.path("/api/poi/**")
                        .filters(f -> f
                                .addRequestHeader("X-Internal-Token", token)
                                .rewritePath("/api/poi/(?<segment>.*)", "/poi/${segment}"))
                        .uri("lb://LOCATION-AND-POI-SERVICE"))

                .route(r -> r.path("/api/group/**")
                        .filters(f -> f
                                .addRequestHeader("X-Internal-Token", token)
                                .rewritePath("/api/group/(?<segment>.*)", "/group/${segment}"))
                                .uri("lb://TRAVEL-GROUP-SERVICE"))

                .build();
    }
}
