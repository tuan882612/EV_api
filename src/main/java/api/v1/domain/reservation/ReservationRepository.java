package api.v1.domain.reservation;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ReservationRepository extends ReactiveMongoRepository<Reservation, String> {
    @Query("{ 'username' : ?1, 'station_id' : ?0 }")
    Mono<Reservation> findByUsername(Integer stationId, String username);

    @Query("{ 'reservation_id' : ?1, 'station_id' : ?0 }")
    Mono<Reservation> findByReservationId(Integer stationId, String reservationId);

    Mono<Reservation> deleteByStationIdAndReservationId(Integer stationId, String reservationId);
}
