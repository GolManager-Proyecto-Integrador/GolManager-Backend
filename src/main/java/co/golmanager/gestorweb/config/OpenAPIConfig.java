package co.golmanager.gestorweb.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration

@OpenAPIDefinition(
        info = @Info(
                title = "GolManager API",
                version = "0.1",
                description = "API for managing soccer tournaments, teams, players, and referees."

        ),
        servers = @Server(
                url = "http://localhost:8085",
                description = "Local server"
        )
)

@SecurityScheme(
        name = "Bearer Authentication",
        type = io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenAPIConfig {
}
