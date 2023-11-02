package com.cbcoders.car_app_v1.Cars.controller;

import com.cbcoders.car_app_v1.Cars.model.DTO.NewCarDTO;
import com.cbcoders.car_app_v1.Cars.services.NewCarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/newcar")
public class NewCarController {
	private final NewCarService newCarService;

	public NewCarController(NewCarService newCarService) {
		this.newCarService = newCarService;
	}

	@PostMapping("/create")
	public ResponseEntity<NewCarDTO> createNewCar(@RequestBody NewCarDTO newCarDTO) {
		newCarDTO = newCarService.createNewCar(newCarDTO);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{carId}")
				.buildAndExpand(newCarDTO.getCarId())
				.toUri();
		return ResponseEntity.created(location).body(newCarDTO);
	}

	@PutMapping("/update/{carId}")
	public ResponseEntity<NewCarDTO> updateNewCar(@PathVariable Long carId, @RequestBody NewCarDTO newCarDTO) {
		return ResponseEntity.ok(newCarService.updateNewCar(carId, newCarDTO));
	}

	@GetMapping("/model/{model}")
	public ResponseEntity<List<NewCarDTO>> getNewCarByModel(@PathVariable String model) {
		return ResponseEntity.ok(newCarService.getNewCarByModel(model));
	}
}
