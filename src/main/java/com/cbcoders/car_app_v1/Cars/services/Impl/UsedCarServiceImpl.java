package com.cbcoders.car_app_v1.Cars.services.Impl;

import com.cbcoders.car_app_v1.Cars.model.DTO.UsedCarDTO;
import com.cbcoders.car_app_v1.Cars.model.UsedCar;
import com.cbcoders.car_app_v1.Cars.repository.UsedCarRepository;
import com.cbcoders.car_app_v1.Cars.services.UsedCarService;
import com.cbcoders.car_app_v1.Exceptions.CarAlreadyExistsException;
import com.cbcoders.car_app_v1.Exceptions.CarNotFoundException;
import com.cbcoders.car_app_v1.Users.model.User;
import com.cbcoders.car_app_v1.Users.repository.UserRepository;
import org.modelmapper.ModelMapper;
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
                Optional<UsedCar> usedCarOptional1 = usedCarRepository.findByRegNumberIgnoreCase(usedCarDTO.getRegNumber());
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
        try {
            Optional<UsedCar> usedCarOptional = usedCarRepository.findById(carId);
            if (usedCarOptional.isPresent()) {
                UsedCar usedCar = usedCarOptional.get();
                usedCar.setBrand(usedCarDTO.getBrand());
                usedCar.setModel(usedCarDTO.getModel());
                usedCar.setColor(usedCarDTO.getColor());
                usedCar.setChassisNumber(usedCarDTO.getChassisNumber());
                usedCar.setKeysNumber(usedCarDTO.getKeysNumber());
                usedCar.setDateArrived(usedCarDTO.getDateArrived());
                usedCar.setCarNewOrUsed(usedCarDTO.getCarNewOrUsed());
                usedCar.setSold(usedCarDTO.getSold());
                usedCar.setRegNumber(usedCarDTO.getRegNumber());
                usedCar.setMileage(usedCarDTO.getMileage());
                usedCar = usedCarRepository.save(usedCar);
                return modelMapper.map(usedCar, UsedCarDTO.class);
            } else {
                throw new CarNotFoundException("Car with id " + carId + " not found");
            }
        } catch (Exception e) {
            throw new CarAlreadyExistsException(e.getMessage());
        }
    }

    @Override
    public UsedCarDTO getUsedCarByRegNumber(String regNumber) {
        try {
            Optional<UsedCar> usedCarOptional = usedCarRepository.findByRegNumberIgnoreCase(regNumber);
            if (usedCarOptional.isPresent()) {
                return modelMapper.map(usedCarOptional.get(), UsedCarDTO.class);
            } else {
                throw new CarNotFoundException("Car with registration number " + regNumber + " not found");
            }
        } catch (Exception e) {
            throw new CarNotFoundException(e.getMessage());
        }
    }
}
