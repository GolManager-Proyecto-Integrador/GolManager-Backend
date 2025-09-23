package co.golmanager.gestorweb.service;

import co.golmanager.gestorweb.controller.models.requests.CreateTournamentRequest;
import co.golmanager.gestorweb.controller.models.responses.CreateTournamentResponse;
import co.golmanager.gestorweb.controller.models.responses.TournamentDeleteResponse;
import co.golmanager.gestorweb.controller.models.responses.TournamentDetailResponse;
import co.golmanager.gestorweb.controller.models.responses.TournamentSummaryResponse;
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

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final RefereeRepository refereeRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public List<TournamentSummaryResponse> getAllTournamentsById(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        List<Tournament> tournaments = tournamentRepository.findByUserId(user.getId());

        return tournaments.stream().map(t -> TournamentSummaryResponse.builder()
                        .id(t.getId())
                        .name(t.getName())
                        .startDate(t.getStartDate())
                        .endDate(t.getEndDate())
                        .format(TournamentFormat.valueOf(t.getFormat().name()))
                        .numberOfTeams(t.getNumberOfTeams())
                        .build())
                .toList();
    }

    @Transactional
    @Override
    public TournamentDetailResponse getTournamentById(String tournamentId, String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Long id;
        try {
            id = Long.parseLong(tournamentId);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid tournament ID format");
        }

        Tournament t = tournamentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found"));

        if (!t.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized to view this tournament");
        }

        List<Long> refereeIds = t.getReferees().stream()
                .map(Referee::getId)
                .toList();

        return TournamentDetailResponse.builder()
                .id(t.getId())
                .name(t.getName())
                .startDate(t.getStartDate())
                .endDate(t.getEndDate())
                .format(t.getFormat())
                .homeAndAway(t.isHomeAndAway())
                .numberOfTeams(t.getNumberOfTeams())
                .yellowCardsSuspension(t.getYellowCardsSuspension())
                .refereeIds(refereeIds)
                .build();
    }


    @Transactional
    public CreateTournamentResponse createTournament(CreateTournamentRequest req, String email) {

        //Validacion de fechas
        if (req.getEndDate().isBefore(req.getStartDate())) {
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

        if (email != null) {
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

    public TournamentDetailResponse updateTournament
            (Long tournamentId, CreateTournamentRequest request, String email) {
        // Implementation for updating a tournament would go here
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Long id;
        try {
            id = Long.parseLong(String.valueOf(tournamentId));
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid tournament ID format");
        }
        Tournament t = tournamentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found"));

        if (!t.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized to update this tournament");
        }

        //Si el formato cambia, validar que este en la lista de formatos permitidos
        if (request.getFormat() != null ) {
            List<String> allowedFormats = List.of("LEAGUE", "DIRECT_ELIMINATION", "PLAY_OFF");
            if (!allowedFormats.contains(request.getFormat().name())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid format. Allowed formats are: " + allowedFormats);
            }
        }

        //Validacion de fechas
        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "endDate must be after startDate");
        }

        t.setName(request.getName());
        t.setStartDate(request.getStartDate());
        t.setEndDate(request.getEndDate());
        t.setFormat(request.getFormat());
        t.setHomeAndAway(request.isHomeAndAway());
        t.setNumberOfTeams(request.getNumberOfTeams());
        t.setYellowCardsSuspension(request.getYellowCardsSuspension());

        var saved = tournamentRepository.save(t);

        return TournamentDetailResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .startDate(saved.getStartDate())
                .endDate(saved.getEndDate())
                .format(saved.getFormat())
                .homeAndAway(saved.isHomeAndAway())
                .numberOfTeams(saved.getNumberOfTeams())
                .yellowCardsSuspension(saved.getYellowCardsSuspension())
                .refereeIds(request.getRefereeIds())
                .build();
    }

    public TournamentDeleteResponse deleteTournament(Long tournamentId, String email) {

        // Implementation for deleting a tournament would go here
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Long id;
        try {
            id = Long.parseLong(String.valueOf(tournamentId));
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid tournament ID format");
        }
        Tournament t = tournamentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found"));

        if (!t.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized to delete this tournament");
        }

        Long logUser = t.getId();
        String logName = t.getName();

        tournamentRepository.delete(t);
        LocalDateTime deleteTime = LocalDateTime.now();

        return TournamentDeleteResponse.builder()
                .tournamentId(logUser)
                .name(logName)
                .deletionDate(deleteTime)
                .build();
    }
}
