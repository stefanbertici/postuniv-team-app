package ro.ubb.SaloonApp.dto;

import java.time.LocalDate;
import java.time.LocalTime;


public record ReservationDto (

    Integer customerId,
    Integer beautyServiceID,
    String status,
    LocalDate date,
    LocalTime hour){
}
