package co.golmanager.gestorweb.presentation.controller;

import co.golmanager.gestorweb.presentation.dto.authentication.AuthResponse;
import co.golmanager.gestorweb.presentation.dto.generalDto.GeneralDeleteResponse;
import co.golmanager.gestorweb.presentation.dto.generalDto.GeneralErrorResponse;
import co.golmanager.gestorweb.presentation.dto.team.*;
import co.golmanager.gestorweb.service.interfaces.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    private final TeamService teamService;

    @GetMapping
    @Operation(summary = "Obtain all teams for tournament",
            description = "Bring the list of teams for a tournament registered in the database.",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "List of teams obtained correctly",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Teams Response",
                                            value = "{\"referees\":[{\"id\":1,\"name\":\"Wilmar Roldan\"},{\"id\":2,\"name\":\"Nicolas Gallo\"},{\"id\":3,\"name\":\"Carlos Ortega\"}]}"
                                    )
                            }
                    )
            ), @ApiResponse(
                    responseCode = "404",
                    description = "List of teams are empty",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Referees not found",
                                            value = """
                                                    {
                                                        "path": "/api/refeeres",
                                                        "error": "Not found",
                                                        "message": "There are no referees registered in the system",
                                                        "status": 404
                                                    }"""
                                    )
                            }
                    )
            )
            })
    public ResponseEntity<List<GetTeamsTournamentSummaryResponse>> getAllTeamsByTournament(@PathVariable Long idTournament, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(teamService.getTeamsTournamentResponse(idTournament, email));
    }

    @GetMapping("/{idTeam}")
    public ResponseEntity<TeamDetailsResponse> getTeamDetails(@PathVariable Long idTournament, Authentication authentication,  @PathVariable Long idTeam) {
        String email = authentication.getName();
        return ResponseEntity.ok(teamService.getTeamDetailsResponse(idTournament, email, idTeam));
    }

    @Operation(summary = "Create a team for selected tournament")
    @PostMapping
    public ResponseEntity<CreateTeamResponse> createTeam (@PathVariable Long idTournament, @Valid @RequestBody CreateTeamRequest createTeamRequest, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(teamService.createTeamResponse(createTeamRequest, email, idTournament));
    }

    @Operation(summary = "Update an existing tournament", description = "Update the details of existing tournament identified by its ID.")
    @PutMapping("/{idTeam}")
    public ResponseEntity<TeamDetailsResponse> updateTeamInfo (@PathVariable Long idTeam, @PathVariable Long idTournament, @Valid @RequestBody UpdateTeamRequest request, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(teamService.updateTeamResponse(idTeam, idTournament, request, email));
    }

    @Operation(summary = "Delete an team", description = "Delete team (players, score, goals, cards) of existing tournament")
    @DeleteMapping("/{idTeam}")
    public ResponseEntity<GeneralDeleteResponse> deleteTeam(@PathVariable Long idTournament, @PathVariable Long idTeam, Authentication authentication) {
      return ResponseEntity.ok(teamService.deleteTeam(idTeam, idTournament, authentication.getName()));
    }

}
