package api.v1.domain.station;

import api.v1.utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stations")
public class StationController {
    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("")
    public Mono<ResponseEntity<ApiResponse<List<Station>>>> AllStationsHandler() {
        return stationService.getAllStations()
                .collectList()
                .map(stations -> ResponseEntity
                        .ok(new ApiResponse<>(HttpStatus.OK.value(), "All stations", stations)));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ApiResponse<Station>>> StationByIdHandler(@PathVariable Integer id) {
        return stationService.getStationByStationId(id)
                .map(station -> ResponseEntity
                        .ok(new ApiResponse<>(HttpStatus.OK.value(), "Station found", station)))
                .defaultIfEmpty(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Station not found", null)));
    }

}
