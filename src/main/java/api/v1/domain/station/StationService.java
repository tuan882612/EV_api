package api.v1.domain.station;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StationService {
    Flux<Station> getAllStations();
    Mono<Station> getStationByStationId(Integer stationId);
    Mono<StationReservation> getStationByStationIdWithReservation(Integer stationId);
}
