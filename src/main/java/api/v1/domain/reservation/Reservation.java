package api.v1.domain.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "reservation")
public class Reservation extends CreateReservation {
    @Id
    private String Id;
    @Field("reservation_id")
    @JsonProperty("reservation_id")
    private String reservationId;

    public Reservation(Integer stationId, CreateReservation createReservation) {
        this.setReservationId(UUID.randomUUID().toString());
        this.setStationId(stationId);
        this.setSlotId(createReservation.getSlotId());
        this.setEmail(createReservation.getEmail());
        this.setUsername(createReservation.getUsername());
        this.setStartTime(createReservation.getStartTime());
        this.setEndTime(createReservation.getEndTime());
    }
}
