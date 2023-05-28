package ro.ubb.SaloonApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.List;

import static ro.ubb.SaloonApp.constant.Status.MODIFIED;

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
        User user = repositoryChecker.getUserIfExists(dto.customerId());
        User employee = repositoryChecker.getEmployeeIfExists(dto.employeeId());
        BeautyService beautyService = repositoryChecker.getBeautyServiceIfExists(dto.beautyServiceId());
        List<Reservation> reservationsOfUser = reservationRepository.findAllByEmployeeIdAndDateOrderByHourAsc(employee.getId(), dto.date());

        availabilityService.checkIfReservationPossibleForCreate(dto, beautyService.getNumOfAvailabilityBlocks(),  reservationsOfUser);

        Reservation reservation = ReservationMapper.INSTANCE.toEntity(dto);
        reservationRepository.save(reservation);

        reservation.setUser(user);
        reservation.setEmployee(employee);
        reservation.setBeautyService(beautyService);

        return ReservationMapper.INSTANCE.toReservationViewDto(reservation);
    }


    @Transactional
    public ReservationViewDto update(Integer id, ReservationUpdateDto dto) {
        Reservation originalReservation = repositoryChecker.getReservationIfExists(id);

        int employeeId = originalReservation.getEmployee().getId();
        LocalDate date = dto.date();
        List<Reservation> reservationsOfUser = reservationRepository.findAllByEmployeeIdAndDateOrderByHourAsc(employeeId, date);

        availabilityService.checkIfReservationPossibleForUpdate(originalReservation, dto, reservationsOfUser);

        originalReservation.setDate(dto.date());
        originalReservation.setHour(dto.hour());
        originalReservation.setStatus(MODIFIED);

        return ReservationMapper.INSTANCE.toReservationViewDto(originalReservation);
    }
}
