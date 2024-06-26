package com.clockworkcode.sibiuairportparkingmanager.controller;

import com.clockworkcode.sibiuairportparkingmanager.DTO.CarDTO;
import com.clockworkcode.sibiuairportparkingmanager.model.Car;
import com.clockworkcode.sibiuairportparkingmanager.service.CarService;
import com.clockworkcode.sibiuairportparkingmanager.service.ParkingActivityService;
import com.clockworkcode.sibiuairportparkingmanager.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.*;

@RestController
@RequestMapping("/parkedCars")
@CrossOrigin
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private ParkingActivityService parkingActivityService;

    private static final Logger logger = Logger.getLogger(CarController.class.getName());

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
    public ResponseEntity<Map<String,Boolean>> addCar(@RequestBody CarDTO carDTO){
        Map<String, Boolean> responseMap = new HashMap<>();
        if(Validator.isValidPattern(carDTO.getLicensePlate(),Validator.licensePlateValidPattern)){
            parkingActivityService.addCar(carDTO.getLicensePlate().toUpperCase(),carDTO.getAirportName(),carDTO.getParkingSpaceName());
            logger.info("Car with license plate "+carDTO.getLicensePlate()+" added successfully");
            responseMap.put("validLicensePlate", true);
        }
        else{
            logger.info("Invalid license plate: "+carDTO.getLicensePlate());
            responseMap.put("validLicensePlate", false);
        }
        return new ResponseEntity<>(responseMap, HttpStatus.OK);

    }

}
