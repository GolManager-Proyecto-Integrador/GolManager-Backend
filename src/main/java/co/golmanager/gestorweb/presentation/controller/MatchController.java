package co.golmanager.gestorweb.presentation.controller;

import co.golmanager.gestorweb.presentation.dto.match.CreateMatchRequest;
import co.golmanager.gestorweb.presentation.dto.match.CreateMatchResponse;
import co.golmanager.gestorweb.service.interfaces.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tournaments/{tournamentId}/matches")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Tournaments", description = "Endpoints for managing matches")
@RequiredArgsConstructor
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Operation(summary = "Create a match with date")
    @PostMapping
    public ResponseEntity<CreateMatchResponse> createMatch (@PathVariable Long tournamentId, @Valid @RequestBody CreateMatchRequest createMatchRequest, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(matchService.createMatchResponse(tournamentId, createMatchRequest, email));
    }
}
