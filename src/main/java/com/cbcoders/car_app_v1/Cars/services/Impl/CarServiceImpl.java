package com.cbcoders.car_app_v1.Cars.services.Impl;

import com.cbcoders.car_app_v1.Cars.model.DTO.CarDTO;
import com.cbcoders.car_app_v1.Cars.repository.NewCarRepository;
import com.cbcoders.car_app_v1.Cars.repository.UsedCarRepository;
import com.cbcoders.car_app_v1.Cars.services.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
    public List<CarDTO> getAllCarsSortedById() {
        List<CarDTO> allCars = new ArrayList<>();

        List<CarDTO> newCarDTOs = newCarRepository.findAll()
                .stream()
                .map(newCar -> {
                    CarDTO carDTO = modelMapper.map(newCar, CarDTO.class);
                    //carDTO.setUser(newCar.getUser());
                    return carDTO;
                })
                .toList();

        List<CarDTO> usedCarDTOs = usedCarRepository.findAll()
                .stream()
                .map(usedCar -> {
                    CarDTO carDTO = modelMapper.map(usedCar, CarDTO.class);
                    //carDTO.setUser(usedCar.getUser());
                    return carDTO;
                })
                .toList();

        allCars.addAll(newCarDTOs);
        allCars.addAll(usedCarDTOs);
        allCars.sort(Comparator.comparing(CarDTO::getCarId));
        return allCars;
    }


}
