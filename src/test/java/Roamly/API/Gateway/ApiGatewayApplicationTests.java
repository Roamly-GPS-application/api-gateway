package Roamly.API.Gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import reactor.core.publisher.Mono;

@SpringBootTest
class ApiGatewayApplicationTests {

	@Test
	void contextLoads() {

	}

	@TestConfiguration
	static class JwtDecoderStub {
		@Bean
		ReactiveJwtDecoder jwtDecoder() {
			return token -> Mono.empty();
		}
	}


}
