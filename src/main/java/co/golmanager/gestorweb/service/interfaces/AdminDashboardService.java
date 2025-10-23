package co.golmanager.gestorweb.service.interfaces;

import co.golmanager.gestorweb.presentation.dto.admin.AdminDashboardResponse;
import org.springframework.stereotype.Service;

@Service
public interface AdminDashboardService {

    AdminDashboardResponse getDashboardAdminInfoResponse (String email);
    int getNumberOfOrganizers();
    int getNumberOfTournaments();
    int getNumberOfTeams();
}
