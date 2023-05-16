package ro.ubb.SaloonApp.repository;


import org.springframework.data.repository.CrudRepository;
import ro.ubb.SaloonApp.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Override
    List<Category> findAll();

    Optional<Category> findCategoryByName(String name);
}
