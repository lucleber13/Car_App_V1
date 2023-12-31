package com.cbcoders.car_app_v1.Cars.repository;

import com.cbcoders.car_app_v1.Cars.model.UsedCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UsedCarRepository extends JpaRepository<UsedCar, Long> {

	Optional<UsedCar> findByRegNumberIgnoreCase(String regNumber);

	Optional<UsedCar> findByChassisNumber(String chassisNumber);
}
