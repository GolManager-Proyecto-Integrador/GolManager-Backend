package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.presentation.dto.match.GetMatchResponse;
import org.springframework.security.core.Authentication;

import java.time.OffsetDateTime;
import java.util.List;

public interface CalendarService {
    List<GetMatchResponse> getMatches(Authentication authentication, OffsetDateTime initialDate, OffsetDateTime finishDate);
}
