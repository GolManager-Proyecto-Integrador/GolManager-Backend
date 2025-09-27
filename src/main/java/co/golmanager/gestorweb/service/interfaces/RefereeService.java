package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.presentation.dto.referee.RefereeListResponse;
import org.springframework.stereotype.Service;

@Service
public interface RefereeService {

    RefereeListResponse listReferees(String email);
}
