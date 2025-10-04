package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.presentation.dto.authentication.AuthResponse;
import co.golmanager.gestorweb.presentation.dto.authentication.AuthenticationRequest;
import co.golmanager.gestorweb.presentation.dto.authentication.RegisterRequest;
import co.golmanager.gestorweb.presentation.dto.authentication.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    RegisterResponse register (RegisterRequest request, String email);
    AuthResponse authenticate (AuthenticationRequest response);
}
