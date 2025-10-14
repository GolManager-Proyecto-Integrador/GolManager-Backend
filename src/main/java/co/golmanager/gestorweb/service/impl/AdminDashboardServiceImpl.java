package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.entity.User;
import co.golmanager.gestorweb.presentation.dto.admin.AdminDashboardResponse;
import co.golmanager.gestorweb.repository.TeamRepository;
import co.golmanager.gestorweb.repository.TournamentRepository;
import co.golmanager.gestorweb.repository.UserRepository;
import co.golmanager.gestorweb.service.interfaces.AdminDashboardService;
import co.golmanager.gestorweb.service.interfaces.UserService;
import co.golmanager.gestorweb.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static co.golmanager.gestorweb.enums.Role.USER;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminDashboardServiceImpl implements AdminDashboardService {


    private final UserService userService;
    private final UserRepository userRepository;
    private final TournamentRepository tournamentRepository;
    private final TeamRepository teamRepository;

    @Override
    public AdminDashboardResponse getDashboardAdminInfoResponse(String email) {
        User user = userService.getUserByEmail(email);
        ValidationUtils.roleAuthorizationValidation(user);

        return AdminDashboardResponse.builder()
                .userName(user.getName())
                .numOrganizers(getNumberOfOrganizers())
                .numTournaments(getNumberOfTournaments())
                .numTeams(getNumberOfTeams())
                .build();
    }

    @Override
    public int getNumberOfOrganizers() {
        return userRepository.countByRole(USER);
    }

    @Override
    public int getNumberOfTournaments() {
        return tournamentRepository.countAllBy();
    }

    @Override
    public int getNumberOfTeams() {
        return teamRepository.countAllBy();
    }


}
