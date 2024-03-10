package com.clockworkcode.sibiuairportparkingmanager.service;

import com.clockworkcode.sibiuairportparkingmanager.model.Car;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingActivity;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingSpace;
import com.clockworkcode.sibiuairportparkingmanager.repository.CarRepository;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingActivityRepository;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        if(carRepository.getCarByCarLicensePlate(licensePlate)!=null){
            assignToParkingSpace(carRepository.getCarByCarLicensePlate(licensePlate),"SIA");
        }else {
            carRepository.save(new Car(licensePlate));
            Car car = carRepository.getCarByCarLicensePlate(licensePlate);
            assignToParkingSpace(car,"SIA");
        }
    }
    public void assignToParkingSpace(Car car,String airportCode){
        ParkingSpace unoccupiedParkingSpace = parkingSpaceRepository.findFirstByAirport_AirportCodeAndIsOccupied(airportCode, false);
        unoccupiedParkingSpace.setOccupied(true);
        assignParkingActivity(car,unoccupiedParkingSpace);
    }

    public void assignParkingActivity(Car car,ParkingSpace parkingSpace){
        ParkingActivity parkingActivity = new ParkingActivity(parkingSpace,car,new Date());
        parkingActivityRepository.save(parkingActivity);
    }

    public void clearParkingSpace(ParkingActivity parkingActivity){
        parkingActivity.getParkingSpace().setOccupied(false);
        parkingActivityRepository.save(parkingActivity);
    }

    public void setDepartureTime(Car car){
        ParkingActivity latestParkingActivity = getLatestParkingActivity(car);
        //add 5 min to the departure time to account for the time it takes to exit the parking lot
        LocalDateTime extendedEndTime = LocalDateTime.now().plus(Duration.of(5, ChronoUnit.MINUTES));
        Date billableDepartureTime = Date.from(extendedEndTime.atZone(ZoneId.systemDefault()).toInstant());
        latestParkingActivity.setEndTime(billableDepartureTime);
        parkingActivityRepository.save(latestParkingActivity);
    }

    public ParkingActivity getLatestParkingActivity(Car car){
        List<ParkingActivity> parkingActivities = parkingActivityRepository.findParkingActivitiesByCarOrderByStartTimeDesc(car);
        Optional<ParkingActivity> latestParkingActivity = parkingActivities.stream().findFirst();//find activity (for this car) that has the closest startDate to the current time

        return latestParkingActivity.orElse(null);
    }

    public void deleteCar(String licensePlate){
        carRepository.deleteCarByCarLicensePlate(licensePlate);
    }
}
