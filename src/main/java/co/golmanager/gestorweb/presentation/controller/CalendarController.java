package co.golmanager.gestorweb.presentation.controller;

import co.golmanager.gestorweb.presentation.dto.match.CreateMatchRequest;
import co.golmanager.gestorweb.presentation.dto.match.CreateMatchResponse;
import co.golmanager.gestorweb.presentation.dto.match.GetMatchResponse;
import co.golmanager.gestorweb.service.interfaces.CalendarService;
import co.golmanager.gestorweb.service.interfaces.MatchService;
import co.golmanager.gestorweb.service.interfaces.PermissionEvaluatorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tournaments/matches/calendar")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Calendar", description = "Endpoints for managing calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;
    private final PermissionEvaluatorService permissionEvaluatorService;
    private final MatchService matchService;

    @GetMapping
    public ResponseEntity<List<GetMatchResponse>> getAllMatchesForOrganizer (@RequestParam OffsetDateTime initialDate, @RequestParam OffsetDateTime finishDate, Authentication authentication) {
        return ResponseEntity.ok(calendarService.getMatches(authentication, initialDate, finishDate));
    }

    @PostMapping
    public ResponseEntity<CreateMatchResponse> createMatch(@RequestBody CreateMatchRequest createMatchRequest, Authentication authentication) {
            permissionEvaluatorService.canAccessTournament(createMatchRequest.getTournamentId(), authentication);
            return ResponseEntity.ok(matchService.createMatchResponse(createMatchRequest.getTournamentId(), createMatchRequest ,authentication.getName()));
    }
}
