package ro.ubb.SaloonApp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.SaloonApp.dto.UserDto;
import ro.ubb.SaloonApp.dto.UserViewDto;
import ro.ubb.SaloonApp.mapper.UserMapper;
import ro.ubb.SaloonApp.model.Role;
import ro.ubb.SaloonApp.model.User;
import ro.ubb.SaloonApp.repository.UserRepository;
import ro.ubb.SaloonApp.utils.RepositoryChecker;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static ro.ubb.SaloonApp.constant.Role.CUSTOMER;

@Service
@AllArgsConstructor
public class UserService {

    private final RepositoryChecker repositoryChecker;
    private final UserRepository userRepository;

    @Transactional
    public UserViewDto registerUser(UserDto userDto) {
        repositoryChecker.checkIfRegisteredEmail(userDto.getEmail());

        User user = UserMapper.INSTANCE.toEntity(userDto);
        user = userRepository.save(user);

        String encryptedPassword = getEncryptedPassword(userDto.getPassword());
        Role role = Role.builder()
                .name(CUSTOMER.value)
                .build();

        user.setEncryptedPassword(encryptedPassword);
        user.setRole(role);

        return UserMapper.INSTANCE.toViewDto(user);
    }

    private String getEncryptedPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashedPassword = md.digest(password.getBytes());
            BigInteger bigInteger = new BigInteger(1, hashedPassword);
            return bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
