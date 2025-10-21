package co.golmanager.gestorweb.util;

import co.golmanager.gestorweb.entity.User;
import co.golmanager.gestorweb.enums.Role;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidationUtils {

    public static void  idAuthorizationValidation (Long idOwner, Long idRequester) {
        if (!idOwner.equals(idRequester)) {
            throw new RuntimeException("Unauthorized to realize this action");
        }
    }

    public static void roleAuthorizationValidation (User user) {
        if (!user.getRole().equals(Role.ADMIN)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized to realize this action");
        }
    }

}
