package co.golmanager.gestorweb.presentation.dto.authentication;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequest {
    @NotBlank (message = "the email cannot be empty.")
    private String actualEmail;
    @NotBlank (message = "The name cannot be empty.")
    private String newName;
    @NotBlank (message = "The email cannot be empty.")
    private String newEmail;
    @NotBlank (message = "The password cannot be empty.")
    private String newPassword;
}
