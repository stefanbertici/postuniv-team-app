package ro.ubb.SaloonApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.ubb.SaloonApp.dto.ReservationDto;
import ro.ubb.SaloonApp.dto.ReservationUpdateDto;
import ro.ubb.SaloonApp.model.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static ro.ubb.SaloonApp.constant.Availability.*;

@RequiredArgsConstructor
@Service
public class AvailabilityService {

    public List<LocalTime> filterAvailabilityBasedOnReservations(List<Reservation> reservations, boolean isToday) {
        List<LocalTime> availabilityBlocks = constructTimeBlocks(
                DEFAULT_START_HOUR.value, DEFAULT_START_MINUTE.value, DEFAULT_AVAILABILITY_BLOCKS.value);
        reservations.forEach(reservation -> availabilityBlocks.removeIf(checkIfTimeIsReserved(reservation)));

        if (isToday) {
            return availabilityBlocks.stream()
                    .filter(a -> a.isAfter(LocalTime.now()))
                    .collect(Collectors.toList());
        } else {
            return availabilityBlocks;
        }
    }

    private List<LocalTime> constructTimeBlocks(int startHour, int startMinute, int numOfAvailabilityBlocks ) {
        List<LocalTime> defaultAvailability = new ArrayList<>();

        LocalTime time = LocalTime.of(startHour, startMinute, 0);

        for (int i = 0; i < numOfAvailabilityBlocks; i++) {
            defaultAvailability.add(time);
            time = time.plusMinutes(30);
        }

        return defaultAvailability;
    }

    private Predicate<LocalTime> checkIfTimeIsReserved(Reservation reservation) {
        return availability -> availability.equals(reservation.getHour()) ||
                (availability.isAfter(reservation.getHour()) &&
                availability.isBefore(reservation.getHour().plusMinutes(
                        30L * reservation.getBeautyService().getNumOfAvailabilityBlocks())));
    }

    public void checkIfReservationPossibleForCreate(ReservationDto dto,
        int numOfAvailabilityBlocks, List<Reservation> reservationsOfEmployee) {

        List<LocalTime> reservationBlocks = constructTimeBlocks(
                dto.hour().getHour(), dto.hour().getMinute(), numOfAvailabilityBlocks);
        boolean isToday = dto.date().isEqual(LocalDate.now());
        List<LocalTime> availabilitiesOfEmployee = filterAvailabilityBasedOnReservations(reservationsOfEmployee, isToday);

        if(!new HashSet<>(availabilitiesOfEmployee).containsAll(reservationBlocks)) {
            throw new IllegalArgumentException("There is no availability for your selected time");
        }
    }

    public void checkIfReservationPossibleForUpdate(Reservation reservation,
        ReservationUpdateDto dto, List<Reservation> reservationsOfEmployee) {

        LocalTime originalTime = reservation.getHour();
        LocalDate originalDate = reservation.getDate();
        int numOfAvailabilityBlocks = reservation.getBeautyService().getNumOfAvailabilityBlocks();

        List<LocalTime> reservationBlocks = constructTimeBlocks(
            dto.hour().getHour(), dto.hour().getMinute(), numOfAvailabilityBlocks);
        boolean isToday = dto.date().isEqual(LocalDate.now());
        List<LocalTime> availabilitiesOfEmployee = filterAvailabilityBasedOnReservations(reservationsOfEmployee, isToday);

        boolean isSameDate = originalDate.equals(dto.date());
        if(isSameDate) {
            List<LocalTime> originalReservationBlocks = constructTimeBlocks(
                    originalTime.getHour(), originalTime.getMinute(), numOfAvailabilityBlocks);
            availabilitiesOfEmployee.addAll(originalReservationBlocks);
        }

        if(!new HashSet<>(availabilitiesOfEmployee).containsAll(reservationBlocks)) {
            throw new IllegalArgumentException("There is no availability for your selected time");
        }
    }
}
