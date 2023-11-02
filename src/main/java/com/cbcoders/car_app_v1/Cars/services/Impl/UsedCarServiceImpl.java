package com.cbcoders.car_app_v1.Cars.services.Impl;

import com.cbcoders.car_app_v1.Cars.model.Car;
import com.cbcoders.car_app_v1.Cars.model.DTO.CarDTO;
import com.cbcoders.car_app_v1.Cars.model.DTO.UsedCarDTO;
import com.cbcoders.car_app_v1.Cars.model.UsedCar;
import com.cbcoders.car_app_v1.Cars.repository.CarRepository;
import com.cbcoders.car_app_v1.Cars.repository.UsedCarRepository;
import com.cbcoders.car_app_v1.Cars.services.UsedCarService;
import com.cbcoders.car_app_v1.Exceptions.CarAlreadyExistsException;
import com.cbcoders.car_app_v1.Exceptions.CarNotFoundException;
import com.cbcoders.car_app_v1.Users.model.User;
import com.cbcoders.car_app_v1.Users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsedCarServiceImpl implements UsedCarService {
    private final UsedCarRepository usedCarRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UsedCarServiceImpl(UsedCarRepository usedCarRepository, ModelMapper modelMapper,
                              UserRepository userRepository) {
        this.usedCarRepository = usedCarRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UsedCarDTO createUsedCar(UsedCarDTO usedCarDTO) {
        try {
            Optional<UsedCar> usedCarOptional = usedCarRepository.findByChassisNumber(usedCarDTO.getChassisNumber());
            if (usedCarOptional.isPresent()) {
                throw new CarAlreadyExistsException("Car with chassis number " + usedCarDTO.getChassisNumber() + " already exists");
            }
            if (usedCarDTO.getRegNumber() != null) {
                Optional<UsedCar> usedCarOptional1 = usedCarRepository.findByRegNumber(usedCarDTO.getRegNumber());
                if (usedCarOptional1.isPresent()) {
                    throw new CarAlreadyExistsException("Car with registration number " + usedCarDTO.getRegNumber() + " already exists");
                }
            }
            UsedCar usedCar = modelMapper.map(usedCarDTO, UsedCar.class);
            Optional<User> userOptional = userRepository.findById(usedCarDTO.getUser().getUserId());
            if (userOptional.isPresent()) {
                usedCar.setUser(userOptional.get());
            } else {
                throw new CarNotFoundException("User with id " + usedCarDTO.getUser().getUserId() + " not found");
            }
            usedCar = usedCarRepository.save(usedCar);
            return modelMapper.map(usedCar, UsedCarDTO.class);
        } catch (Exception e) {
            throw new CarAlreadyExistsException(e.getMessage());
        }
    }

    @Override
    public UsedCarDTO updateUsedCar(Long carId, UsedCarDTO usedCarDTO) {
        return null;
    }

    @Override
    public UsedCarDTO getUsedCarByRegNumber(String regNumber) {
        return null;
    }
}
