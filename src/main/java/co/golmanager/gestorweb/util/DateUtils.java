package co.golmanager.gestorweb.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;


public class DateUtils {

    public static void dateValidation (LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "endDate must be after startDate");
        }
    }

}
