package ro.ubb.SaloonApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.SaloonApp.dto.UserViewDto;
import ro.ubb.SaloonApp.mapper.UserMapper;
import ro.ubb.SaloonApp.model.User;
import ro.ubb.SaloonApp.repository.UserRepository;
import ro.ubb.SaloonApp.utils.RepositoryChecker;

import java.util.List;

@Service
@RequiredArgsConstructor
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
}
