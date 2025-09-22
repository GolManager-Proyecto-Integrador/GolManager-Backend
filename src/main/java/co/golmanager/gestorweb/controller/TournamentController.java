package co.golmanager.gestorweb.controller;

import co.golmanager.gestorweb.controller.models.requests.CreateTournamentRequest;
import co.golmanager.gestorweb.controller.models.responses.CreateTournamentResponse;
import co.golmanager.gestorweb.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tournaments")
@RequiredArgsConstructor
class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @PostMapping("/create")
    public ResponseEntity<CreateTournamentResponse> createTournament
            (@Valid @RequestBody CreateTournamentRequest request, Authentication authentication) {

        String email = authentication.getName();
        return ResponseEntity.ok(tournamentService.createTournament(request, email));
    }
}
