package com.clockworkcode.sibiuairportparkingmanager.service;

import com.clockworkcode.sibiuairportparkingmanager.model.Car;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingActivity;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingSpace;
import com.clockworkcode.sibiuairportparkingmanager.repository.CarRepository;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingActivityRepository;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final ParkingSpaceRepository parkingSpaceRepository;
    private final ParkingActivityRepository parkingActivityRepository;

    @Autowired
    public CarService(CarRepository carRepository,ParkingSpaceRepository parkingSpaceRepository,ParkingActivityRepository parkingActivityRepository) {
        this.carRepository = carRepository;
        this.parkingSpaceRepository = parkingSpaceRepository;
        this.parkingActivityRepository = parkingActivityRepository;
    }

    public Car getCarByLicensePlate(String licensePlate){
        Car car = null;
        try {
            car = carRepository.getCarByCarLicensePlate(licensePlate);
        }catch (NullPointerException e){
            System.out.println(licensePlate+" doesn't exist!");
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return car;
    }

    public void addCar(String licensePlate) {
        carRepository.save(new Car(licensePlate));
        assignToParkingSpace(licensePlate,"SIA");
    }
    public void deleteCar(String licensePlate){
        carRepository.deleteCarByCarLicensePlate(licensePlate);
    }

    public void assignToParkingSpace(String licensePlate,String airportCode){

        Car car = carRepository.getCarByCarLicensePlate(licensePlate);

        ParkingSpace unoccupiedParkingSpace = parkingSpaceRepository.findParkingSpaceByAirport_AirportCodeAndIsOccupied(airportCode, false);
        //TODO: 1)refactor with ParkingSpaceService class
        addParkingActivity(car,unoccupiedParkingSpace);
    }

    public void addParkingActivity(Car car,ParkingSpace parkingSpace){
        List<ParkingActivity> listParkingActivities = parkingActivityRepository.findParkingActivitiesByParkingSpace(parkingSpace);

        ParkingActivity parkingActivity = new ParkingActivity(parkingSpace,car,new Date());

        listParkingActivities.add(parkingActivity);
    }


}
