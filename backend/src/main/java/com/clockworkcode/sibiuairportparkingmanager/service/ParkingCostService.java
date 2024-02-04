package com.clockworkcode.sibiuairportparkingmanager.service;

import com.clockworkcode.sibiuairportparkingmanager.model.Airport;
import com.clockworkcode.sibiuairportparkingmanager.model.Car;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingActivity;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingCost;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingActivityRepository;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class ParkingCostService {

    private final ParkingCostRepository parkingCostRepository;
    private final ParkingActivityRepository parkingActivityRepository;

    @Autowired
    public ParkingCostService(ParkingCostRepository parkingCostRepository,ParkingActivityRepository parkingActivityRepository){
        this.parkingCostRepository=parkingCostRepository;
        this.parkingActivityRepository=parkingActivityRepository;
    }

    public Float getAmountToBePaid(Car car){

        ParkingActivity carParkingActivity = parkingActivityRepository.findParkingActivityByCarAndStartTimeOrderByStartTimeDesc(car);

        Float airportCostPerMinute = carParkingActivity.getParkingSpace().getAirport().getCostPerMinute();

        Date startTime = carParkingActivity.getStartTime();
        Date endTime = carParkingActivity.getEndTime();
        long differenceInMinutes=0;
        if(startTime!=null && endTime!=null){
            long diffInMilliseconds = Math.abs(endTime.getTime() - startTime.getTime());
            differenceInMinutes= TimeUnit.DAYS.convert(diffInMilliseconds, TimeUnit.MINUTES)+5; //5 is an arbitrary value that represents
                                                                                                // the amount of time necessary to exit the parking lot
        }
        return differenceInMinutes*airportCostPerMinute;
    }

    public void addParkingCostForCar(Car car){

        ParkingActivity activity = parkingActivityRepository.findParkingActivityByCarAndStartTimeOrderByStartTimeDesc(car);

        ParkingCost cost = parkingCostRepository.findParkingCostByParkingActivity(activity);

        cost.setAmount(getAmountToBePaid(car));
    }

    //TODO: 5) here we have to add a method which handles
    // the calculation for the amount to be paid
    // amount will be initiated when ParkingActivity startTime is initiated - DONE (04.02.2024)

}
