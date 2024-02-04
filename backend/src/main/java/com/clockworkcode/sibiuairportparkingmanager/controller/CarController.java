package com.clockworkcode.sibiuairportparkingmanager.controller;

import com.clockworkcode.sibiuairportparkingmanager.DTO.CarDTO;
import com.clockworkcode.sibiuairportparkingmanager.model.Car;
import com.clockworkcode.sibiuairportparkingmanager.service.CarService;
import com.clockworkcode.sibiuairportparkingmanager.service.ParkingActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkedCars")
public class CarController {

    @Autowired
    private CarService carService;
    private ParkingActivityService parkingActivityService;

    @GetMapping("/getCarDetails/{licensePlate}")
    public ResponseEntity<CarDTO> getCarByLicensePlate(@PathVariable String licensePlate){

        Car car = carService.getCarByLicensePlate(licensePlate);

        if(car!=null){
            return new ResponseEntity<>(converToDTO(car), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    private CarDTO converToDTO(Car car){
//        return new CarDTO(car.getNumberPlate(),car.isPaymentFlag());
        return null;
    }

    @PostMapping("/addCar/{licensePlate}")
    public void addCar(@PathVariable String licensePlate){
        parkingActivityService.addCar(licensePlate);
    }



}
