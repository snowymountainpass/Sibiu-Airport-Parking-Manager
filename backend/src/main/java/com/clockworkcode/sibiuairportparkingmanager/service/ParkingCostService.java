package com.clockworkcode.sibiuairportparkingmanager.service;

import com.clockworkcode.sibiuairportparkingmanager.model.Car;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingActivity;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingCost;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class ParkingCostService {

    private final ParkingCostRepository parkingCostRepository;
    private final ParkingActivityService parkingActivityService;

    @Autowired
    public ParkingCostService(ParkingCostRepository parkingCostRepository,ParkingActivityService parkingActivityService){
        this.parkingCostRepository=parkingCostRepository;
        this.parkingActivityService=parkingActivityService;
    }

    public long calculateAmountToBePaid(Car car){
        ParkingActivity carParkingActivity =parkingActivityService.getLatestParkingActivity(car);
        Long airportCostPerMinute = carParkingActivity.getParkingSpace().getAirport().getCostPerMinute();
        Date startTime = carParkingActivity.getStartTime();
        Date endTime = carParkingActivity.getEndTime();
        long differenceInMinutes=0;
        if(startTime!=null && endTime!=null){
            long diffInMilliseconds = Math.abs(endTime.getTime() - startTime.getTime());
            differenceInMinutes= TimeUnit.MILLISECONDS.toMinutes(diffInMilliseconds);
        }

        return differenceInMinutes*airportCostPerMinute;
    }

    public Long getAmountToBePaid(Car car){
        ParkingActivity carParkingActivity =parkingActivityService.getLatestParkingActivity(car);
        ParkingCost parkingActivityCost = parkingCostRepository.findParkingCostByParkingActivity(carParkingActivity);

        return parkingActivityCost.getAmount();
    }

    public void addParkingCostForCar(ParkingActivity activity){
        ParkingCost parkingCost = new ParkingCost(activity,0L);
        parkingCost.setAmount(calculateAmountToBePaid(activity.getCar()));
        parkingCostRepository.save(parkingCost);
    }
}
