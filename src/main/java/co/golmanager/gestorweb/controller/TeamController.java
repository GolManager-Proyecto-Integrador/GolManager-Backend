package co.golmanager.gestorweb.controller;

import co.golmanager.gestorweb.controller.dto.requests.CreateTeamRequest;
import co.golmanager.gestorweb.controller.dto.responses.CreateTeamResponse;
import co.golmanager.gestorweb.service.interfaces.TeamService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teams")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name= "Teams", description = "Endpoints for managing teams.")
@RequiredArgsConstructor
public class TeamController {

    @Autowired
    private final TeamService teamService;

//    @GetMapping
//    public List<Team> getTeams() {}
//
    @PostMapping("/{idTournament}")
    public ResponseEntity<CreateTeamResponse> createTeam (@PathVariable Long idTournament, @Valid @RequestBody CreateTeamRequest createTeamRequest, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(teamService.createTeamResponse(teamService.createTeam(createTeamRequest, email, idTournament)));
    }


}
