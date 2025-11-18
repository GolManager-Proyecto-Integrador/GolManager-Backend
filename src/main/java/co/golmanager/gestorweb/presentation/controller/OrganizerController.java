package co.golmanager.gestorweb.presentation.controller;

import co.golmanager.gestorweb.presentation.dto.organizer.GetDashboardOrganizerResponse;
import co.golmanager.gestorweb.service.interfaces.OrganizerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organizer")
@RequiredArgsConstructor
@Tag(name = "Organizer", description = "Controller of Dashboard organizer")
public class OrganizerController {

    private final OrganizerService organizerService;

    @GetMapping("/dashboard")
    public ResponseEntity<GetDashboardOrganizerResponse> getDashboardOrganizer(Authentication authentication) {
        return ResponseEntity.ok(organizerService.getDashboardOrganizer(authentication));
    }

}
