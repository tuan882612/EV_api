package api.v1.domain.station;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface StationRepository extends ReactiveMongoRepository<Station, String> {
    @Query("{ 'station_id' : ?0 }")
    Mono<Station> findByStationId(Integer stationId);

    @Aggregation(pipeline = {
            "{ $match: { station_id: ?0 } }",
            "{ $lookup: { from: 'reservation', localField: 'station_id', foreignField: 'station_id',as: 'reservations' } }"
    })
    Mono<StationReservation> findByStationIdWithReservation(Integer stationId);
}
