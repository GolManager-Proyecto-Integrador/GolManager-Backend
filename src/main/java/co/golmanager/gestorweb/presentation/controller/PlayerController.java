package co.golmanager.gestorweb.presentation.controller;

import co.golmanager.gestorweb.presentation.dto.player.GetPlayerDTOResponse;
import co.golmanager.gestorweb.service.interfaces.PlayerService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Players", description = "Endpoints for managing players info")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayersByStatus(@Parameter(name = "status") String status, @PathVariable Long id, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(playerService.getSuspendedPlayers(status, id, email));
    }

    @GetMapping("/{idTournament}/teams/{idTeam}")
    public ResponseEntity <List<GetPlayerDTOResponse>> getPlayersByTournamentAndTeam(@PathVariable Long idTournament, @PathVariable Long idTeam ,Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(playerService.getPlayersByTournamentIdAndTeam(idTournament, idTeam, email));
    }
}
