package ro.ubb.SaloonApp.dto;

import java.time.LocalDate;
import java.time.LocalTime;


public record ReservationViewDto(

    Integer id,
    String clientName,
    String clientEmail,
    String employeeName,
    String status,
    LocalDate resDate,
    LocalTime resHour){
}
