package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.presentation.dto.referee.RefereeListResponse;
import co.golmanager.gestorweb.entity.Referee;
import co.golmanager.gestorweb.repository.RefereeRepository;
import co.golmanager.gestorweb.service.interfaces.RefereeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefereeServiceImpl implements RefereeService {

    private final RefereeRepository refereeRepository;


    @Override
    @Transactional
    public RefereeListResponse listReferees(String email) {
        Optional<List<Referee>> listReferee = Optional.of(refereeRepository.findAll());
        if (listReferee.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The list of referees is empty.");
        }
        log.info("listReferee={}", listReferee);
        return RefereeListResponse.builder()
                .referees(listReferee.get())
                .build();

    }

    public Referee getReferee(Long refereeId) {
        Optional<Referee> referee = refereeRepository.findById(refereeId);
        if (referee.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Referee: " + refereeId + " not found");
        }
        return referee.get();

    }

}
