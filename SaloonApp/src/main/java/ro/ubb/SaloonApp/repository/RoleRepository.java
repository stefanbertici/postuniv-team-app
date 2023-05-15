package ro.ubb.SaloonApp.repository;


import org.springframework.data.repository.CrudRepository;
import ro.ubb.SaloonApp.model.Role;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Optional<Role> findRoleByName(String name);
}
