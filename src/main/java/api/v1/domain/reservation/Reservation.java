package api.v1.domain.reservation;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "reservation")
public class Reservation extends CreateReservation {
    private String _id;
    private String reservation_id;
}

