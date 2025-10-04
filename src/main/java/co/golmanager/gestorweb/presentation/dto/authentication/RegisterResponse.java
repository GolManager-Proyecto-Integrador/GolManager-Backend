package co.golmanager.gestorweb.presentation.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RegisterResponse {
    private long id;
    private String email;
    private String name;
    private int status;
    private String token;
}
