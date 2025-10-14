package co.golmanager.gestorweb.presentation.controller;

import co.golmanager.gestorweb.presentation.dto.admin.AdminDashboardResponse;
import co.golmanager.gestorweb.presentation.dto.admin.OrganizersResponse;
import co.golmanager.gestorweb.presentation.dto.authentication.RegisterRequest;
import co.golmanager.gestorweb.presentation.dto.authentication.RegisterResponse;
import co.golmanager.gestorweb.presentation.dto.authentication.UpdateRequest;
import co.golmanager.gestorweb.presentation.dto.generalDto.GeneralErrorResponse;
import co.golmanager.gestorweb.service.interfaces.AdminDashboardService;
import co.golmanager.gestorweb.service.interfaces.AuthService;
import co.golmanager.gestorweb.service.interfaces.OrganizerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Controller of Admin")
public class AdminController {

    private final AdminDashboardService adminDashboardService;
    private final OrganizerService organizerService;
    private final AuthService authService;

    @GetMapping("/dashboard")
    @Operation(summary = "Delivers the data to display on the administration dashboard.")
    public ResponseEntity<AdminDashboardResponse> getDashboard(@Valid Authentication authentication) {
        return ResponseEntity.ok(adminDashboardService.getDashboardAdminInfoResponse(authentication.getName()));
    }

    @GetMapping("/organizers")
    @Operation(summary = "Displays the organizers registered in the system with the number of associated tournaments.")
    public ResponseEntity<List<OrganizersResponse>> getOrganizers(Authentication authentication) {
        String email = authentication.getName();
        List<OrganizersResponse> organizers = organizerService.getOrganizers(email);
        return ResponseEntity.ok(organizers);
    }

//    public ResponseEntity<RegisterRequest> registerOrganizer(Authentication authentication, @Valid RegisterRequest registerRequest) {
//        String email = authentication.getName();
//        return ResponseEntity.ok(registerRequest);
//    }

    @PostMapping("/organizers")
    @Operation(
            summary = "Register new user",
            description = "Register a new user with the administrator profile.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RegisterRequest.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Successful Register",
                                            value = "{\"name\" :\"organizador1\" ,\"email\": \"organizador@torneos.com\",\"password\": \"organizador123\"}"
                                    )
                            }
                    )
            ),
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Succesful register",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RegisterResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Successful Login",
                                            value = "{\"id\":10,\"email\":\"organizador6@torneos.com\",\"name\":\"organizador6\",\"status\":200,\"token\":\"eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsInN1YiI6Im9yZ2FuaXphZG9yNkB0b3JuZW9zLmNvbSIsImlhdCI6MTc1OTEwOTU2MywiZXhwIjoxNzU5MTE2NzYzfQ.T5YVYGSBmZNg5wTJh_VjtPJURh3vIoOCTyxqFMQM7os\"}"
                                    )
                            }
                    )
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Empty registration fields",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Empty registration fields",
                                            value = "{\"status\":400,\"error\":\"Validation Failed\",\"messages\":[\"name: The name cannot be empty.\",\"password: The password cannot be empty.\",\"email: The email cannot be empty.\"]}"
                                    )
                            }
                    )
            ), @ApiResponse(
                    responseCode = "401",
                    description = "A user without administrator permissions attempted to register a new user.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "A user without permissions.",
                                            value = "{\"path\":\"/error\",\"error\":\"Unauthorized\",\"message\":\"No autorizado o token inválido\",\"status\":401}"
                                    )
                            }
                    )
            )


            }



    )
    public ResponseEntity<RegisterResponse> registerOrganizer(@RequestBody @Valid RegisterRequest request, Authentication authentication) {
        return ResponseEntity.ok(authService.register(request, authentication.getName()));

    }

    @PutMapping("/organizers")
    public ResponseEntity<RegisterResponse> updateOrganizerAuth(@RequestBody @Valid UpdateRequest request, Authentication authentication) {
        return ResponseEntity.ok(authService.updateUserResponse(request, authentication.getName()));
    }
}
