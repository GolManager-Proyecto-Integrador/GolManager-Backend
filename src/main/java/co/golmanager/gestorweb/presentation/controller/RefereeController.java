package co.golmanager.gestorweb.presentation.controller;


import co.golmanager.gestorweb.presentation.dto.authentication.AuthResponse;
import co.golmanager.gestorweb.presentation.dto.authentication.AuthenticationRequest;
import co.golmanager.gestorweb.presentation.dto.generalDto.GeneralErrorResponse;
import co.golmanager.gestorweb.presentation.dto.referee.RefereeListResponse;
import co.golmanager.gestorweb.service.interfaces.RefereeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

    private final RefereeService refereeService;

    @GetMapping
    @Operation(
            summary = "List of Referees",
            description = "Bring the list of referees registered in the database.",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "List of referees obtained correctly",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Referees list",
                                            value = "{\"Token\": \"eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsInN1YiI6Im9yZ2FuaXphZG9yQHRvcm5lb3MuY29tIiwiaWF0IjoxNzU4OTMxMzk0LCJleHAiOjE3NTg5Mzg1OTR9.9EfE3GP_ZfVFDHIaxpbmVAS3_NajHdJySi8kSRe4dpA\"}"
                                    )
                            }
                    )
            ), @ApiResponse(
                    responseCode = "404",
                    description = "",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Unsuccessful Login",
                                            value = """
                                                    {
                                                        "path": "/api/auth/login",
                                                        "error": "Unauthorized",
                                                        "message": "No autorizado o token inválido",
                                                        "status": 401
                                                    }"""
                                    )
                            }
                    )
            )
            })
    public RefereeListResponse listReferees(Authentication authentication) {
        String email = authentication.getName();
        return refereeService.listReferees(email);
    }
}
