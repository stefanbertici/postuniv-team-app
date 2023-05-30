package ro.ubb.SaloonApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.SaloonApp.constant.Role;
import ro.ubb.SaloonApp.constant.Status;
import ro.ubb.SaloonApp.dto.ReservationDto;
import ro.ubb.SaloonApp.dto.ReservationUpdateDto;
import ro.ubb.SaloonApp.dto.ReservationViewDto;
import ro.ubb.SaloonApp.mapper.ReservationMapper;
import ro.ubb.SaloonApp.model.BeautyService;
import ro.ubb.SaloonApp.model.Reservation;
import ro.ubb.SaloonApp.model.User;
import ro.ubb.SaloonApp.repository.ReservationRepository;
import ro.ubb.SaloonApp.utils.RepositoryChecker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ro.ubb.SaloonApp.constant.Status.*;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RepositoryChecker repositoryChecker;

    private final AvailabilityService availabilityService;

    public List<ReservationViewDto> readAll() {
        List<Reservation> reservations = reservationRepository.findAll();

        return ReservationMapper.INSTANCE.toReservationViewDtos(reservations);
    }

    public ReservationViewDto delete(Integer id) {
        Reservation reservationToBeDeleted = repositoryChecker.getReservationIfExists(id);

        reservationRepository.deleteById(id);

        return ReservationMapper.INSTANCE.toReservationViewDto(reservationToBeDeleted);
    }

    public ReservationViewDto readOne(Integer id) {
        Reservation reservation = repositoryChecker.getReservationIfExists(id);

        return ReservationMapper.INSTANCE.toReservationViewDto(reservation);
    }

    @Transactional
    public ReservationViewDto create(ReservationDto dto) {
        if (dto.date().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Reservation date '" + dto.date() + "' cannot be in the past");
        } else if (dto.date().equals(LocalDate.now()) && dto.hour().isBefore(LocalTime.now())) {
            throw new IllegalArgumentException("Reservation hour '" + dto.hour() + "' cannot be in the past");
        }

        User user = repositoryChecker.getUserIfExists(dto.customerId());
        User employee = repositoryChecker.getEmployeeIfExists(dto.employeeId());
        BeautyService beautyService = repositoryChecker.getBeautyServiceIfExists(dto.beautyServiceId());
        List<Reservation> reservationsOfUser = reservationRepository
                .findAllByEmployeeIdAndDateOrderByHourAsc(employee.getId(), dto.date());

        availabilityService.checkIfReservationPossibleForCreate(
                dto, beautyService.getNumOfAvailabilityBlocks(), reservationsOfUser);

        Reservation reservation = ReservationMapper.INSTANCE.toEntity(dto);
        reservationRepository.save(reservation);

        reservation.setUser(user);
        reservation.setEmployee(employee);
        reservation.setBeautyService(beautyService);
        reservation.setStatus(PENDING);

        return ReservationMapper.INSTANCE.toReservationViewDto(reservation);
    }

    @Transactional
    public ReservationViewDto update(Integer id, ReservationUpdateDto dto) {
        Reservation reservation = repositoryChecker.getReservationIfExists(id);

        if (reservation.getStatus().equals(COMPLETED)) {
            throw new IllegalArgumentException("Completed reservations cannot be modified");
        }

        if (dto.date().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Reservation date '" + dto.date() + "' cannot be in the past");
        } else if (dto.date().equals(LocalDate.now()) && dto.hour().isBefore(LocalTime.now())) {
            throw new IllegalArgumentException("Reservation hour '" + dto.hour() + "' cannot be in the past");
        }

        int employeeId = reservation.getEmployee().getId();
        LocalDate date = dto.date();
        List<Reservation> reservationsOfEmployee = reservationRepository
                .findAllByEmployeeIdAndDateOrderByHourAsc(employeeId, date);

        availabilityService.checkIfReservationPossibleForUpdate(reservation, dto, reservationsOfEmployee);

        reservation.setDate(dto.date());
        reservation.setHour(dto.hour());
        reservation.setStatus(MODIFIED);

        return ReservationMapper.INSTANCE.toReservationViewDto(reservation);
    }

    public String CheckReservationStatus(ReservationViewDto reservationViewDto) {
        Integer reservationId = reservationViewDto.id();
        Reservation reservation = repositoryChecker.getReservationIfExists(reservationId);
        User userOfReservation = reservation.getUser();
        User employeeOfReservation = reservation.getEmployee();
        String userIdOfReservationStringify = String.valueOf(userOfReservation.getId());

        if (!reservation.getStatus().equals(CANCELLED)) {
            if (employeeOfReservation.getRole().equals(Role.EMPLOYEE)) {
                return userIdOfReservationStringify + "/" + Status.valueOf(String.valueOf(ACCEPTED));
            }
        }

        return userIdOfReservationStringify + "/" + Status.valueOf(String.valueOf(CANCELLED));
    }
}
