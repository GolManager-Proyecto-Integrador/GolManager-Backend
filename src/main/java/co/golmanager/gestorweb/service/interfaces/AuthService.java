package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.entity.User;
import co.golmanager.gestorweb.presentation.dto.admin.DeleteOrganizerRequest;
import co.golmanager.gestorweb.presentation.dto.authentication.*;
import co.golmanager.gestorweb.presentation.dto.generalDto.GeneralDeleteResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    RegisterResponse register (RegisterRequest request, String email);
    AuthResponse authenticate (AuthenticationRequest response);
    User updateUser(UpdateRequest request, String email);
    RegisterResponse updateUserResponse(UpdateRequest request, String email);
    GeneralDeleteResponse deleteUserResponse(DeleteOrganizerRequest request, String email);
}
