package com.cbcoders.car_app_v1.Cars.services;

import com.cbcoders.car_app_v1.Cars.model.Car;
import com.cbcoders.car_app_v1.Cars.model.DTO.CarDTO;

import java.util.Date;
import java.util.List;

public interface CarService {
    Car getCarById(Long carId);
    List<CarDTO> getAllCars();
    CarDTO getCarByChassisNumber(String chassisNumber);
    List<CarDTO> getCarsByModel(String model);
    CarDTO getCarByRegNumber(String regNumber);
    List<CarDTO> getCarByDateArrived(String dateArrived);
    void deleteCar(Long carId);
}





