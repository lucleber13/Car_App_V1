package com.cbcoders.car_app_v1.Cars.repository;

import com.cbcoders.car_app_v1.Cars.model.Car;
import com.cbcoders.car_app_v1.Cars.model.DTO.CarDTO;
import com.cbcoders.car_app_v1.Cars.model.UsedCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	Optional<Car> findByChassisNumberIgnoreCase(String chassisNumber);

	Collection<Object> findAllByModelContainsIgnoreCase(String model);

	List<Car> findAllByDateArrived(Date dateArrived);
}