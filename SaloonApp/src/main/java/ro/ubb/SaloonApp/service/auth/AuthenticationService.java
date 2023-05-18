package ro.ubb.SaloonApp.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.SaloonApp.dto.CustomerViewDto;
import ro.ubb.SaloonApp.dto.LoginViewDto;
import ro.ubb.SaloonApp.dto.UserDto;
import ro.ubb.SaloonApp.dto.UserLoginDto;
import ro.ubb.SaloonApp.exception.UnauthorizedException;
import ro.ubb.SaloonApp.mapper.UserMapper;
import ro.ubb.SaloonApp.model.User;
import ro.ubb.SaloonApp.repository.UserRepository;
import ro.ubb.SaloonApp.utils.RepositoryChecker;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ro.ubb.SaloonApp.constant.Role.CUSTOMER;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final InMemoryTokenBlacklistService inMemoryTokenBlacklistService;
    private final AuthenticationManager authenticationManager;
    private final RepositoryChecker repositoryChecker;
    private final UserRepository userRepository;

    @Transactional
    public CustomerViewDto register(UserDto userDto) {
        repositoryChecker.checkIfAlreadyRegistered(userDto.getEmail());

        User user = UserMapper.INSTANCE.toEntity(userDto);
        user = userRepository.save(user);

        String encryptedPassword = passwordEncoder.encode(userDto.getPassword());

        user.setEncryptedPassword(encryptedPassword);
        user.setRole(CUSTOMER);

        return UserMapper.INSTANCE.toCustomerViewDto(user);
    }

    @Transactional
    public LoginViewDto login(UserLoginDto userLoginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.getEmail(), userLoginDto.getPassword()
                )
        );

        Optional<User> optional = userRepository.findUserByEmail(userLoginDto.getEmail());
        if (optional.isEmpty()) {
            throw new UnauthorizedException("Incorrect email or password");
        }

        User user = optional.get();
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("role", user.getRole());
        String accessToken = jwtService.generateToken(claims, user);

        return UserMapper.INSTANCE.toLoginViewDto(user, accessToken);
    }

    public String logout(String authHeader) {
        String accessToken = authHeader.substring(7);
        inMemoryTokenBlacklistService.blacklist(accessToken);

        return "Logout successful";
    }
}
