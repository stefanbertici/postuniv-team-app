package ro.ubb.SaloonApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.ubb.SaloonApp.dto.ReservationDto;
import ro.ubb.SaloonApp.dto.ReservationUpdateDto;
import ro.ubb.SaloonApp.model.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static ro.ubb.SaloonApp.constant.Availability.*;

@RequiredArgsConstructor
@Service
public class AvailabilityService {

    public List<LocalTime> filterAvailabilityBasedOnReservations(List<Reservation> reservations) {
        List<LocalTime> availability = constructAvailabilities(
                DEFAULT_START_HOUR.value, DEFAULT_START_MINUTE.value, DEFAULT_AVAILABILITY_BLOCKS.value);
        reservations.forEach(reservation -> availability.removeIf(checkIfTimeIsReserved(reservation)));

        return availability;
    }

    private List<LocalTime> constructAvailabilities(int startHour, int startMinute, int numOfAvailabilityBlocks ) {
        List<LocalTime> defaultAvailability = new ArrayList<>();

        LocalTime time = LocalTime.of(startHour, startMinute, 0);

        for (int i = 0; i < numOfAvailabilityBlocks; i++) {
            defaultAvailability.add(time);
            time = time.plusMinutes(30);
        }

        return defaultAvailability;
    }

    private static Predicate<LocalTime> checkIfTimeIsReserved(Reservation reservation) {
        return availability -> availability.equals(reservation.getHour()) || (availability.isAfter(reservation.getHour()) &&
                availability.isBefore(reservation.getHour().plusMinutes(
                        30L * reservation.getBeautyService().getNumOfAvailabilityBlocks())));
    }

    public void checkIfReservationPossibleForCreate(ReservationDto reservationDto, int numOfAvailabilityBlocks, List<Reservation> reservationsOfUser) {
        List<LocalTime> reservationList = constructAvailabilities(
                reservationDto.hour().getHour(), reservationDto.hour().getMinute(), numOfAvailabilityBlocks);
        List<LocalTime> availabilitiesOfUser = filterAvailabilityBasedOnReservations(reservationsOfUser);

        if(!availabilitiesOfUser.containsAll(reservationList)) {
            throw new IllegalArgumentException("There is no availability for your selected time");
        }
    }

    public void checkIfReservationPossibleForUpdate(Reservation originalReservation, ReservationUpdateDto reservationUpdateDto, List<Reservation> reservationsOfUser) {
        LocalTime originalTime = originalReservation.getHour();
        LocalDate originalDate = originalReservation.getDate();
        int numOfAvailabilityBlocks = originalReservation.getBeautyService().getNumOfAvailabilityBlocks();

        List<LocalTime> updatedReservationList = constructAvailabilities(
            reservationUpdateDto.hour().getHour(), reservationUpdateDto.hour().getMinute(), numOfAvailabilityBlocks);
        List<LocalTime> availabilitiesOfUser = filterAvailabilityBasedOnReservations(reservationsOfUser);

        boolean isDateModified = !originalDate.equals(reservationUpdateDto.date());
        if(!isDateModified) {
            List<LocalTime> originalReservationList = constructAvailabilities(
                    originalTime.getHour(), originalTime.getMinute(), numOfAvailabilityBlocks);
            availabilitiesOfUser.addAll(originalReservationList);
        }

        if(!availabilitiesOfUser.containsAll(updatedReservationList)) {
            throw new IllegalArgumentException("There is no availability for your selected time");
        }
    }
}
