package api.v1.domain.station;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "station")
public class Station {
    @Id
    private String _id;
    private Integer station_id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private double latitude;
    private double longitude;
    private List<ChargingSlot> charging_slots;
}
