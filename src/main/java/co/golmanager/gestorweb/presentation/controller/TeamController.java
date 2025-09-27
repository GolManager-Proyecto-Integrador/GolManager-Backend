package co.golmanager.gestorweb.presentation.controller;

import co.golmanager.gestorweb.presentation.dto.team.CreateTeamRequest;
import co.golmanager.gestorweb.presentation.dto.team.CreateTeamResponse;
import co.golmanager.gestorweb.presentation.dto.team.GetTeamsTournamentResponse;
import co.golmanager.gestorweb.service.interfaces.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments/{idTournament}/teams")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name= "Teams", description = "Endpoints for managing teams.")
@RequiredArgsConstructor
public class TeamController {

    @Autowired
    private final TeamService teamService;

    @Operation(summary = "Obtain all teams for tournament")
    @GetMapping
    public ResponseEntity<List<GetTeamsTournamentResponse>> getAllTeamsByTournament(@PathVariable Long idTournament,Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(teamService.getTeamsTournamentResponse(idTournament, email));
    }

    @Operation(summary = "Create a team for selected tournament")
    @PostMapping
    public ResponseEntity<CreateTeamResponse> createTeam (@PathVariable Long idTournament, @Valid @RequestBody CreateTeamRequest createTeamRequest, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(teamService.createTeamResponse(createTeamRequest, email, idTournament));
    }

}
