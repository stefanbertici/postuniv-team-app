package ro.ubb.SaloonApp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.SaloonApp.dto.CustomerViewDto;
import ro.ubb.SaloonApp.dto.UserDto;
import ro.ubb.SaloonApp.dto.UserViewDto;
import ro.ubb.SaloonApp.mapper.UserMapper;
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
    public List<UserViewDto> readAllUsers() {
        List<User> users = userRepository.findAll();

        return UserMapper.INSTANCE.toUserViewDtos(users);
    }

    @Transactional
    public UserViewDto readUserById(int id) {
        User user = repositoryChecker.getUserIfExists(id);

        return UserMapper.INSTANCE.toUserViewDto(user);
    }

    @Transactional
    public CustomerViewDto registerUser(UserDto userDto) {
        repositoryChecker.checkIfRegisteredEmail(userDto.getEmail());

        User user = UserMapper.INSTANCE.toEntity(userDto);
        user = userRepository.save(user);

        String encryptedPassword = getEncryptedPassword(userDto.getPassword());

        user.setEncryptedPassword(encryptedPassword);
        user.setRole(CUSTOMER);

        return UserMapper.INSTANCE.toCustomerViewDto(user);
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
