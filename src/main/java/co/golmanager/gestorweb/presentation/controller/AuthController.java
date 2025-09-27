package co.golmanager.gestorweb.presentation.controller;


import co.golmanager.gestorweb.presentation.dto.authentication.AuthResponse;
import co.golmanager.gestorweb.presentation.dto.authentication.AuthenticationRequest;
import co.golmanager.gestorweb.presentation.dto.authentication.RegisterRequest;
import co.golmanager.gestorweb.service.interfaces.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Controller of Auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));

    }

    @PostMapping("/login")
    @Operation(
            summary = "Login User",
            description = "Authenticate a user and return the authentication token along wit user details.",
            tags = "Authenticate",
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
            responses = @ApiResponse(
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
            )


    )
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));

    }


}
