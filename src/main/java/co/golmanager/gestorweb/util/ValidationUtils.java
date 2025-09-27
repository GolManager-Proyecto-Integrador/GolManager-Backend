package co.golmanager.gestorweb.util;

import co.golmanager.gestorweb.entity.Tournament;
import co.golmanager.gestorweb.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidationUtils {

    UserService userService;

    public static void idAuthorizationValidation (Long idOwner, Long idRequester) {
        if (!idOwner.equals(idRequester)) {
            throw new RuntimeException("Unauthorized to realize this action");
        }
    }
}
