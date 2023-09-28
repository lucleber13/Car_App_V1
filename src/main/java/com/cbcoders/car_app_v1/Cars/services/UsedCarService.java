package com.cbcoders.car_app_v1.Cars.services;

import com.cbcoders.car_app_v1.Cars.model.UsedCar;

public interface UsedCarService {
    UsedCar createUsedCar(UsedCar usedCar);

    UsedCar updateUsedCar(Long carId, UsedCar usedCar);

    void deleteUsedCar(Long carId);

    UsedCar getUsedCarByChassisNumber(String chassisNumber);

    UsedCar getUsedCarById(Long carId);

    UsedCar getUsedCarByRegNumber(String regNumber);


}
