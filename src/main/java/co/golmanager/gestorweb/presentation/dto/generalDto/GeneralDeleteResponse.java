package co.golmanager.gestorweb.presentation.dto.generalDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralDeleteResponse {
    Long elementId;
    String elementName;
    LocalDateTime deletionElementDate;
}
