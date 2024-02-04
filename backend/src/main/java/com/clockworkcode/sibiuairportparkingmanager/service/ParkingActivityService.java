package com.clockworkcode.sibiuairportparkingmanager.service;

import com.clockworkcode.sibiuairportparkingmanager.model.Car;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingActivity;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingSpace;
import com.clockworkcode.sibiuairportparkingmanager.repository.CarRepository;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingActivityRepository;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ParkingActivityService {

    private final CarRepository carRepository;
    private final ParkingSpaceRepository parkingSpaceRepository;
    private final ParkingActivityRepository parkingActivityRepository;

    @Autowired
    public ParkingActivityService(CarRepository carRepository,ParkingSpaceRepository parkingSpaceRepository,ParkingActivityRepository parkingActivityRepository) {
        this.carRepository = carRepository;
        this.parkingSpaceRepository = parkingSpaceRepository;
        this.parkingActivityRepository = parkingActivityRepository;
    }

    public void addCar(String licensePlate) {
        carRepository.save(new Car(licensePlate));
        Car car = carRepository.getCarByCarLicensePlate(licensePlate);

        assignToParkingSpace(car,"SIA");

    }
    public void assignToParkingSpace(Car car,String airportCode){

        ParkingSpace unoccupiedParkingSpace = parkingSpaceRepository.findParkingSpaceByAirport_AirportCodeAndIsOccupied(airportCode, false);
        assignParkingActivity(car,unoccupiedParkingSpace);
        unoccupiedParkingSpace.setOccupied(true);
    }

    public void assignParkingActivity(Car car,ParkingSpace parkingSpace){
        List<ParkingActivity> listParkingActivities = parkingActivityRepository.findParkingActivitiesByParkingSpace(parkingSpace);

        ParkingActivity parkingActivity = new ParkingActivity(parkingSpace,car,new Date());

        listParkingActivities.add(parkingActivity);
    }

    public void setDepartureTime(Car car, Date endTime){
        //add 5 min to the departure time to account for the time it takes to exit the parking lot
        ParkingActivity latestParkingActivity = parkingActivityRepository.findParkingActivityByCarAndStartTimeOrderByStartTimeDesc(car);//find activity (for this car) that has a that has the closest startDate to the current time
        latestParkingActivity.setEndTime(endTime); //TODO: 6) figure out how to add 5 minutes to the end date
    }

    public void deleteCar(String licensePlate){
        carRepository.deleteCarByCarLicensePlate(licensePlate);
    }


}
