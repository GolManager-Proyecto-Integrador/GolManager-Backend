package co.golmanager.gestorweb.presentation.dto.authentication;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank (message = "The name cannot be empty.")
    private String name;
    @NotBlank (message = "The email cannot be empty.")
    private String email;
    @NotBlank (message = "The password cannot be empty.")
    private String password;
}
