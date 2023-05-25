package ro.ubb.SaloonApp.dto;

import java.time.LocalDate;
import java.time.LocalTime;


public record ReservationUpdateDto(

    LocalDate resDate,
    LocalTime resHour){
}
