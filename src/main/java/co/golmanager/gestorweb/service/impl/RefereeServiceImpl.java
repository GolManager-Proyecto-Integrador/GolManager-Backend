package co.golmanager.gestorweb.service.impl;

import co.golmanager.gestorweb.entity.Tournament;
import co.golmanager.gestorweb.presentation.dto.referee.RefereeListResponse;
import co.golmanager.gestorweb.entity.Referee;
import co.golmanager.gestorweb.repository.RefereeRepository;
import co.golmanager.gestorweb.repository.UserRepository;
import co.golmanager.gestorweb.service.interfaces.RefereeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefereeServiceImpl implements RefereeService {

    private final RefereeRepository refereeRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public RefereeListResponse listReferees(String email) {
        List<Referee> listReferee = refereeRepository.findAll();
        return RefereeListResponse.builder()
                .referees(listReferee)
                .build();

    }

    public Referee getReferee(Long refereeId) {
        return refereeRepository.findById(refereeId)
                .orElseThrow(() -> new EntityNotFoundException("referee not found with id: " + refereeId));

    }

}
