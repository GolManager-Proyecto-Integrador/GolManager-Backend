package co.golmanager.gestorweb.service;

import co.golmanager.gestorweb.controller.models.requests.CreateTournamentRequest;
import co.golmanager.gestorweb.controller.models.responses.CreateTournamentResponse;
import co.golmanager.gestorweb.entity.Referee;
import co.golmanager.gestorweb.entity.Tournament;
import co.golmanager.gestorweb.enums.TournamentFormat;
import co.golmanager.gestorweb.repository.RefereeRepository;
import co.golmanager.gestorweb.repository.TournamentRepository;
import co.golmanager.gestorweb.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final RefereeRepository refereeRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateTournamentResponse createTournament(CreateTournamentRequest req, String email) {

        //Validacion de fechas
        if (req.getEndDate().isBefore(req.getEndDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "endDate must be after startDate");
        }

        Tournament t = Tournament.builder()
                .name(req.getName())
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .format(req.getFormat())
                .homeAndAway(req.isHomeAndAway())
                .numberOfTeams(req.getNumberOfTeams())
                .yellowCardsSuspension(req.getYellowCardsSuspension())
                .build();

        if (email != null){
            var user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
            t.setUser(user);
        }

        if (req.getRefereeIds() != null && !req.getRefereeIds().isEmpty()) {
            List<Referee> referees = refereeRepository.findAllById(req.getRefereeIds());
            if (referees.size() != req.getRefereeIds().size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One or more referees not found");
            }
            t.setReferees(referees);
        }

        Tournament savedTournament = tournamentRepository.save(t);

        return CreateTournamentResponse.builder()
                .id(savedTournament.getId())
                .name(savedTournament.getName())
                .startDate(savedTournament.getStartDate())
                .endDate(savedTournament.getEndDate())
                .format(savedTournament.getFormat().name())
                .homeAndAway(savedTournament.isHomeAndAway())
                .numberOfTeams(savedTournament.getNumberOfTeams())
                .yellowCardsSuspension(savedTournament.getYellowCardsSuspension())
                .refereeIds(req.getRefereeIds())
                .build();
    }
}
