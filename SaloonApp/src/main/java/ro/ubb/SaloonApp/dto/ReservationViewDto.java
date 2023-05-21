package ro.ubb.SaloonApp.dto;

import java.time.LocalDate;


public record ReservationViewDto(

    Integer id,
    Integer customerId,
    Integer beautyServiceId,
    String status,
    LocalDate resDate,
    double resHour){
}
