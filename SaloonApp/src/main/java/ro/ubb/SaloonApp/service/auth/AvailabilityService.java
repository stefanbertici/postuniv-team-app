package ro.ubb.SaloonApp.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.ubb.SaloonApp.model.Reservation;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@RequiredArgsConstructor
@Service
public class AvailabilityService {

    public List<LocalTime> filterAvailabilityBasedOnReservations(List<Reservation> reservations) {
        List<LocalTime> availability = constructDefaultAvailabilities();
        reservations.forEach(reservation -> availability.removeIf(checkIfTimeIsReserved(reservation)));

        return availability;
    }

    private List<LocalTime> constructDefaultAvailabilities() {
        List<LocalTime> defaultAvailability = new ArrayList<>();

        LocalTime time = LocalTime.of(9, 0, 0);

        for (int i = 0; i < 16; i++) {
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
}
