package ro.ubb.SaloonApp.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ro.ubb.SaloonApp.exception.ResourceNotFoundException;
import ro.ubb.SaloonApp.model.User;
import ro.ubb.SaloonApp.repository.UserRepository;

@AllArgsConstructor
@Component
public class RepositoryChecker {

    private final UserRepository userRepository;

    public User getUserIfExists(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no user with id = " + id));
    }

    public User getUserIfExists(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("There is no user with email = " + email));
    }

    public void checkIfRegisteredEmail(String email) {
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email " + email + " is already registered");
        }
    }
}
