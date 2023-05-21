package ro.ubb.SaloonApp.dto;

import java.time.LocalDate;


public record ReservationUpdateDto(

    LocalDate resDate,
    double resHour){
}
