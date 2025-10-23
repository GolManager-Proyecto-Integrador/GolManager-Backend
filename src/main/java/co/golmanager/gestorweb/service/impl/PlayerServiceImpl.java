package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.presentation.dto.player.*;
import co.golmanager.gestorweb.entity.Player;
import co.golmanager.gestorweb.entity.Team;
import co.golmanager.gestorweb.enums.PlayerStatus;
import co.golmanager.gestorweb.repository.PlayerRepository;
import co.golmanager.gestorweb.service.interfaces.PlayerService;
import co.golmanager.gestorweb.service.interfaces.TournamentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TournamentService tournamentService;

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

    @Override
    public SuspendedPlayersResponse getSuspendedPlayers(String status, Long tournamentId, String email) {
        tournamentService.getTournamentById(email,tournamentId);
        PlayerStatus playerStatus;
        try {
            playerStatus = PlayerStatus.valueOf(status.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status of player invalid: " + status );
        }

        List<SuspendedPlayerDTO> players = playerRepository.findPlayersByStatusAndTournament(playerStatus, tournamentId);

        return SuspendedPlayersResponse.builder()
                .players(players)
                .build();
    }

    @Override
    public ScorerPlayersResponse getScorerPlayers(Long tournamentId, String email, int numberRegisters) {
        log.info("Obtaint table of scorer for tournament: {}", tournamentId);
        tournamentService.getTournamentById(email,tournamentId);
        Pageable pageable = PageRequest.of(0, numberRegisters);
        List<ScorerPlayerDTO> scorerPlayers = playerRepository.findScorerPlayersByTournamentId(tournamentId, pageable);
        return ScorerPlayersResponse.builder()
                .players(scorerPlayers)
                .build();
    }

    @Override
    public YellowCardPlayersResponse getYellowCardPlayers(Long tournamentId, String email, int numberRegisters) {
        log.info("Obtaint table of yellow cards for tournament: {}", tournamentId);
        tournamentService.getTournamentById(email,tournamentId);
        Pageable pageable = PageRequest.of(0, numberRegisters);
        List<YellowCardPlayerDTO> yellowCardsPlayers = playerRepository.findYellowCardsPlayersByTournamentId(tournamentId, pageable);
        return YellowCardPlayersResponse.builder()
                .players(yellowCardsPlayers)
                .build();
    }
}
