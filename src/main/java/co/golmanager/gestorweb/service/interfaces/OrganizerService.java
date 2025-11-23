package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.entity.User;
import co.golmanager.gestorweb.presentation.dto.admin.OrganizersResponse;
import co.golmanager.gestorweb.presentation.dto.organizer.GetDashboardOrganizerResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrganizerService {
    List<OrganizersResponse> getOrganizers(String email);
    GetDashboardOrganizerResponse getDashboardOrganizer(Authentication authentication);
}


