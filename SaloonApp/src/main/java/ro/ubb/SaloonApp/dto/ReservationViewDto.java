package ro.ubb.SaloonApp.dto;

import java.time.LocalDate;
import java.time.LocalTime;


public record ReservationViewDto(

        Integer id,
        String clientName,
        String clientEmail,
        String employeeName,
        Integer employeeId,
        String beautyServiceName,
        double beautyServicePrice,
        String status,
        LocalDate date,
        LocalTime hour) {
}
