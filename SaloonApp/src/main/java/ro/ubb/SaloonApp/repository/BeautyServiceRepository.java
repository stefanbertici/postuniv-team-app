package ro.ubb.SaloonApp.repository;

import org.springframework.data.repository.CrudRepository;
import ro.ubb.SaloonApp.model.BeautyService;
import ro.ubb.SaloonApp.model.Category;

import java.util.List;
import java.util.Optional;

public interface BeautyServiceRepository extends CrudRepository<BeautyService, Integer> {

    @Override
    List<BeautyService> findAll();

    Optional<BeautyService> findBeautyServiceByName(String name);
}
