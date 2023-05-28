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
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final RepositoryChecker repositoryChecker;
    private final AvailabilityService availabilityService;
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

        List<Reservation> reservations = reservationRepository.findAllByEmployeeIdAndDateOrderByHourAsc(id, date);

        return availabilityService.filterAvailabilityBasedOnReservations(reservations);
    }
}
