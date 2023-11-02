package com.cbcoders.car_app_v1.Cars.services;

import com.cbcoders.car_app_v1.Cars.model.DTO.NewCarDTO;
import com.cbcoders.car_app_v1.Cars.model.NewCar;

import java.util.List;

public interface NewCarService {
    NewCarDTO createNewCar(NewCarDTO newCarDTO);
    NewCarDTO updateNewCar(Long carId, NewCarDTO newCarDTO);
    List<NewCarDTO> getNewCarByModel(String model);
}




