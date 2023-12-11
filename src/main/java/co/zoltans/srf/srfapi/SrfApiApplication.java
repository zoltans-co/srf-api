package co.zoltans.srf.srfapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @io.swagger.v3.oas.annotations.info.Info(
				title = "SRF API",
				version = "1.0",
				description = "Demo API Documentation"
		),
		servers = @io.swagger.v3.oas.annotations.servers.Server(
				description = "SRF Demo API",
				url = "/"
		)
)
@SpringBootApplication
public class SrfApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrfApiApplication.class, args);
	}

}
