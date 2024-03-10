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
@CrossOrigin
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private ParkingActivityService parkingActivityService;

    @GetMapping("/getCarDetails")
    public ResponseEntity<Car> getCarByLicensePlate(@RequestBody CarDTO carDTO){
        Car car = carService.getCarByLicensePlate(carDTO.getLicensePlate());

        if(car!=null){
            return new ResponseEntity<>(car, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addCar")
    public ResponseEntity<String> addCar(@RequestBody CarDTO carDTO){
        parkingActivityService.addCar(carDTO.getLicensePlate());

        return ResponseEntity.ok("Car with license plate "+carDTO.getLicensePlate()+" added successfully");
    }

}
