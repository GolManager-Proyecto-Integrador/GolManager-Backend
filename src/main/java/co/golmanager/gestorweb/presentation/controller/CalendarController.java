package co.golmanager.gestorweb.presentation.controller;

import co.golmanager.gestorweb.presentation.dto.match.GetMatchResponse;
import co.golmanager.gestorweb.service.interfaces.CalendarService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tournaments/matches/calendar")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Calendar", description = "Endpoints for managing calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping
    public ResponseEntity<List<GetMatchResponse>> getAllMatchesForOrganizer (@RequestParam OffsetDateTime initialDate, @RequestParam OffsetDateTime finishDate, Authentication authentication) {
        return ResponseEntity.ok(calendarService.getMatches(authentication, initialDate, finishDate));
    }
}
