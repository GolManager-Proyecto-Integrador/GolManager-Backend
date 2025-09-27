package co.golmanager.gestorweb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;

@Slf4j
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
                assert openApiJson != null;
                writer.write(openApiJson) ;
            }
            log.info("OpenAPI JSON exported to: {}", output.getAbsolutePath());
        } catch (Exception e) {
            log.warn("Error while writing OpenAPI JSON file: ", e);
        }
    }
}
