package com.cbcoders.car_app_v1.Cars.services.Impl;

import com.cbcoders.car_app_v1.Cars.model.DTO.NewCarDTO;
import com.cbcoders.car_app_v1.Cars.model.NewCar;
import com.cbcoders.car_app_v1.Cars.repository.NewCarRepository;
import com.cbcoders.car_app_v1.Cars.services.NewCarService;
import com.cbcoders.car_app_v1.Exceptions.CarAlreadyExistsException;
import com.cbcoders.car_app_v1.Exceptions.CarNotFoundException;
import com.cbcoders.car_app_v1.Users.model.User;
import com.cbcoders.car_app_v1.Users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewCarServiceImpl implements NewCarService {
    private final NewCarRepository newCarRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public NewCarServiceImpl(NewCarRepository newCarRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.newCarRepository = newCarRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public NewCarDTO createNewCar(NewCarDTO newCarDTO) {
        try {
            Optional<NewCar> newCarOptional = newCarRepository.findByChassisNumber(newCarDTO.getChassisNumber());
        if (newCarOptional.isPresent()) {
            throw new CarAlreadyExistsException("Car with chassis number " + newCarDTO.getChassisNumber() + " already exists");
        }
        NewCar newCar = modelMapper.map(newCarDTO, NewCar.class);
        Optional<User> userOptional = userRepository.findById(newCarDTO.getUser().getUserId());
        if (userOptional.isPresent()) {
            newCar.setUser(userOptional.get());
        } else {
            throw new CarNotFoundException("User with id " + newCarDTO.getUser().getUserId() + " not found");
        }
        newCar = newCarRepository.save(newCar);
        return modelMapper.map(newCar, NewCarDTO.class);
        } catch (Exception e) {
            throw new CarNotFoundException("The " + e.getMessage());
        }
    }

    @Override
    public NewCarDTO updateNewCar(Long carId, NewCarDTO newCarDTO) {
        try {
            Optional<NewCar> newCarOptional = newCarRepository.findById(carId);
            if (newCarOptional.isPresent()) {
                NewCar newCar = newCarOptional.get();
                newCar.setBrand(newCarDTO.getBrand());
                newCar.setModel(newCarDTO.getModel());
                newCar.setColor(newCarDTO.getColor());
                newCar.setChassisNumber(newCarDTO.getChassisNumber());
                newCar.setKeysNumber(newCarDTO.getKeysNumber());
                newCar.setDateArrived(newCarDTO.getDateArrived());
                newCar.setCarNewOrUsed(newCarDTO.getCarNewOrUsed());
                newCar.setSold(newCarDTO.getSold());
                newCar = newCarRepository.save(newCar);
                return modelMapper.map(newCar, NewCarDTO.class);
            } else {
                throw new CarNotFoundException("Car with id " + carId + " not found");
            }
        } catch (Exception e) {
            throw new CarAlreadyExistsException(e.getMessage());
        }
    }

    @Override
    public List<NewCarDTO> getNewCarByModel(String model) {
        List<NewCar> newCarList = newCarRepository.findByModelContainsIgnoreCase(model);
        if (newCarList.isEmpty()) {
            throw new CarNotFoundException("No cars found with model " + model);
        }
        return newCarList.stream()
                .map(newCar -> modelMapper.map(newCar, NewCarDTO.class))
                .toList();
    }

}
