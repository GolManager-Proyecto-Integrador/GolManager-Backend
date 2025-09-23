package co.golmanager.gestorweb.service;

import co.golmanager.gestorweb.controller.models.responses.RefereeListResponse;
import org.springframework.stereotype.Service;

@Service
public interface RefereeService {

    RefereeListResponse listReferees(String email);
}
