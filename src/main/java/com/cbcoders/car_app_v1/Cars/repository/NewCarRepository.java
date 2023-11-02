package com.cbcoders.car_app_v1.Cars.repository;

import com.cbcoders.car_app_v1.Cars.model.NewCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewCarRepository extends JpaRepository<NewCar, Long> {

	Optional<NewCar> findByChassisNumber(String chassisNumber);

	List<NewCar> findByModelContainsIgnoreCase(String model);
}
