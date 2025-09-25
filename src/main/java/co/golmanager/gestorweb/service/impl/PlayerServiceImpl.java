package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.controller.dto.requests.CreatePlayerRequest;
import co.golmanager.gestorweb.entity.Player;
import co.golmanager.gestorweb.entity.Team;
import co.golmanager.gestorweb.enums.PlayerStatus;
import co.golmanager.gestorweb.repository.PlayerRepository;
import co.golmanager.gestorweb.service.interfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player createPlayer(CreatePlayerRequest createPlayerRequest, Team team) {

        Player player = Player.builder()
                .name(createPlayerRequest.getName())
                .age(createPlayerRequest.getAge())
                .position(createPlayerRequest.getPlayerPosition())
                .shirtNumber(createPlayerRequest.getShirtNumber())
                .team(team)
                .status(PlayerStatus.ACTIVE)
                .build();

        Player savedPlayer = playerRepository.save(player);

        return savedPlayer;
    }
}
