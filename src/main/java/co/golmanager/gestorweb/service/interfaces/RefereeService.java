package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.controller.dto.responses.RefereeListResponse;
import org.springframework.stereotype.Service;

@Service
public interface RefereeService {

    RefereeListResponse listReferees(String email);
}
