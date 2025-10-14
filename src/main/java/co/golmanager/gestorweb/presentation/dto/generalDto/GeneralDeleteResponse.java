package co.golmanager.gestorweb.presentation.dto.generalDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralDeleteResponse {
    Long elementId;
    String elementName;
    OffsetDateTime deletionElementDate;
}
