package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.enums.Role;
import co.golmanager.gestorweb.presentation.dto.admin.OrganizersResponse;
import co.golmanager.gestorweb.repository.UserRepository;
import co.golmanager.gestorweb.service.interfaces.OrganizerService;
import co.golmanager.gestorweb.service.interfaces.UserService;
import co.golmanager.gestorweb.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrganizerServiceImpl implements OrganizerService {

    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public List<OrganizersResponse> getOrganizers(String email) {
        ValidationUtils.roleAuthorizationValidation(userService.getUserByEmail(email));
        log.info("Getting organizers for email {}", email);
        return userRepository.findOrganizersWithTournamentCount(Role.USER);
    }
}
