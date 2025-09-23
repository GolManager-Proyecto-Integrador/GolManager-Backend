package co.golmanager.gestorweb.controller.models.responses;

import co.golmanager.gestorweb.entity.Referee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefereeListResponse {
    private List<Referee> referees;
}
