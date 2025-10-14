package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.presentation.dto.authentication.*;
import co.golmanager.gestorweb.entity.User;
import co.golmanager.gestorweb.enums.Role;
import co.golmanager.gestorweb.repository.UserRepository;
import co.golmanager.gestorweb.service.interfaces.AuthService;
import co.golmanager.gestorweb.service.interfaces.UserService;
import co.golmanager.gestorweb.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Override
    public RegisterResponse register(RegisterRequest request, String requesterEmail) {
        ValidationUtils.roleAuthorizationValidation(userService.getUserByEmail(requesterEmail));
        emailValidation(request.getEmail());
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        log.info("Register request: username={}, email={}, role={}",
                user.getName(), user.getEmail(), user.getRole());
        return RegisterResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .status(200)
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponse authenticate(AuthenticationRequest request) {
        log.info("Attempt to log in with email: {}", request.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public User updateUser(UpdateRequest request, String email) {
        ValidationUtils.roleAuthorizationValidation(userService.getUserByEmail(email));
        emailValidation(request.getNewEmail());
        User user = userService.getUserByEmail(request.getActualEmail());

        user.setName(request.getNewName());
        user.setEmail(request.getNewEmail());
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        log.info("User with id={} has been updated successful ", user.getId());

        var savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public RegisterResponse updateUserResponse(UpdateRequest request, String email) {
        User user = updateUser(request, email);
        return RegisterResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .status(200)
                .token(jwtService.generateToken(user))
                .build();
    }

    private void emailValidation(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ("The email " + email + " is already registered") );
        }
    }

}
