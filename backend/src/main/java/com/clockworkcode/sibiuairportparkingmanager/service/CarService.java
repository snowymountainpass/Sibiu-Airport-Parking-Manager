package com.clockworkcode.sibiuairportparkingmanager.service;

import com.clockworkcode.sibiuairportparkingmanager.model.Car;
import com.clockworkcode.sibiuairportparkingmanager.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Optional<Car> getCarByNumberPlate(String numberPlate){
        return Optional.ofNullable(carRepository.getCarByNumberPlate(numberPlate));
    }

    public void deleteCar(String numberPlate){
        carRepository.deleteCarByNumberPlate(numberPlate);
    }

}
