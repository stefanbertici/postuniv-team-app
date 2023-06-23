package ro.ubb.SaloonApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.ubb.SaloonApp.dto.ReservationDto;
import ro.ubb.SaloonApp.dto.ReservationViewDto;
import ro.ubb.SaloonApp.model.Reservation;

import java.util.List;


@Mapper
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);


    Reservation toEntity(ReservationDto reservationDto);

    @Mapping(target = "clientName", expression = "java(reservation.getUser().getName())")
    @Mapping(target = "clientEmail", expression = "java(reservation.getUser().getEmail())")
    @Mapping(target = "employeeName", expression = "java(reservation.getEmployee().getName())")
    @Mapping(target = "employeeId", expression = "java(reservation.getEmployee().getId())")
    @Mapping(target = "beautyServiceName", expression = "java(reservation.getBeautyService().getName())")
    @Mapping(target = "beautyServicePrice", expression = "java(reservation.getBeautyService().getPrice())")
    @Mapping(target = "status", expression = "java(reservation.getStatus().name())")
    ReservationViewDto toReservationViewDto(Reservation reservation);

    @Mapping(target = "clientName", expression = "java(reservation.getUser().getName())")
    @Mapping(target = "clientEmail", expression = "java(reservation.getUser().getEmail())")
    @Mapping(target = "employeeName", expression = "java(reservation.getEmployee().getName())")
    @Mapping(target = "employeeId", expression = "java(reservation.getEmployee().getId())")
    @Mapping(target = "beautyServiceName", expression = "java(reservation.getBeautyService().getName())")
    @Mapping(target = "beautyServicePrice", expression = "java(reservation.getBeautyService().getPrice())")
    @Mapping(target = "status", expression = "java(reservation.getStatus().name())")
    List<ReservationViewDto> toReservationViewDtos(List<Reservation> reservations);
}
