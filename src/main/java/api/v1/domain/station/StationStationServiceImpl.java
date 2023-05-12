package api.v1.domain.station;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StationStationServiceImpl implements StationService {
    private final StationRepository stationRepository;

    public StationStationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }
    @Override
    public Flux<Station> getAllStations() {
        return stationRepository.findAll();
    }

    @Override
    public Mono<Station> getStationByStationId(Integer stationId) {
        return stationRepository.findByStationId(stationId);
    }

    @Override
    public Mono<StationReservation> getStationByStationIdWithReservation(Integer stationId) {
        return stationRepository.findByStationIdWithReservation(stationId);
    }
}
