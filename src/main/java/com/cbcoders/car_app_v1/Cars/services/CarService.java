package com.cbcoders.car_app_v1.Cars.services;

import com.cbcoders.car_app_v1.Cars.model.DTO.CarDTO;

import java.util.List;

public interface CarService {
    List<CarDTO> getAllCarsSortedById();
}
