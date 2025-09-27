package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.presentation.dto.tournament.CreateTournamentRequest;
import co.golmanager.gestorweb.presentation.dto.tournament.CreateTournamentResponse;
import co.golmanager.gestorweb.presentation.dto.tournament.TournamentDeleteResponse;
import co.golmanager.gestorweb.presentation.dto.tournament.TournamentDetailResponse;
import co.golmanager.gestorweb.presentation.dto.tournament.TournamentSummaryResponse;
import co.golmanager.gestorweb.entity.Referee;
import co.golmanager.gestorweb.entity.Tournament;
import co.golmanager.gestorweb.enums.TournamentFormat;
import co.golmanager.gestorweb.repository.RefereeRepository;
import co.golmanager.gestorweb.repository.TournamentRepository;
import co.golmanager.gestorweb.repository.UserRepository;
import co.golmanager.gestorweb.service.interfaces.TournamentService;
import co.golmanager.gestorweb.service.interfaces.UserService;
import co.golmanager.gestorweb.util.DateUtils;
import co.golmanager.gestorweb.util.ValidationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final RefereeRepository refereeRepository;
    private final UserRepository userRepository;

    @Autowired
    private final UserService userService;

    @Transactional
    @Override
    public List<TournamentSummaryResponse> getAllTournamentsById(String email) {

        //Comprueba que el id del usuario exista
        var user = userService.getUserByEmail(email);

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
    public Tournament getTournamentById(String email, Long tournamentId) {
        var user = userService.getUserByEmail(email);
        Tournament t = idTournamentValidation(tournamentId);
        ValidationUtils.idAuthorizationValidation(user.getId(), t.getUser().getId());

        return t;
    }

    @Transactional
    @Override
    public TournamentDetailResponse getTournamentResponseById(String tournamentId, String email) {

        //Comprueba que el id del usuario exista
        var user = userService.getUserByEmail(email);

        Long id;
        try {
            id = Long.parseLong(tournamentId);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid tournament ID format");
        }

        Tournament t = idTournamentValidation(id);
        ValidationUtils.idAuthorizationValidation(t.getUser().getId(), user.getId());

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

        DateUtils.dateValidation(req.getStartDate(), req.getEndDate()) ;
        OffsetDateTime dateCreated =  OffsetDateTime.now();
        Tournament t = Tournament.builder()
                .name(req.getName())
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .format(req.getFormat())
                .homeAndAway(req.isHomeAndAway())
                .numberOfTeams(req.getNumberOfTeams())
                .yellowCardsSuspension(req.getYellowCardsSuspension())
                .dateCreated(dateCreated)
                .build();

        if (email != null) {
            var user = userService.getUserByEmail(email);
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

        var user = userService.getUserByEmail(email);
        Long id = idTournamentFormatValidation(tournamentId);
        Tournament t = idTournamentValidation(id);
        ValidationUtils.idAuthorizationValidation(t.getUser().getId(), user.getId());

        //Si el formato cambia, validar que este en la lista de formatos permitidos
        formatValidation(request.getFormat());

        DateUtils.dateValidation(request.getStartDate(), request.getEndDate()) ;

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

        var user = userService.getUserByEmail(email);
        Long id = idTournamentFormatValidation(tournamentId);
        Tournament t = idTournamentValidation(id);

        ValidationUtils.idAuthorizationValidation(t.getUser().getId(),user.getId());

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

    private void formatValidation (TournamentFormat format) {
        if (format != null ) {
            List<String> allowedFormats = List.of("LEAGUE", "DIRECT_ELIMINATION", "PLAY_OFF");
            if (!allowedFormats.contains(format.name())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid format. Allowed formats are: " + allowedFormats);
            }
        }
    }

    private Tournament idTournamentValidation (Long id) {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found"));
    }

    private Long idTournamentFormatValidation(Long tournamentId) {
        Long id;
        try {
             id = Long.parseLong(String.valueOf(tournamentId));
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid tournament ID format");
        }
        return id;
    }
}
