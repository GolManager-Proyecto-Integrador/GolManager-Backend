package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.presentation.dto.player.CreatePlayerRequest;
import co.golmanager.gestorweb.entity.Player;
import co.golmanager.gestorweb.entity.Team;
import co.golmanager.gestorweb.enums.PlayerStatus;
import co.golmanager.gestorweb.repository.PlayerRepository;
import co.golmanager.gestorweb.service.interfaces.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

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
        log.info("Player created: name={}, team={}", player.getName(), player.getTeam().getName());

        return savedPlayer;
    }
}
