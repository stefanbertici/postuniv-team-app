package ro.ubb.SaloonApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.SaloonApp.constant.Role;
import ro.ubb.SaloonApp.dto.EmployeeViewDto;
import ro.ubb.SaloonApp.exception.UnauthorizedException;
import ro.ubb.SaloonApp.mapper.UserMapper;
import ro.ubb.SaloonApp.model.Reservation;
import ro.ubb.SaloonApp.model.User;
import ro.ubb.SaloonApp.repository.ReservationRepository;
import ro.ubb.SaloonApp.repository.UserRepository;
import ro.ubb.SaloonApp.utils.RepositoryChecker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final RepositoryChecker repositoryChecker;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    public List<EmployeeViewDto> readAll() {
        List<User> users = userRepository.findAll();

        return UserMapper.INSTANCE.toEmployeeViewDtos(users);
    }

    @Transactional
    public EmployeeViewDto readById(int id) {
        User user = repositoryChecker.getUserIfExists(id);

        return UserMapper.INSTANCE.toEmployeeViewDto(user);
    }

    @Transactional
    public String changePassword(Integer id, String password, Authentication authentication) {
        User user = repositoryChecker.getUserIfExists(id);
        Optional<? extends GrantedAuthority> optional = authentication.getAuthorities().stream()
                .filter(authority -> authority.getAuthority().equals("ADMIN"))
                .findAny();

        if (!user.getEmail().equals(authentication.getName()) && optional.isEmpty()) {
            throw new UnauthorizedException("You cannot change other users' passwords");
        }

        user.setEncryptedPassword(passwordEncoder.encode(password));

        return "Password changed successfully";
    }

    @Transactional
    public List<LocalTime> getAvailability(Integer id, LocalDate date) {
        User user = repositoryChecker.getUserIfExists(id);

        if (!user.getRole().equals(Role.EMPLOYEE)) {
            throw new IllegalArgumentException("User with id = '" + id + "' is not an employee");
        }

        List<LocalTime> availability = constructDefaultAvailabilities();
        List<Reservation> reservations = reservationRepository.findAllByUserIdAndDateOrderByHourAsc(id, date);

        filterAvailabilityBasedOnReservations(reservations, availability);

        return availability;
    }

    private void filterAvailabilityBasedOnReservations(List<Reservation> reservations, List<LocalTime> defaultAvailability) {
        reservations.forEach(reservation -> defaultAvailability.removeIf(checkIfTimeIsReserved(reservation)));
    }

    private static Predicate<LocalTime> checkIfTimeIsReserved(Reservation reservation) {
        return availability -> availability.equals(reservation.getHour()) || (availability.isAfter(reservation.getHour()) &&
                availability.isBefore(reservation.getHour().plusMinutes(
                        30L * reservation.getBeautyService().getNumOfAvailabilityBlocks())));
    }

    private List<LocalTime> constructDefaultAvailabilities() {
        List<LocalTime> defaultAvailability = new ArrayList<>();

        LocalTime time = LocalTime.of(9, 0, 0);

        for (int i = 0; i < 16; i++) {
            defaultAvailability.add(time);
            time = time.plusMinutes(30);
        }

        return defaultAvailability;
    }
}
