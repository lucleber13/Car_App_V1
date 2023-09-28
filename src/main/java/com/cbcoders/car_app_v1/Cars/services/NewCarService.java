package com.cbcoders.car_app_v1.Cars.services;

import com.cbcoders.car_app_v1.Cars.model.NewCar;

public interface NewCarService {
    NewCar createNewCar(NewCar newCar);
    NewCar updateNewCar(Long carId, NewCar newCar);
    void deleteNewCar(Long carId);
    NewCar getNewCarById(Long carId);
    NewCar getNewCarByChassisNumber(String chassisNumber);
}
