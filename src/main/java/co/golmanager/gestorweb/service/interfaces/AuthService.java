package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.controller.dto.responses.AuthResponse;
import co.golmanager.gestorweb.controller.dto.requests.AuthenticationRequest;
import co.golmanager.gestorweb.controller.dto.requests.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthResponse register (RegisterRequest request);
    AuthResponse authenticate (AuthenticationRequest response);
}
