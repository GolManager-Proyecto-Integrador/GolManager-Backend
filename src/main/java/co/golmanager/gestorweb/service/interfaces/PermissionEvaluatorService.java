package co.golmanager.gestorweb.service.interfaces;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service("permissionEvaluatorService")
public interface PermissionEvaluatorService {
    void canAccessTournament(Long tournamentId, Authentication authentication);
}
