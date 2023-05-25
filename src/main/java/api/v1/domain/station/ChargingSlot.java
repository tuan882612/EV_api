package api.v1.domain.station;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class ChargingSlot {
    @Field("slot_id")
    @JsonProperty("slot_id")
    private Integer slot_id;
    private String type;
    private String status;
}
