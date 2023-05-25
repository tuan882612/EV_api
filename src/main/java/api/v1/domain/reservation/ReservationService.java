package api.v1.domain.reservation;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReservationService {
    Flux<Reservation> getAllReservations(Integer pageNumber, Integer pageSize);
    Mono<Reservation> getReservationByReservationId(Integer stationId, String reservationId);
    Mono<Reservation> getReservationByUsername(Integer stationId, String username);
    Mono<Reservation> reserveChargingSlot(Integer stationId, CreateReservation createReservation);
    Mono<Reservation> cancelReservation(Integer stationId, String reservationId);
}
