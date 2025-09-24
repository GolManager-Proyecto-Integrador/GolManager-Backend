package co.golmanager.gestorweb.controller;

import co.golmanager.gestorweb.controller.models.requests.CreateTournamentRequest;
import co.golmanager.gestorweb.controller.models.responses.CreateTournamentResponse;
import co.golmanager.gestorweb.controller.models.responses.TournamentDeleteResponse;
import co.golmanager.gestorweb.controller.models.responses.TournamentDetailResponse;
import co.golmanager.gestorweb.controller.models.responses.TournamentSummaryResponse;
import co.golmanager.gestorweb.service.TournamentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Tournaments", description = "Endpoints for managing tournaments")
@RequiredArgsConstructor
class TournamentController {

    @Autowired
    private final TournamentService tournamentService;

    @Operation(summary = "Obtain all tournaments for the authenticated user", description = "Retrieves a list of all tournaments associated with the authenticated user.")
    @GetMapping
    public ResponseEntity<List<TournamentSummaryResponse>> getAllTournamentsById(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(tournamentService.getAllTournamentsById(email));
    }

    @Operation(summary = "Get tournament details by ID", description = "Retrieves detailed information about a specific tournament by its ID.")
    @GetMapping("/{Id}")
    public ResponseEntity<TournamentDetailResponse> getTournamentById(@PathVariable String Id, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(tournamentService.getTournamentById(Id, email));
    }


    @Operation(summary = "Create a new tournament", description = "Creates a new tournament with the provided details.")
    @PostMapping("/create")
    public ResponseEntity<CreateTournamentResponse> createTournament
            (@Valid @RequestBody CreateTournamentRequest request, Authentication authentication) {

        String email = authentication.getName();
        return ResponseEntity.ok(tournamentService.createTournament(request, email));
    }

    @Operation(summary = "Update an existing tournament", description = "Updates the details of an existing tournament identified by its ID.")
    @PutMapping("/{Id}")
    public ResponseEntity<TournamentDetailResponse> updateTournament
            (@PathVariable Long Id, @Valid @RequestBody CreateTournamentRequest request, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(tournamentService.updateTournament(Id, request, email));
    }

    @Operation(summary = "Delete a tournament", description = "Deletes a tournament identified by its ID.")
    @DeleteMapping("/{Id}")
    public ResponseEntity<TournamentDeleteResponse> deleteTournament(
            @PathVariable Long Id, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(tournamentService.deleteTournament(Id, email));
    }
}
