package com.cbcoders.car_app_v1.Cars.services;

import com.cbcoders.car_app_v1.Cars.model.DTO.UsedCarDTO;

public interface UsedCarService {
    UsedCarDTO createUsedCar(UsedCarDTO usedCarDTO);

    UsedCarDTO updateUsedCar(Long carId, UsedCarDTO usedCarDTO);

    UsedCarDTO getUsedCarByRegNumber(String regNumber);


}
