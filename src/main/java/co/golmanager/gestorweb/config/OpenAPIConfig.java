package co.golmanager.gestorweb.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration

@OpenAPIDefinition(
        info = @Info(
                title = "GolManager API",
                version = "0.2-SNAPSHOT",
                description = "API for managing soccer tournaments, teams, players, and referees.",
                contact = @Contact(
                        name = "Mateo Vargas Tirado - Luis Carlos Vanegas Zapata",
                        email = "mateo.vargast@udea.edu.co - lcarlos.vanegas@udea.edu.co"
                ),
                license = @License(
                        name = " AGPL-3.0 license"
                )

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
