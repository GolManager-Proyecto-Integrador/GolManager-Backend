package co.golmanager.gestorweb.service;

import co.golmanager.gestorweb.controller.models.AuthResponse;
import co.golmanager.gestorweb.controller.models.AuthenticationRequest;
import co.golmanager.gestorweb.controller.models.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthResponse register (RegisterRequest request);
    AuthResponse authenticate (AuthenticationRequest response);
}
