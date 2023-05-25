package api.v1.domain.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservation {
    @Field("station_id")
    @JsonProperty("station_id")
    private Integer stationId;
    @Field("slot_id")
    @JsonProperty("slot_id")
    private Integer slotId;
    private String email;
    private String username;
    @Field("start_time")
    @JsonProperty("start_time")
    private LocalDateTime startTime;
    @Field("end_time")
    @JsonProperty("end_time")
    private LocalDateTime endTime;
}
