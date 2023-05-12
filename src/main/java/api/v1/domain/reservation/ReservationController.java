package api.v1.domain.reservation;

import api.v1.domain.station.StationService;
import api.v1.utility.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
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
    public Mono<ResponseEntity<ApiResponse<List<Reservation>>>> getAllReservations() {
        return reservationService.getAllReservations()
                .collectList()
                .map(reservations -> ResponseEntity
                        .ok(new ApiResponse<>(HttpStatus.OK.value(), "All reservations", reservations)));
    }

    @GetMapping("/{stationId}/reservations")
    public Mono<ResponseEntity<ApiResponse<List<Reservation>>>> getAllReservationsPerStation(@PathVariable Integer stationId) {
        return stationService.getStationByStationIdWithReservation(stationId)
                .map(stationReservation -> ResponseEntity
                        .ok(new ApiResponse<>(HttpStatus.OK.value(), "All reservations for station", stationReservation.getReservations())))
                .switchIfEmpty(Mono.just(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Station not found", null))));
    }


    @GetMapping("/{stationId}/reservations/reservation")
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
                .map(reservation -> ResponseEntity
                        .ok(new ApiResponse<>(HttpStatus.OK.value(), "Reservation found", reservation)))
                .defaultIfEmpty(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "No reservations found ", null)));
    }


    @PostMapping("/{stationId}/reservations")
    public Flux<ResponseEntity<ApiResponse<Reservation>>> reserveChargingSlot(@PathVariable Integer stationId) {
        return null;
    }

    @DeleteMapping("/{stationId}/reservations/{reservationId}")
    public Mono<ResponseEntity<ApiResponse<Void>>> cancelReservation(@PathVariable Integer stationId, @PathVariable Integer reservationId) {
        return null;
    }
}
