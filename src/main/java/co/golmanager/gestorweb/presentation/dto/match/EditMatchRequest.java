package co.golmanager.gestorweb.presentation.dto.match;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditMatchRequest {
    @NotNull
    private Long matchId;
    private OffsetDateTime matchDate;
    private String stadium;
    private Long RefereeId;
}
