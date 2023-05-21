package ro.ubb.SaloonApp.dto;

import java.time.LocalDate;


public record ReservationDto (

    Integer customerId,
    Integer beautyServiceID,
    String status,
    LocalDate resDate,
    double resHour){
}
