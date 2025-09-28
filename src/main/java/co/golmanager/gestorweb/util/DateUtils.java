package co.golmanager.gestorweb.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;


public class DateUtils {

    public static void dateValidation (LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "endDate must be after startDate");
        }
    }

    public static OffsetDateTime LocalToOffsetDate (LocalDate date) {
        ZoneId zone = ZoneId.of("America/Bogota");
        return date.atStartOfDay(zone).toOffsetDateTime();
    }

}
