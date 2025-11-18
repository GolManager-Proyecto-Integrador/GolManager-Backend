package co.golmanager.gestorweb.presentation.controller;

import co.golmanager.gestorweb.presentation.dto.match.CreateMatchRequest;
import co.golmanager.gestorweb.presentation.dto.match.CreateMatchResponse;
import co.golmanager.gestorweb.presentation.dto.match.GetLastPlayedMatchesResponse;
import co.golmanager.gestorweb.presentation.dto.match.GetMatchResponse;
import co.golmanager.gestorweb.service.interfaces.MatchService;
import co.golmanager.gestorweb.service.interfaces.PermissionEvaluatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments/{tournamentId}/matches")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Matches", description = "Endpoints for managing matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;
    private final PermissionEvaluatorService permissionEvaluatorService;

    @Operation(summary = "Create a match with date")
    @PostMapping
    public ResponseEntity<CreateMatchResponse> createMatch (@PathVariable Long tournamentId, @Valid @RequestBody CreateMatchRequest createMatchRequest, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(matchService.createMatchResponse(tournamentId, createMatchRequest, email));
    }

    @GetMapping("/played")
    public ResponseEntity<GetLastPlayedMatchesResponse> getPlayedMatches(@PathVariable Long tournamentId, @RequestParam(defaultValue = "3") int numberRegisters, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(matchService.getLastPlayedMatches(tournamentId, numberRegisters, email));
    }

    @GetMapping("/upcoming")
    public ResponseEntity<GetLastPlayedMatchesResponse> getUpcomingMatches (@PathVariable Long tournamentId, @RequestParam(defaultValue = "3") int numberRegisters, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(matchService.getUpcomingMatches(tournamentId, numberRegisters, email));

    }

    @PostMapping("/generator")
    public ResponseEntity<List<GetMatchResponse>> createMatchesLeague (@PathVariable Long tournamentId, Authentication authentication) {
        permissionEvaluatorService.canAccessTournament(tournamentId, authentication);
        return ResponseEntity.ok(matchService.generateLeagueMatches(tournamentId, authentication.getName()));
    }

    @GetMapping("{matchId}")
    public ResponseEntity<GetMatchResponse> getMatchDetails (@PathVariable Long tournamentId, @PathVariable Long matchId, Authentication authentication) {
        permissionEvaluatorService.canAccessTournament(tournamentId, authentication);
        return ResponseEntity.ok(matchService.getMatchById(matchId, tournamentId));
    }

}
