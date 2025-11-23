package co.golmanager.gestorweb.presentation.dto.team;

import co.golmanager.gestorweb.enums.TeamCategory;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class    TeamDetailsResponse {
    private Long teamId;
    private String name;
    private String coach;
    private TeamCategory category;
    private String mainStadium;
    private String secondaryStadium;
    private OffsetDateTime dateCreated;
}
