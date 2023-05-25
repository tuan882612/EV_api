package api.v1.domain.reservation;

import api.v1.domain.station.StationService;
import api.v1.utility.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stations")
public class ReservationController {
    private final ReservationService reservationService;
    private final StationService stationService;

    public ReservationController(ReservationService reservationService, StationService stationService) {
        this.reservationService = reservationService;
        this.stationService = stationService;
    }

    @GetMapping("/reservations")
    public Mono<ResponseEntity<ApiResponse<List<Reservation>>>> getAllReservations(
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize
    ) {
        return reservationService.getAllReservations(pageNumber, pageSize)
                .collectList()
                .map(reservations -> ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "All reservations", reservations)))
                .switchIfEmpty(Mono.error(new ChangeSetPersister.NotFoundException()));
    }

    @GetMapping("station/{stationId}/reservations")
    public Mono<ResponseEntity<ApiResponse<List<Reservation>>>> getAllReservationsPerStation(
            @PathVariable Integer stationId,
            @RequestParam Integer pageNumber,
            @RequestParam Integer pageSize
    ) {
        return stationService.getStationByStationIdWithReservation(stationId)
                .map(stationReservation -> {
                    int startIndex = (pageNumber - 1) * pageSize;
                    int endIndex = Math.min(startIndex + pageSize, stationReservation.getReservations().size());

                    return ResponseEntity
                            .ok(new ApiResponse<>(
                                    HttpStatus.OK.value(),
                                    "All reservations for station",
                                    stationReservation.getReservations().subList(startIndex, endIndex)));
                })
                .switchIfEmpty(Mono.error(new ChangeSetPersister.NotFoundException()));
    }


    @GetMapping("station/{stationId}/reservations/reservation")
    public Mono<ResponseEntity<ApiResponse<Reservation>>> getReservationDetails(
            @PathVariable Integer stationId,
            @RequestParam(required = false) String reservationId,
            @RequestParam(required = false, defaultValue = "") String username
    ) {
        if (reservationId == null && (username == null || username.isEmpty())) {
            return Mono.just(ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Please provide either reservationId or username", null)));
        }
        return ((username != null && !username.isEmpty()) ?
                        reservationService.getReservationByUsername(stationId, username) :
                        reservationService.getReservationByReservationId(stationId, reservationId))
                .map(reservation -> ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Reservation found", reservation)))
                .switchIfEmpty(Mono.error(new ChangeSetPersister.NotFoundException()));
    }

    @PostMapping("station/{stationId}/reservations")
    public Mono<ResponseEntity<ApiResponse<Reservation>>> reserveChargingSlot(
            @PathVariable Integer stationId,
            @Valid @RequestBody CreateReservation createReservation
    ) {
        return reservationService.reserveChargingSlot(stationId, createReservation)
                .map(reservation -> ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Reservation created", reservation)))
                .switchIfEmpty(Mono.error(new ChangeSetPersister.NotFoundException()));
    }

    @DeleteMapping("station/{stationId}/reservations/{reservationId}")
    public Mono<ResponseEntity<ApiResponse<Object>>> cancelReservation(@PathVariable Integer stationId, @PathVariable String reservationId) {
        return reservationService.cancelReservation(stationId, reservationId)
                .map(reservation -> ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Reservation cancelled", null)))
                .switchIfEmpty(Mono.error(new ChangeSetPersister.NotFoundException()));
    }
}
