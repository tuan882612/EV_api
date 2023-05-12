package api.v1.domain.reservation;

import api.v1.domain.station.StationRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final StationRepository stationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, StationRepository stationRepository) {
        this.reservationRepository = reservationRepository;
        this.stationRepository = stationRepository;
    }

    @Override
    public Flux<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Mono<Reservation> getReservationByReservationId(Integer stationId, String reservationId) {
        return reservationRepository.findByReservationId(stationId, reservationId);
    }

    @Override
    public Mono<Reservation> getReservationByUsername(Integer stationId, String username) {
        return reservationRepository.findByUsername(stationId, username);
    }

    @Override
    public Mono<Reservation> reserveChargingSlot(Integer stationId) {
        return null;
    }

    @Override
    public Mono<Reservation> cancelReservation(Integer stationId, Integer reservationId) {
        return null;
    }
}
