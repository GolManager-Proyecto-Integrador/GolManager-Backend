package co.golmanager.gestorweb.config;

import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.stereotype.Component;

@Component
@SecurityScheme(
        name = "Bearer Authentication",
        type = io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenAPIConfig {
}
