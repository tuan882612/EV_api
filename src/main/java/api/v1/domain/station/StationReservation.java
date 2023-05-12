package api.v1.domain.station;

import api.v1.domain.reservation.Reservation;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "station")
public class StationReservation extends Station {
    private List<Reservation> reservations;
}
