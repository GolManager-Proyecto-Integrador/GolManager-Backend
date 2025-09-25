package co.golmanager.gestorweb.util;

public class ValidationUtils {

    public static void idAuthorizationValidation (Long idOwner, Long idRequester) {
        if (!idOwner.equals(idRequester)) {
            throw new RuntimeException("Unauthorized to realize this action");
        }
    }

}
