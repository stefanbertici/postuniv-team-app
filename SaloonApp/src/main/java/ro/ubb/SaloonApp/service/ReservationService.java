package ro.ubb.SaloonApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.SaloonApp.constant.Status;
import ro.ubb.SaloonApp.dto.ReservationDto;
import ro.ubb.SaloonApp.dto.ReservationUpdateDto;
import ro.ubb.SaloonApp.dto.ReservationViewDto;
import ro.ubb.SaloonApp.exception.UnauthorizedException;
import ro.ubb.SaloonApp.mapper.ReservationMapper;
import ro.ubb.SaloonApp.model.BeautyService;
import ro.ubb.SaloonApp.model.Reservation;
import ro.ubb.SaloonApp.model.User;
import ro.ubb.SaloonApp.repository.ReservationRepository;
import ro.ubb.SaloonApp.utils.RepositoryChecker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional
    public String accept(Integer id, Authentication authentication) {
        Optional<? extends GrantedAuthority> optional = authentication.getAuthorities().stream()
                .filter(authority -> authority.getAuthority().equals("EMPLOYEE"))
                .findAny();

        if (optional.isEmpty()) {
            throw new UnauthorizedException("Only employees can accept reservations");
        }

        Reservation reservation = repositoryChecker.getReservationIfExists(id);
        if (!reservation.getStatus().equals(MODIFIED) && !reservation.getStatus().equals(PENDING)) {
            throw new IllegalArgumentException("Only pending or modified reservations can be accepted");
        }

        reservation.setStatus(ACCEPTED);

        return "Reservation accepted successfully";
    }

    @Transactional
    public String complete(Integer id, Authentication authentication) {
        Optional<? extends GrantedAuthority> optional = authentication.getAuthorities().stream()
                .filter(authority -> authority.getAuthority().equals("EMPLOYEE"))
                .findAny();

        if (optional.isEmpty()) {
            throw new UnauthorizedException("Only employees can accept reservations");
        }

        Reservation reservation = repositoryChecker.getReservationIfExists(id);

        if (!reservation.getStatus().equals(ACCEPTED)) {
            throw new IllegalArgumentException("Only accepted reservations can be completed");
        }

        reservation.setStatus(COMPLETED);

        return "Reservation completed successfully";
    }

    @Transactional
    public String cancel(Integer id) {
        Reservation reservation = repositoryChecker.getReservationIfExists(id);

        if (reservation.getStatus().equals(COMPLETED)) {
            throw new IllegalArgumentException("Completed reservations cannot be cancelled");
        }

        reservation.setStatus(CANCELLED);

        return "Reservation cancelled successfully";
    }

    public List<ReservationViewDto> getUserReservations(Integer userId) {
        repositoryChecker.checkIfUserIsCustomer(userId);

        List<Reservation> allReservations = reservationRepository.findAll();
        List<Reservation> usersReservations = allReservations.stream()
                .filter(u -> Objects.equals(u.getUser().getId(), userId)).toList();

        return ReservationMapper.INSTANCE.toReservationViewDtos(usersReservations.stream().toList());
    }
}
