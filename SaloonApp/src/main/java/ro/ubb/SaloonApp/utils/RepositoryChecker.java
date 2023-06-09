package ro.ubb.SaloonApp.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.ubb.SaloonApp.constant.Role;
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

    public User getEmployeeIfExists(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no user with id = '" + id + "'"));

        if (!user.getRole().equals(Role.EMPLOYEE)) {
            throw new ResourceNotFoundException("The user with id = '" + id + "' is not an employee");
        }

        return user;
    }

    public boolean checkIfUserIsCustomer(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no user with id = '" + id + "'"));

        if (!user.getRole().equals(Role.CUSTOMER)) {
            throw new ResourceNotFoundException("The user with id = '" + id + "' is not a customer");
        }

        return true;
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
