package ro.ubb.SaloonApp.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.ubb.SaloonApp.exception.ResourceNotFoundException;
import ro.ubb.SaloonApp.model.BeautyService;
import ro.ubb.SaloonApp.model.Category;
import ro.ubb.SaloonApp.model.Reservation;
import ro.ubb.SaloonApp.model.User;
import ro.ubb.SaloonApp.repository.BeautyServiceRepository;
import ro.ubb.SaloonApp.repository.CategoryRepository;
import ro.ubb.SaloonApp.repository.ReservationRepository;
import ro.ubb.SaloonApp.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class RepositoryChecker {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final BeautyServiceRepository beautyServiceRepository;
    private final ReservationRepository reservationRepository;

    public BeautyService getBeautyServiceIfExists(Integer id) {
        return beautyServiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no service with id =" + id));
    }

    public User getUserIfExists(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no user with id = '" + id + "'"));
    }

    public User getUserIfExists(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("There is no user with email = '" + email + "'"));
    }

    public void checkIfEmailAlreadyRegistered(String email) {
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email '" + email + "' is already registered");
        }
    }

    public Category getCategoryIfExists(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no category with id = '" + id + "'"));
    }

    public Category getCategoryIfExists(String name) {
        return categoryRepository.findCategoryByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("There is no category with name '" + name + "'"));
    }

    public void checkIfCategoryAlreadyExists(String name) {
        if (categoryRepository.findCategoryByName(name).isPresent()) {
            throw new IllegalArgumentException("Category '" + name + "' already exists");
        }
    }

    public Reservation getReservationIfExists(Integer id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no reservation with id '" + id + "'"));
    }
}
