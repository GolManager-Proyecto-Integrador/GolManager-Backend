package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.entity.Match;
import co.golmanager.gestorweb.entity.Team;
import co.golmanager.gestorweb.entity.Tournament;
import co.golmanager.gestorweb.presentation.dto.match.*;
import co.golmanager.gestorweb.repository.MatchRepository;
import co.golmanager.gestorweb.service.interfaces.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchServiceImpl implements MatchService {


    private final MatchRepository matchRepository;

    private final TeamService teamService;
    private final TournamentService tournamentService;
    private final RefereeService refereeService;

    @Override
    public Match createMatch(Long tournamentId,CreateMatchRequest request, String email) {

        Match match = Match.builder()
                .homeTeam(teamService.getTeamById(tournamentId, email, request.getHomeTeamId()))
                .awayTeam(teamService.getTeamById(tournamentId, email, request.getAwayTeamId()))
                .tournament(tournamentService.getTournamentById(email, request.getTournamentId()))
                .stadium(request.getStadiumName())
                .referee(refereeService.getReferee(request.getReferee()))
                .matchDate(request.getMatchDate())
                .build();

        Match savedMatch = matchRepository.save(match);
        log.info("Created Match with ID: {}", savedMatch.getId());
        return savedMatch;
    }

    @Override
    public CreateMatchResponse createMatchResponse(Long tournamentId, CreateMatchRequest request, String email) {
        log.info("Attempt create a Match for tournament with ID: {}", tournamentId);
        tournamentService.getTournamentById(email,tournamentId);
        Match match = createMatch(tournamentId, request, email);
        log.info("Time match created with ID: {}, {}", match.getId(), match.getMatchDate());
        return CreateMatchResponse.builder()
                .matchId(match.getId())
                .homeTeam(match.getHomeTeam().getName())
                .awayTeam(match.getAwayTeam().getName())
                .matchDate(match.getMatchDate())
                .stadiumName((match.getStadium()))
                .build();
    }

    @Override
    public GetLastPlayedMatchesResponse getLastPlayedMatches(Long tournamentId, int numberRegisters, String email) {
        tournamentService.getTournamentById(email,tournamentId);
        OffsetDateTime currentDateTime = OffsetDateTime.now();
        Pageable  pageable = PageRequest.of(0, numberRegisters);
        List<GetLastPlayedMatchesDTO> matches = matchRepository.findLastPlayedMatchesByTournamentId(tournamentId, currentDateTime, pageable);
        log.info("List of matches played in the tournament with ID {} obtained correctly", tournamentId);
        return GetLastPlayedMatchesResponse.builder()
                .matches(matches)
                .build();
    }

    @Override
    public GetLastPlayedMatchesResponse getUpcomingMatches(Long tournamentId, int numberRegisters, String email) {
        tournamentService.getTournamentById(email,tournamentId);
        OffsetDateTime currentDateTime = OffsetDateTime.now();

        Pageable  pageable = PageRequest.of(0, numberRegisters);
        List<GetLastPlayedMatchesDTO> matches = matchRepository.findUpcomingMatchesByTournamentId(tournamentId, currentDateTime, pageable);
        log.info("List of matches upcoming in the tournament with ID {} obtained correctly", tournamentId);
        return GetLastPlayedMatchesResponse.builder()
                .matches(matches)
                .build();
    }

    @Override
    public List<GetMatchResponse> generateLeagueMatches(Long tournamentId, String email) {
        Tournament tournament = tournamentService.getTournamentById(email, tournamentId);
        List<Team> teams = teamService.getAllTeamsByTournament(tournamentId);

        LocalDate startDate = tournament.getStartDate();
        LocalDate endDate = tournament.getEndDate();
        boolean homeAndAway = tournament.isHomeAndAway();

        // Si el número de equipos es impar, añadimos un “descansa”
        if (teams.size() % 2 != 0) {
            teams.add(Team.builder().id(-1L).name("Descansa").build());
        }

        int n = teams.size();
        int totalRounds = homeAndAway ? (n - 1) * 2 : (n - 1);
        int matchesPerRound = n / 2;

        List<GetMatchResponse> matches = new ArrayList<>();
        List<Match> matchesBD = new ArrayList<>();
        List<Team> rotatedTeams = new ArrayList<>(teams);

        long totalDays = ChronoUnit.DAYS.between(startDate, endDate);
        long daysBetweenRounds = Math.max(1, totalDays / totalRounds);

        for (int round = 0; round < totalRounds; round++) {
            LocalDate matchDate = startDate.plusDays(round * daysBetweenRounds);

            for (int i = 0; i < matchesPerRound; i++) {
                Team home = rotatedTeams.get(i);
                Team away = rotatedTeams.get(n - 1 - i);

                if (home.getId() == -1L || away.getId() == -1L) continue; // descansa

                // En ida y vuelta, invertir local/visitante en la segunda ronda
                if (homeAndAway && round >= (n - 1)) {
                    Team temp = home;
                    home = away;
                    away = temp;
                }

                Match matchBD = Match.builder()
                        .stadium(home.getMainStadium())
                        .matchDate(matchDate.atTime(15, 0).atOffset(ZoneOffset.UTC))
                        .homeTeam(home)
                        .awayTeam(away)
                        .tournament(tournament)
                        .build();

                Match savedMatch = matchRepository.save(matchBD);

                matches.add(GetMatchResponse.builder()
                        .tournamentId(savedMatch.getTournament().getId())
                        .matchId(Long.valueOf(savedMatch.getId())) // se asigna al guardar en BD
                        .homeTeam(savedMatch.getHomeTeam().getName())
                        .homeTeamId(savedMatch.getHomeTeam().getId())
                        .awayTeam(savedMatch.getAwayTeam().getName())
                        .awayTeamId(savedMatch.getAwayTeam().getId())
                        .matchDateTIme(savedMatch.getMatchDate())
                        .stadium(savedMatch.getStadium()) // o null si no aplica
                        .goalsHomeTeam(0)
                        .goalsAwayTeam(0)
                        .refereeId(null)
                        .build());
            }

            // Rotación de equipos (round-robin)
            List<Team> temp = new ArrayList<>(rotatedTeams);
            Team fixed = temp.remove(0);
            Team last = temp.remove(temp.size() - 1);
            temp.add(0, last);
            temp.add(0, fixed);
            rotatedTeams = temp;
        }

        return matches;
    }

    @Override
    @Transactional
    public GetMatchResponse getMatchById(Long matchId, Long tournamentId) {

        Optional<Match> m = matchRepository.findById(matchId);
        if (m.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match id: " + matchId + " not found");
        }
        Match match = m.get();

        if (!Objects.equals(match.getTournament().getId(), tournamentId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Match with id: " + matchId + " does not belong to the tournament");
        }

        return GetMatchResponse.builder()
                .matchId((long) match.getId())
                .tournamentId(match.getTournament().getId())
                .tournamentName(match.getTournament().getName())
                .homeTeamId(match.getHomeTeam().getId())
                .homeTeam(match.getHomeTeam().getName())
                .awayTeamId(match.getAwayTeam().getId())
                .awayTeam(match.getAwayTeam().getName())
                .matchDateTIme(match.getMatchDate())
                .stadium(match.getStadium())
                .goalsHomeTeam(match.getHomeGoals())
                .goalsAwayTeam(match.getAwayGoals())
                .refereeId(match.getReferee().getId())
                .refereeName(match.getReferee().getName())
                .build();
    }

    @Override
    public Match matchById(Long matchId, Long tournamentId) {

        Optional<Match> m = matchRepository.findById(matchId);

        if(m.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found");
        }

        if (!Objects.equals(m.get().getTournament().getId(), tournamentId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Match with id: " + matchId + " does not belong to the tournament");
        }

        return m.get();
    }


}
