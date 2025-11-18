package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.repository.TournamentRepository;
import co.golmanager.gestorweb.service.interfaces.PermissionEvaluatorService;
import co.golmanager.gestorweb.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionEvaluatorServiceImpl implements PermissionEvaluatorService {

    private final TournamentRepository tournamentRepository;
    private final UserService userService;

    @Override
    public void canAccessTournament(Long tournamentId, Authentication authentication) {
        var tournament = tournamentRepository.findById(tournamentId).orElse(null);
        if (tournament == null)  throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found.");
        String email = authentication.getName();
        var user = userService.getIdByEmail(email);

        if (!tournament.getUser().getId().equals(user)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The User are not allowed to perform this action.");
        }

        log.info("Can Access Tournament " + tournamentId);

    }


}
