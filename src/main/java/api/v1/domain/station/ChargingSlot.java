package api.v1.domain.station;

import lombok.Data;

@Data
public class ChargingSlot {
    private Integer slot_id;
    private String type;
    private String status;
}
