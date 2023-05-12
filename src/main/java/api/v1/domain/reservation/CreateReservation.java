package api.v1.domain.reservation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservation {
    private Integer station_id;
    private Integer slot_id;
    private String email;
    private String username;
    private String start_time;
    private String end_time;
}
