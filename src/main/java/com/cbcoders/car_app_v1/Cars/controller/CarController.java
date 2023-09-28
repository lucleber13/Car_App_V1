package com.cbcoders.car_app_v1.Cars.controller;

import com.cbcoders.car_app_v1.Cars.model.Car;
import com.cbcoders.car_app_v1.Cars.model.DTO.CarDTO;
import com.cbcoders.car_app_v1.Cars.model.NewCar;
import com.cbcoders.car_app_v1.Cars.model.UsedCar;
import com.cbcoders.car_app_v1.Cars.services.CarService;
import com.cbcoders.car_app_v1.Cars.services.NewCarService;
import com.cbcoders.car_app_v1.Cars.services.UsedCarService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/v1/cars")
@CrossOrigin(origins = "http://localhost:4200")
public class CarController {
    private final CarService carService;
    private final NewCarService newCarService;
    private final UsedCarService usedCarService;

    public CarController(CarService carService, NewCarService newCarService, UsedCarService usedCarService) {
        this.carService = carService;
        this.newCarService = newCarService;
        this.usedCarService = usedCarService;
    }

    @PostMapping("/new")
    public ResponseEntity<NewCar> createNewCar(@RequestBody NewCar newCar) {
        newCar = newCarService.createNewCar(newCar);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{carId}")
                .buildAndExpand(newCar.getCarId())
                .toUri();
        return ResponseEntity.created(location).body(newCar);
    }

    @PostMapping("/used")
    public ResponseEntity<UsedCar> createUsedCar(@RequestBody UsedCar usedCar) {
        usedCar = usedCarService.createUsedCar(usedCar);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{carId}")
                .buildAndExpand(usedCar.getCarId())
                .toUri();
        return ResponseEntity.created(location).body(usedCar);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CarDTO>> getAllCarsSortedById() {
        List<CarDTO> allCars = carService.getAllCarsSortedById();
        return ResponseEntity.ok(allCars);
    }

    @GetMapping("/new/{carId}")
    public ResponseEntity<NewCar> getNewCarById(@PathVariable("carId") Long carId) {
        return ResponseEntity.ok( newCarService.getNewCarById(carId));
    }

    @GetMapping("/used/{carId}")
    public ResponseEntity<UsedCar> getUsedCarById(@PathVariable("carId") Long carId) {
        return ResponseEntity.ok( usedCarService.getUsedCarById(carId));
    }

    @GetMapping("/new/chassis/{chassisNumber}")
    public ResponseEntity<NewCar> getNewCarByChassisNumber(@PathVariable("chassisNumber") String chassisNumber) {
        return ResponseEntity.ok( newCarService.getNewCarByChassisNumber(chassisNumber));
    }

    @GetMapping("/used/chassis{chassisNumber}")
    public ResponseEntity<UsedCar> getUsedCarByChassisNumber(@PathVariable("chassisNumber") String chassisNumber) {
        return ResponseEntity.ok( usedCarService.getUsedCarByChassisNumber(chassisNumber));
    }

   /* @GetMapping("customer/{customerName}")
    public ResponseEntity<List<CarDTO>> getCarsByCustomerName(@PathVariable("customerName") String customerName) {
        return ResponseEntity.ok( carService.getCarsByCustomerName(customerName));
    }*/

    @GetMapping("/used/reg/{regNumber}")
    public ResponseEntity<UsedCar> getUsedCarByRegNumber(@PathVariable("regNumber") String regNumber) {
        return ResponseEntity.ok( usedCarService.getUsedCarByRegNumber(regNumber));
    }

    @PutMapping("/new/{carId}")
    public ResponseEntity<NewCar> updateNewCar(@PathVariable("carId") Long carId, @RequestBody NewCar newCar) {
        return ResponseEntity.ok( newCarService.updateNewCar(carId, newCar));
    }

    @PutMapping("/used/{carId}")
    public ResponseEntity<UsedCar> updateUsedCar(@PathVariable("carId") Long carId, @RequestBody UsedCar usedCar) {
        return ResponseEntity.ok( usedCarService.updateUsedCar(carId, usedCar));
    }

    @DeleteMapping("/new/{carId}")
    public ResponseEntity<Void> deleteNewCar(@PathVariable("carId") Long carId) {
        newCarService.deleteNewCar(carId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/used/{carId}")
    public ResponseEntity<Void> deleteUsedCar(@PathVariable("carId") Long carId) {
        usedCarService.deleteUsedCar(carId);
        return ResponseEntity.noContent().build();
    }


}
