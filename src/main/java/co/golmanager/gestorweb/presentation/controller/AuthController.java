package co.golmanager.gestorweb.presentation.controller;


import co.golmanager.gestorweb.presentation.dto.authentication.AuthResponse;
import co.golmanager.gestorweb.presentation.dto.authentication.AuthenticationRequest;
import co.golmanager.gestorweb.presentation.dto.authentication.RegisterRequest;
import co.golmanager.gestorweb.presentation.dto.authentication.RegisterResponse;
import co.golmanager.gestorweb.presentation.dto.generalDto.GeneralErrorResponse;
import co.golmanager.gestorweb.service.interfaces.AuthService;
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

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Controller of Auth")
public class AuthController {


    private final AuthService authService;


    @PostMapping("/register")
    @Operation(
            summary = "Register new user",
            description = "Register a new user with the administrator profile.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RegisterRequest.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Successful Login",
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
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest request, Authentication authentication) {
        return ResponseEntity.ok(authService.register(request, authentication.getName()));

    }

    @PostMapping("/login")
    @Operation(
            summary = "Login User",
            description = "Authenticate a user and return the authentication token along wit user details.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthenticationRequest.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Successful Login",
                                            value = "{\"user\": \"organizador@torneos.com\",\"password\": \"organizador123\"}"
                                    )
                            }
                    )
            ),
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Succesful authentication",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponse.class),
                            examples = {
                                @ExampleObject(
                                        name = "Successful Login",
                                        value = "{\"Token\": \"eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsInN1YiI6Im9yZ2FuaXphZG9yQHRvcm5lb3MuY29tIiwiaWF0IjoxNzU4OTMxMzk0LCJleHAiOjE3NTg5Mzg1OTR9.9EfE3GP_ZfVFDHIaxpbmVAS3_NajHdJySi8kSRe4dpA\"}"
                                )
                            }
                    )
            ), @ApiResponse(
                    responseCode = "401",
                    description = "Unsuccesful authentication",
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
            }



    )
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));

    }


}
