package co.golmanager.gestorweb.presentation.dto.generalDto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeneralErrorResponse {
    private int status;
    private String error;
    private String message;
}
