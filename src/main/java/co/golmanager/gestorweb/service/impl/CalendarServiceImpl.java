package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.entity.User;
import co.golmanager.gestorweb.presentation.dto.match.GetMatchResponse;
import co.golmanager.gestorweb.repository.MatchRepository;
import co.golmanager.gestorweb.service.interfaces.CalendarService;
import co.golmanager.gestorweb.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalendarServiceImpl implements CalendarService {

    private final MatchRepository matchRepository;
    private final UserService userService;

    @Override
    public List<GetMatchResponse> getMatches(Authentication authentication, OffsetDateTime  initialDate, OffsetDateTime finishDate) {
       User user =  userService.getUserByEmail(authentication.getName());
        List<GetMatchResponse> matches = matchRepository.findAllMatchesByOrganizerId(user.getId(), initialDate, finishDate);
        log.info("Matches for user {} correctly consulted", user.getId());
        return matches;
    }
}
