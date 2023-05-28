package ro.ubb.SaloonApp.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationDto(

        Integer customerId,
        Integer beautyServiceId,
        Integer employeeId,
        String status,
        LocalDate date,
        LocalTime hour) {
}
