package com.cbcoders.car_app_v1.Cars.services;

import com.cbcoders.car_app_v1.Cars.model.Car;
import com.cbcoders.car_app_v1.Cars.model.DTO.CarDTO;

import java.util.List;

public interface CarService {
    CarDTO getCarById(Long carId);
    List<CarDTO> getAllCars();
    CarDTO getCarByChassisNumber(String chassisNumber);
    List<CarDTO> getCarsByModel(String model);
    void deleteCar(Long carId);
}





