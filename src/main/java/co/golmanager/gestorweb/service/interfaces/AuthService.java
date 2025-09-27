package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.presentation.dto.authentication.AuthResponse;
import co.golmanager.gestorweb.presentation.dto.authentication.AuthenticationRequest;
import co.golmanager.gestorweb.presentation.dto.authentication.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthResponse register (RegisterRequest request);
    AuthResponse authenticate (AuthenticationRequest response);
}
