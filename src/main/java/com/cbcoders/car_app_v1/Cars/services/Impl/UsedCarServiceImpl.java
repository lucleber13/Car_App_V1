package com.cbcoders.car_app_v1.Cars.services.Impl;

import com.cbcoders.car_app_v1.Cars.model.UsedCar;
import com.cbcoders.car_app_v1.Cars.repository.UsedCarRepository;
import com.cbcoders.car_app_v1.Cars.services.UsedCarService;
import com.cbcoders.car_app_v1.Exceptions.CarAlreadyExistsException;
import com.cbcoders.car_app_v1.Exceptions.CarNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsedCarServiceImpl implements UsedCarService {
    private final UsedCarRepository usedCarRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UsedCarServiceImpl.class);

    public UsedCarServiceImpl(UsedCarRepository usedCarRepository) {
        this.usedCarRepository = usedCarRepository;
    }

    @Override
    public UsedCar createUsedCar(UsedCar usedCar) {
        try {
            Optional<UsedCar> usedCarOptional = usedCarRepository.findByChassisNumber(usedCar.getChassisNumber());
            if (usedCarOptional.isPresent()) {
                throw new CarAlreadyExistsException("Car with chassis number " + usedCar.getChassisNumber() + " already exists");
            }
            Optional<UsedCar> usedCarOptional1 = usedCarRepository.findByRegNumber(usedCar.getRegNumber());
            if (usedCarOptional1.isPresent()) {
                throw new CarAlreadyExistsException("Car with registration number " + usedCar.getRegNumber() + " already exists");
            }
            return usedCarRepository.save(usedCar);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new CarAlreadyExistsException(e.getMessage());
        }
    }

    @Override
    public UsedCar updateUsedCar(Long carId, UsedCar usedCar) {
        Optional<UsedCar> usedCarOptional = usedCarRepository.findById(carId);
        if (usedCarOptional.isPresent()) {
            UsedCar usedCar1 = usedCarOptional.get();
            usedCar1.setCarId(usedCar.getCarId());
            //usedCar1.setChassisNumber(usedCar.getChassisNumber());
            usedCar1.setBrand(usedCar.getBrand());
            usedCar1.setModel(usedCar.getModel());
            usedCar1.setColor(usedCar.getColor());
            usedCar1.setKeysNumber(usedCar.getKeysNumber());
            usedCar1.setDateArrived(usedCar.getDateArrived());
            usedCar1.setSoldOrStock(usedCar.getSoldOrStock());
            usedCar1.setMileage(usedCar.getMileage());
            //usedCar1.setRegNumber(usedCar.getRegNumber());
            return usedCarRepository.save(usedCar1);
        } else {
            throw new CarNotFoundException("Car with id " + carId + " not found");
        }
    }

    @Override
    public void deleteUsedCar(Long carId) {
        Optional<UsedCar> usedCarOptional = usedCarRepository.findById(carId);
        if (usedCarOptional.isPresent()) {
            usedCarRepository.deleteById(carId);
        } else {
            throw new CarNotFoundException("Car with id " + carId + " not found");
        }
    }

    @Override
    public UsedCar getUsedCarByChassisNumber(String chassisNumber) {
        Optional<UsedCar> usedCarOptional = usedCarRepository.findByChassisNumber(chassisNumber);
        if (usedCarOptional.isPresent()) {
            return usedCarOptional.get();
        } else {
            throw new CarNotFoundException("Car with chassis number " + chassisNumber + " not found");
        }
    }

    @Override
    public UsedCar getUsedCarById(Long carId) {
        Optional<UsedCar> usedCarOptional = usedCarRepository.findById(carId);
        if (usedCarOptional.isPresent()) {
            return usedCarOptional.get();
        } else {
            throw new CarNotFoundException("Car with id " + carId + " not found");
        }
    }

    @Override
    public UsedCar getUsedCarByRegNumber(String regNumber) {
        Optional<UsedCar> usedCarOptional = usedCarRepository.findByRegNumber(regNumber);
        if (usedCarOptional.isPresent()) {
            return usedCarOptional.get();
        } else {
            throw new CarNotFoundException("Car with registration number " + regNumber + " not found");
        }
    }
}
