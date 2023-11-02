package com.cbcoders.car_app_v1.Cars.controller;

import com.cbcoders.car_app_v1.Cars.model.DTO.UsedCarDTO;
import com.cbcoders.car_app_v1.Cars.services.UsedCarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
