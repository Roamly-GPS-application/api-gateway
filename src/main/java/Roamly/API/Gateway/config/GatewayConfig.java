package Roamly.API.Gateway.config;

import Roamly.API.Gateway.ApiGatewayApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.parameters.P;

import java.io.InputStream;
import java.util.Properties;

@Configuration
public class GatewayConfig {

    private final static Logger logger = LoggerFactory.getLogger(ApiGatewayApplication.class);
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){

        Properties prop = new Properties();
        String internalToken = null;

       try (InputStream input = getClass().getClassLoader().getResourceAsStream("eureka.properties")){

           if(input == null){
               System.out.println("Couldn't find properties file");
           }

           prop.load(input);

           internalToken = prop.getProperty("INTERNAL_TOKEN");

       }catch(Exception ex){
           System.out.print(ex);
       }

       String token = internalToken;


        logger.debug("Internal token {}", token);


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

                .build();
    }
}
