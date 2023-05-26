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

import java.util.List;

import static ro.ubb.SaloonApp.constant.Status.MODIFIED;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RepositoryChecker repositoryChecker;

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
    public ReservationViewDto create(ReservationDto reservationDto) {
        User user = repositoryChecker.getUserIfExists(reservationDto.customerId());

        BeautyService beautyService = repositoryChecker.getBeautyServiceIfExists(reservationDto.beautyServiceID());

        Reservation reservation = ReservationMapper.INSTANCE.toEntity(reservationDto);
        reservationRepository.save(reservation);

        reservation.setUser(user);
        reservation.setBeautyService(beautyService);

        return ReservationMapper.INSTANCE.toReservationViewDto(reservation);
    }


    @Transactional
    public ReservationViewDto update(Integer id, ReservationUpdateDto reservationUpdateDto) {

        Reservation reservationToBeUpdated = repositoryChecker.getReservationIfExists(id);

        reservationToBeUpdated.setDate(reservationUpdateDto.resDate());
        reservationToBeUpdated.setHour(reservationUpdateDto.resHour());
        reservationToBeUpdated.setStatus(MODIFIED);

        return ReservationMapper.INSTANCE.toReservationViewDto(reservationToBeUpdated);
    }

}
