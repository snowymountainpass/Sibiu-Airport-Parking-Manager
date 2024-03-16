package com.clockworkcode.sibiuairportparkingmanager.service;

import com.clockworkcode.sibiuairportparkingmanager.model.Car;
import com.clockworkcode.sibiuairportparkingmanager.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car getCarByLicensePlate(String licensePlate){
        try {
            return carRepository.getCarByCarLicensePlate(licensePlate);
        }catch (NullPointerException e){
            System.out.println(Arrays.toString(e.getStackTrace()));
            return null;
        }
    }
}
