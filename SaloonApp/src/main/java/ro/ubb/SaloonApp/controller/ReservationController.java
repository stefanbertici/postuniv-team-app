package ro.ubb.SaloonApp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ro.ubb.SaloonApp.dto.ReservationDto;
import ro.ubb.SaloonApp.dto.ReservationUpdateDto;
import ro.ubb.SaloonApp.dto.ReservationViewDto;
import ro.ubb.SaloonApp.service.ReservationService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping()
    public ResponseEntity<List<ReservationViewDto>> readAll() {
        List<ReservationViewDto> reservationViewDtos = reservationService.readAll();

        return new ResponseEntity<>(reservationViewDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<ReservationViewDto> getReservationById(@PathVariable Integer id) {
        ReservationViewDto reservationViewDto = reservationService.readOne(id);

        return new ResponseEntity<>(reservationViewDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<ReservationViewDto> deleteReservation(@PathVariable Integer id) {
        ReservationViewDto reservationViewDto = reservationService.delete(id);

        return new ResponseEntity<>(reservationViewDto, HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<ReservationViewDto> saveReservation(@RequestBody ReservationDto reservationDto) {
        ReservationViewDto reservationViewDto = reservationService.create(reservationDto);

        return new ResponseEntity<>(reservationViewDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<ReservationViewDto> updateReservation(@PathVariable Integer id, @RequestBody ReservationUpdateDto reservationUpdateDto) {
        ReservationViewDto reservationViewDto = reservationService.update(id, reservationUpdateDto);

        return new ResponseEntity<>(reservationViewDto, HttpStatus.OK);
    }

    @GetMapping("/{id}/accept")
    ResponseEntity<String> acceptReservation(@PathVariable Integer id, Authentication authentication) {
        String response = reservationService.accept(id, authentication);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/complete")
    ResponseEntity<String> completeReservation(@PathVariable Integer id, Authentication authentication) {
        String response = reservationService.complete(id, authentication);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/cancel")
    ResponseEntity<String> cancelReservation(@PathVariable Integer id) {
        String response = reservationService.cancel(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
