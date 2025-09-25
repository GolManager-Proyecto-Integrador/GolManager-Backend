package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.entity.Team;
import co.golmanager.gestorweb.entity.TeamPosition;
import co.golmanager.gestorweb.entity.Tournament;
import org.springframework.stereotype.Service;

@Service
public interface TeamPositionService {
    public TeamPosition createTeamPosition(Tournament tournament, Team team);
}
