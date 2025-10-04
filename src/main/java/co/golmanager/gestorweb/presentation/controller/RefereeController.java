package co.golmanager.gestorweb.presentation.controller;

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
                    description = "List of referees obtained",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RefereeListResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Referees list",
                                            value = "{\"referees\":[{\"id\":1,\"name\":\"Wilmar Roldan\"},{\"id\":2,\"name\":\"Nicolas Gallo\"},{\"id\":3,\"name\":\"Carlos Ortega\"}]}"
                                    )
                            }
                    )
            ), @ApiResponse(
                    responseCode = "404",
                    description = "Referees not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GeneralErrorResponse.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Referees not found",
                                            value = """
                                                    {
                                                        "path": "/api/refeeres",
                                                        "error": "Not found",
                                                        "message": "There are no referees registered in the system",
                                                        "status": 404
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
