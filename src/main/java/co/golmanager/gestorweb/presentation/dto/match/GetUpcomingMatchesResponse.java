package co.golmanager.gestorweb.presentation.dto.match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUpcomingMatchesResponse {
    List<GetUpcomingMatchesDTO> matches;
}
