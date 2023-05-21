package ro.ubb.SaloonApp.repository;

import org.springframework.data.repository.CrudRepository;
import ro.ubb.SaloonApp.model.Reservation;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

    List<Reservation> findAll();

    List<Reservation> findAllByOrderByResDate();
}
