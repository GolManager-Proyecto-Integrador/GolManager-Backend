package co.golmanager.gestorweb.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;

@Component
@Profile("dev")
public class OpenApiExporter {

    @EventListener(ApplicationReadyEvent.class)
    public void exportOpenApi() {
        try {
            String apiDocsUrl = "http://localhost:8085/v3/api-docs";
            RestTemplate restTemplate = new RestTemplate();
            String openApiJson = restTemplate.getForObject(apiDocsUrl, String.class);

            File output = new File("src/main/resources/static/docs/openapi.json");
            output.getParentFile().mkdirs();

            try (FileWriter writer = new FileWriter(output)) {
                writer.write(openApiJson);
            }

            System.out.println("✅ OpenAPI JSON exportado en: " + output.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("❌ Error exportando OpenAPI: " + e.getMessage());
        }
    }
}
