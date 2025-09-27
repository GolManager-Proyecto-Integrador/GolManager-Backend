package co.golmanager.gestorweb.presentation.controller;


import co.golmanager.gestorweb.presentation.dto.referee.RefereeListResponse;
import co.golmanager.gestorweb.service.interfaces.RefereeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/referees")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Referees", description = "Endpoints for managing referees")
@RequiredArgsConstructor
public class RefereeController {

    @Autowired
    private final RefereeService refereeService;

    @GetMapping
    public RefereeListResponse listReferees(Authentication authentication) {
        String email = authentication.getName();
        return refereeService.listReferees(email);
    }
}
