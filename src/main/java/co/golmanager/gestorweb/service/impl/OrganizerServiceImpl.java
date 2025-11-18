package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.entity.User;
import co.golmanager.gestorweb.enums.Role;
import co.golmanager.gestorweb.presentation.dto.admin.OrganizersResponse;
import co.golmanager.gestorweb.presentation.dto.organizer.GetDashboardOrganizerResponse;
import co.golmanager.gestorweb.repository.MatchRepository;
import co.golmanager.gestorweb.repository.TeamRepository;
import co.golmanager.gestorweb.repository.TournamentRepository;
import co.golmanager.gestorweb.repository.UserRepository;
import co.golmanager.gestorweb.service.interfaces.OrganizerService;
import co.golmanager.gestorweb.service.interfaces.UserService;
import co.golmanager.gestorweb.util.ValidationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrganizerServiceImpl implements OrganizerService {

    private final UserRepository userRepository;
    private final TournamentRepository tournamentRepository;
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final UserService userService;

    @Override
    public List<OrganizersResponse> getOrganizers(String email) {
        ValidationUtils.roleAuthorizationValidation(userService.getUserByEmail(email));
        log.info("Getting organizers for email {}", email);
        return userRepository.findOrganizersWithTournamentCount(Role.USER);
    }

    @Transactional
    @Override
    public GetDashboardOrganizerResponse getDashboardOrganizer(Authentication authentication) {
        log.info("Getting dashboard organizer for email {}", authentication.getName());
        User user = userService.getUserByEmail(authentication.getName());
        int numTournamentByOrganizer = tournamentRepository.countTournamentsByOrganizer(user.getId());
        int numTournamentCreateThisMonthByOrganizer = tournamentRepository.countTournamentsCreatedSince(user.getId(),  OffsetDateTime.now().minusDays(30));
        int numTournamentInProgressByOrganizer = tournamentRepository.countTournamentsInProgress(user.getId(), LocalDate.now());
        int numMatchesThisWeekByOrganizer = matchRepository.countMatchInRange(user.getId(), OffsetDateTime.now().minusDays(3),OffsetDateTime.now().plusDays(3));
        int numTeamsRegisteredByOrganizer = teamRepository.countTeamByOrganizer(user.getId());
        return GetDashboardOrganizerResponse.builder()
                .userName(user.getUsername())
                .numTournaments(numTournamentByOrganizer)
                .numTournamentsCreateThisMonth(numTournamentCreateThisMonthByOrganizer)
                .numTournamentsInProgress(numTournamentInProgressByOrganizer)
                .numMatchesThisWeek(numMatchesThisWeekByOrganizer)
                .numTeamsRegistered(numTeamsRegisteredByOrganizer)
                .build();
    }


}
