package com.cbcoders.car_app_v1.Cars.services.Impl;

import com.cbcoders.car_app_v1.Cars.model.NewCar;
import com.cbcoders.car_app_v1.Cars.repository.NewCarRepository;
import com.cbcoders.car_app_v1.Cars.services.NewCarService;
import com.cbcoders.car_app_v1.Exceptions.CarAlreadyExistsException;
import com.cbcoders.car_app_v1.Exceptions.CarNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewCarServiceImpl implements NewCarService {
    private final NewCarRepository newCarRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(NewCarServiceImpl.class);

    public NewCarServiceImpl(NewCarRepository newCarRepository) {
        this.newCarRepository = newCarRepository;
    }

    @Override
    public NewCar createNewCar(NewCar newCar) {
        try {
            Optional<NewCar> newCarOptional = newCarRepository.findByChassisNumber(newCar.getChassisNumber());
            if (newCarOptional.isPresent()) {
                throw new CarAlreadyExistsException("Car with chassis number " + newCar.getChassisNumber() + " already exists");
            }
            return newCarRepository.save(newCar);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new CarAlreadyExistsException(e.getMessage());
        }
    }

    @Override
    public NewCar updateNewCar(Long carId, NewCar newCar) {
        Optional<NewCar> newCarOptional = newCarRepository.findById(carId);
        if (newCarOptional.isPresent()) {
            NewCar newCar1 = newCarOptional.get();
            newCar1.setCarId(newCar.getCarId());
            //newCar1.setChassisNumber(newCar.getChassisNumber());
            newCar1.setBrand(newCar.getBrand());
            newCar1.setModel(newCar.getModel());
            newCar1.setColor(newCar.getColor());
            newCar1.setKeysNumber(newCar.getKeysNumber());
            newCar1.setCustomerName(newCar.getCustomerName());
            newCar1.setDateArrived(newCar.getDateArrived());
            newCar1.setSoldOrStock(newCar.getSoldOrStock());
            return newCarRepository.save(newCar1);
        } else {
            throw new CarNotFoundException("Car with id " + carId + " not found");
        }
    }

    @Override
    public void deleteNewCar(Long carId) {
        Optional<NewCar> newCarOptional = newCarRepository.findById(carId);
        if (newCarOptional.isPresent()) {
            newCarRepository.deleteById(newCarOptional.get().getCarId());
        } else {
            throw new CarNotFoundException("Car with id " + carId + " not found");
        }
    }

    @Override
    public NewCar getNewCarById(Long carId) {
        Optional<NewCar> newCarOptional = newCarRepository.findById(carId);
        if (newCarOptional.isPresent()) {
            return newCarOptional.get();
        } else {
            throw new CarNotFoundException("Car with id " + carId + " not found");
        }
    }

    @Override
    public NewCar getNewCarByChassisNumber(String chassisNumber) {
        Optional<NewCar> newCarOptional = newCarRepository.findByChassisNumber(chassisNumber);
        if (newCarOptional.isPresent()) {
            return newCarOptional.get();
        } else {
            throw new CarNotFoundException("Car with chassis number " + chassisNumber + " not found");
        }
    }
}
