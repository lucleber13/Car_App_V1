package com.cbcoders.car_app_v1.Cars.services.Impl;

import com.cbcoders.car_app_v1.Cars.model.Car;
import com.cbcoders.car_app_v1.Cars.model.DTO.CarDTO;
import com.cbcoders.car_app_v1.Cars.model.DTO.NewCarDTO;
import com.cbcoders.car_app_v1.Cars.model.DTO.UsedCarDTO;
import com.cbcoders.car_app_v1.Cars.model.Enums.CarNewOrUsed;
import com.cbcoders.car_app_v1.Cars.model.NewCar;
import com.cbcoders.car_app_v1.Cars.model.UsedCar;
import com.cbcoders.car_app_v1.Cars.repository.CarRepository;
import com.cbcoders.car_app_v1.Cars.repository.NewCarRepository;
import com.cbcoders.car_app_v1.Cars.repository.UsedCarRepository;
import com.cbcoders.car_app_v1.Cars.services.CarService;
import com.cbcoders.car_app_v1.Exceptions.CarNotFoundException;
import com.cbcoders.car_app_v1.Users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private final NewCarRepository newCarRepository;
    private final UsedCarRepository usedCarRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public CarServiceImpl(NewCarRepository newCarRepository, UsedCarRepository usedCarRepository, CarRepository carRepository,
                          UserRepository userRepository, ModelMapper modelMapper) {
        this.newCarRepository = newCarRepository;
        this.usedCarRepository = usedCarRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Car getCarById(Long carId) {
        try {
            Optional<Car> carOptional = carRepository.findById(carId);
            if (carOptional.isEmpty()) {
                throw new CarNotFoundException("Car with id " + carId + " not found");
            }
            Car car = carOptional.get();
            if (car.getCarNewOrUsed().equals(CarNewOrUsed.NEW)) {
                return modelMapper.map(car, NewCar.class);
            } else if (car.getCarNewOrUsed().equals(CarNewOrUsed.USED)) {
                return modelMapper.map(car, UsedCar.class);
            }
            return null;
        } catch (Exception e) {
            throw new CarNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<CarDTO> getAllCars() {
        try {

	        List<CarDTO> cars = carRepository.findAll()
                    .stream()
                    .map(car -> {
                        CarDTO carDTO = modelMapper.map(car, CarDTO.class);
                        return carDTO;
                    })
                    .toList();
	        List<CarDTO> allCars = new ArrayList<>(cars);
            allCars.sort(Comparator.comparing(CarDTO::getCarId));
            return allCars;
        } catch (Exception e) {
            throw new CarNotFoundException(e.getMessage());
        }
    }


    @Override
    public CarDTO getCarByChassisNumber(String chassisNumber) {
        try {
            Optional<Car> carOptional = carRepository.findByChassisNumberIgnoreCase(chassisNumber);
            if (carOptional.isEmpty()) {
                throw new CarNotFoundException("Car with chassis number " + chassisNumber + " not found");
            }
            return modelMapper.map(carOptional.get(), CarDTO.class);
        } catch (Exception e) {
            throw new CarNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<CarDTO> getCarsByModel(String model) {
        try {
            List<CarDTO> cars = carRepository.findAllByModelContainsIgnoreCase(model)
                    .stream()
                    .map(car -> {
                        CarDTO carDTO = modelMapper.map(car, CarDTO.class);
                        return carDTO;
                    })
                    .toList();
            if (cars.isEmpty()) {
                throw new CarNotFoundException("Car with model " + model + " not found");
            }
            List<CarDTO> allCars = new ArrayList<>(cars);
            allCars.sort(Comparator.comparing(CarDTO::getCarId));
            return allCars;
        } catch (Exception e) {
            throw new CarNotFoundException(e.getMessage());
        }
    }

    @Override
    public CarDTO getCarByRegNumber(String regNumber) {
        try {
            Optional<UsedCar> usedCarOptional = usedCarRepository.findByRegNumberIgnoreCase(regNumber);
            if (usedCarOptional.isEmpty()) {
                throw new CarNotFoundException("Car with registration number " + regNumber + " not found");
            }
            return modelMapper.map(usedCarOptional.get(), CarDTO.class);
        } catch (Exception e) {
            throw new CarNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<CarDTO> getCarByDateArrived(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date dateArrived = dateFormat.parse(dateString);

            List<CarDTO> cars = carRepository.findAllByDateArrived(dateArrived)
                    .stream()
                    .map(car -> modelMapper.map(car, CarDTO.class))
                    .collect(Collectors.toList());

            if (cars.isEmpty()) {
                throw new CarNotFoundException("Car with arrived date " + dateString + " not found");
            }

            cars.sort(Comparator.comparing(CarDTO::getCarId));
            return cars;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please provide the date in dd-MM-yyyy format.");
        } catch (Exception e) {
            throw new CarNotFoundException(e.getMessage());
        }
    }


    @Override
    public void deleteCar(Long carId) {
        try {
            Optional<NewCar> newCarOptional = newCarRepository.findById(carId);
            Optional<UsedCar> usedCarOptional = usedCarRepository.findById(carId);
            if (newCarOptional.isEmpty() && usedCarOptional.isEmpty()) {
                throw new CarNotFoundException("Car with id " + carId + " not found");
            }
            if (newCarOptional.isPresent()) {
                newCarRepository.deleteById(carId);
            } else {
                usedCarRepository.deleteById(carId);
            }
        } catch (Exception e) {
            throw new CarNotFoundException(e.getMessage());
        }
    }
}
