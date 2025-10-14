package co.golmanager.gestorweb.presentation.controller;

import co.golmanager.gestorweb.service.interfaces.AdminDashboardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Controller of Admin")
public class AdminController {

    private final AdminDashboardService adminDashboardService;

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboard(@Valid Authentication authentication) {
        return ResponseEntity.ok(adminDashboardService.getDashboardAdminInfoResponse(authentication.getName()));
    }


}
