package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.controller.dto.requests.CreatePlayerRequest;
import co.golmanager.gestorweb.entity.Player;
import co.golmanager.gestorweb.entity.Team;
import org.springframework.stereotype.Service;

@Service
public interface PlayerService {
    public Player createPlayer(CreatePlayerRequest createPlayerRequest, Team team);
}
