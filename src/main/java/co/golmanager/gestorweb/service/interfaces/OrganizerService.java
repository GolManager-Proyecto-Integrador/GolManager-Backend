package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.entity.User;
import co.golmanager.gestorweb.presentation.dto.admin.OrganizersResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrganizerService {
    List<OrganizersResponse> getOrganizers(String email);

}


