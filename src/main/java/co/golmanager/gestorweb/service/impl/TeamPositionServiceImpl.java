package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.entity.Team;
import co.golmanager.gestorweb.entity.TeamPosition;
import co.golmanager.gestorweb.entity.Tournament;
import co.golmanager.gestorweb.repository.TeamPositionRepository;
import co.golmanager.gestorweb.repository.TeamRepository;
import co.golmanager.gestorweb.service.interfaces.TeamPositionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamPositionServiceImpl implements TeamPositionService {

    @Autowired
    private TeamPositionRepository teamPositionRepository;

    @Override
    @Transactional
    public TeamPosition createTeamPosition(Tournament tournament, Team team) {

        TeamPosition teamPosition = TeamPosition.builder()
                .team(team)
                .tournament(tournament)
                .goalsAgainst(0)
                .goalsScored(0)
                .matchesLost(0)
                .matchesWon(0)
                .matchesPlayed(0)
                .matchesTied(0)
                .points(0)
                .build();

        TeamPosition teamPositionSaved = teamPositionRepository.save(teamPosition);

        return teamPositionSaved;
    }
}
