package co.golmanager.gestorweb.service;

import co.golmanager.gestorweb.controller.models.responses.AuthResponse;
import co.golmanager.gestorweb.controller.models.requests.AuthenticationRequest;
import co.golmanager.gestorweb.controller.models.requests.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthResponse register (RegisterRequest request);
    AuthResponse authenticate (AuthenticationRequest response);
}
