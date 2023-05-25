package api.v1.domain.reservation;

import api.v1.domain.station.StationRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final StationRepository stationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, StationRepository stationRepository) {
        this.reservationRepository = reservationRepository;
        this.stationRepository = stationRepository;
    }

    @Override
    public Flux<Reservation> getAllReservations(Integer pageNumber, Integer pageSize) {
        return reservationRepository.findAll()
                .skip((long) (pageNumber-1) * pageSize)
                .take(pageSize);
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
    public Mono<Reservation> reserveChargingSlot(Integer stationId, CreateReservation createReservation) {
        LocalDateTime now = LocalDateTime.now();
        if (!createReservation.getStartTime().isAfter(now) || !createReservation.getEndTime().isAfter(now)) {
            return Mono.error(new IllegalArgumentException("Both start and end times must be in the future"));
        }

        return stationRepository.findByStationIdWithReservation(createReservation.getStationId())
                .flatMap(stationReservation -> {
                    Optional<Reservation> conflictingReservation = stationReservation.getReservations().stream()
                            .filter(reservation -> timeValidate(createReservation, reservation))
                            .findAny();

                    return conflictingReservation.isPresent() ?
                            Mono.error(new IllegalArgumentException("Conflicting reservation exists")) :
                            reservationRepository.save(new Reservation(stationId, createReservation));
                });
    }

    private boolean timeValidate(CreateReservation r1, Reservation r2) {
        return !r1.getStartTime().isBefore(r2.getStartTime()) && !r1.getEndTime().isAfter(r2.getEndTime());
    }

    @Override
    public Mono<Reservation> cancelReservation(Integer stationId, String reservationId) {
        return stationRepository.findByStationIdWithReservation(stationId)
                .flatMap(stationReservation -> reservationRepository.deleteByStationIdAndReservationId(stationId, reservationId)
                        .switchIfEmpty(Mono.error(new ChangeSetPersister.NotFoundException())))
                .switchIfEmpty(Mono.error(new ChangeSetPersister.NotFoundException()));
    }
}
