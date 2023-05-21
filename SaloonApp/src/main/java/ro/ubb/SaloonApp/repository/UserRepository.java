package ro.ubb.SaloonApp.repository;


import org.springframework.data.repository.CrudRepository;
import ro.ubb.SaloonApp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Override
    List<User> findAll();

    Optional<User> findUserByEmail(String email);
}

