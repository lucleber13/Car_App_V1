package com.cbcoders.car_app_v1.Cars.controller;

import com.cbcoders.car_app_v1.Cars.model.Car;
import com.cbcoders.car_app_v1.Cars.model.DTO.CarDTO;
import com.cbcoders.car_app_v1.Cars.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
	private final CarService carService;

	public CarController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping("/find/{carId}")
	public ResponseEntity<Car> getCarById(@PathVariable Long carId) {
		Car car = carService.getCarById(carId);
		return ResponseEntity.ok(car);
	}

	@GetMapping("/all")
	public ResponseEntity<List<CarDTO>> getAllCars() {
		return ResponseEntity.ok(carService.getAllCars());
	}

	@GetMapping("/chassis/{chassisNumber}")
	public ResponseEntity<CarDTO> getCarByChassisNumber(@PathVariable String chassisNumber) {
		CarDTO car = carService.getCarByChassisNumber(chassisNumber);
		return ResponseEntity.ok(car);
	}

	@GetMapping("/model/{model}")
	public ResponseEntity<List<CarDTO>> getCarsByModel(@PathVariable String model) {
		List<CarDTO> cars = carService.getCarsByModel(model);
		return ResponseEntity.ok(cars);
	}

	@GetMapping("/reg/{regNumber}")
	public ResponseEntity<CarDTO> getCarByRegNumber(@PathVariable String regNumber) {
		CarDTO car = carService.getCarByRegNumber(regNumber);
		return ResponseEntity.ok(car);
	}

	@GetMapping("/date/{dateArrived}")
	public ResponseEntity<List<CarDTO>> getCarByDateArrived(@PathVariable String dateArrived) {
		List<CarDTO> cars = carService.getCarByDateArrived(dateArrived);
		return ResponseEntity.ok(cars);
	}

	@DeleteMapping("/delete/{carId}")
	public ResponseEntity<Void> deleteCar(@PathVariable Long carId) {
		carService.deleteCar(carId);
		return ResponseEntity.noContent().build();
	}
}
