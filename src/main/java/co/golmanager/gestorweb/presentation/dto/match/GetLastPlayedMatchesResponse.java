package co.golmanager.gestorweb.presentation.dto.match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetLastPlayedMatchesResponse {
    private List<GetLastPlayedMatchesDTO> matches;
}
