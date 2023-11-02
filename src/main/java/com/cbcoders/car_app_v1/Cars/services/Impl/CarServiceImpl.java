package com.cbcoders.car_app_v1.Cars.services.Impl;

import com.cbcoders.car_app_v1.Cars.model.DTO.CarDTO;
import com.cbcoders.car_app_v1.Cars.repository.NewCarRepository;
import com.cbcoders.car_app_v1.Cars.repository.UsedCarRepository;
import com.cbcoders.car_app_v1.Cars.services.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final NewCarRepository newCarRepository;
    private final UsedCarRepository usedCarRepository;
    private final ModelMapper modelMapper;

    public CarServiceImpl(NewCarRepository newCarRepository, UsedCarRepository usedCarRepository, ModelMapper modelMapper) {
        this.newCarRepository = newCarRepository;
        this.usedCarRepository = usedCarRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CarDTO getCarById(Long carId) {
        return null;
    }

    @Override
    public List<CarDTO> getAllCars() {
        return null;
    }

    @Override
    public CarDTO getCarByChassisNumber(String chassisNumber) {
        return null;
    }

    @Override
    public List<CarDTO> getCarsByModel(String model) {
        return null;
    }

    @Override
    public void deleteCar(Long carId) {

    }
}
