package ro.ubb.SaloonApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.SaloonApp.dto.EmployeeViewDto;
import ro.ubb.SaloonApp.exception.UnauthorizedException;
import ro.ubb.SaloonApp.mapper.UserMapper;
import ro.ubb.SaloonApp.model.User;
import ro.ubb.SaloonApp.repository.UserRepository;
import ro.ubb.SaloonApp.utils.RepositoryChecker;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final RepositoryChecker repositoryChecker;
    private final UserRepository userRepository;

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
}
