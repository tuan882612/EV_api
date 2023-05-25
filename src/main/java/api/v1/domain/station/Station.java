package api.v1.domain.station;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "station")
public class Station {
    @Id
    @Field("_id")
    @JsonProperty("_id")
    private String id;
    @Field("station_id")
    @JsonProperty("station_id")
    private Integer stationId;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private double latitude;
    private double longitude;
    @Field("charging_slots")
    @JsonProperty("charging_slots")
    private List<ChargingSlot> chargingSlots;
}
