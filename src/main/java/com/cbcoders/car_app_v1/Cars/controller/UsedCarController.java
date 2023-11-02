package com.cbcoders.car_app_v1.Cars.controller;

import com.cbcoders.car_app_v1.Cars.model.DTO.UsedCarDTO;
import com.cbcoders.car_app_v1.Cars.services.UsedCarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/usedcar")
public class UsedCarController {
	private final UsedCarService usedCarService;

	public UsedCarController(UsedCarService usedCarService) {
		this.usedCarService = usedCarService;
	}

	@PostMapping("/create")
	public ResponseEntity<UsedCarDTO> createUsedCar(@RequestBody UsedCarDTO usedCarDTO) {
		usedCarDTO = usedCarService.createUsedCar(usedCarDTO);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{carId}")
				.buildAndExpand(usedCarDTO.getCarId())
				.toUri();
		return ResponseEntity.created(location).body(usedCarDTO);
	}

	@PutMapping("/update/{carId}")
	public ResponseEntity<UsedCarDTO> updateUsedCar(@PathVariable Long carId, @RequestBody UsedCarDTO usedCarDTO) {
		return ResponseEntity.ok(usedCarService.updateUsedCar(carId, usedCarDTO));
	}

	@GetMapping("/regNumber/{regNumber}")
	public ResponseEntity<UsedCarDTO> getUsedCarByRegNumber(@PathVariable String regNumber) {
		return ResponseEntity.ok(usedCarService.getUsedCarByRegNumber(regNumber));
	}
}
