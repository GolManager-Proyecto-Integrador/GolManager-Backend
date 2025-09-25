package co.golmanager.gestorweb.controller.dto.requests;

import co.golmanager.gestorweb.enums.TournamentFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTournamentRequest {
    @NotBlank
    private String name;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull
    private TournamentFormat format;

    @NotNull
    private boolean homeAndAway;

    @NotNull
    private int numberOfTeams;

    @NotNull
    private int yellowCardsSuspension;

    private List<Long> refereeIds;
}
