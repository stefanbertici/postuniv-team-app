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

    @Mapping(target = "customerId", expression = "java(reservation.getUser().getId())")
    @Mapping(target = "beautyServiceId", expression = "java(reservation.getBeautyService().getId())")
    @Mapping(target = "status", expression = "java(reservation.getStatus().name())")
    ReservationViewDto toReservationViewDto(Reservation reservation);

    @Mapping(target = "customerId", expression = "java(reservation.getUser().getId())")
    @Mapping(target = "beautyServiceId", expression = "java(reservation.getBeautyService().getId())")
    @Mapping(target = "status", expression = "java(reservation.getStatus().name())")
    List<ReservationViewDto> toReservationViewDtos(List<Reservation> reservations);

}
