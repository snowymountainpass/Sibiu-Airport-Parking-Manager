package com.clockworkcode.sibiuairportparkingmanager.service;

import com.clockworkcode.sibiuairportparkingmanager.model.Car;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingActivity;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingCost;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ParkingCostService {

    private final ParkingCostRepository parkingCostRepository;
    private final ParkingActivityService parkingActivityService;

    @Autowired
    public ParkingCostService(ParkingCostRepository parkingCostRepository,ParkingActivityService parkingActivityService){
        this.parkingCostRepository=parkingCostRepository;
        this.parkingActivityService=parkingActivityService;
    }

    public long calculateAmountToBePaid(ParkingActivity parkingActivity){
        Long airportCostPerMinute = parkingActivity.getParkingSpace().getAirport().getCostPerMinute();
        Date startTime = parkingActivity.getStartTime();
        Date endTime = parkingActivity.getEndTime();
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

        ParkingCost currentParkingCost = parkingCostRepository.findParkingCostByParkingActivity(activity);

        if(currentParkingCost==null){
            ParkingCost parkingCost = new ParkingCost(activity.getParkingSpace(),activity,0L);
            System.out.println("V1"+"--"+LocalDateTime.now()+" - "+parkingCost.getParkingActivity().getActivityId()+" - " +parkingCost.getParkingActivity().getCar()+"--"+parkingCost.getParkingActivity().getStartTime()+"- -"+parkingCost.getParkingActivity().getEndTime());
            parkingCost.setAmount(calculateAmountToBePaid(activity));
            parkingCostRepository.save(parkingCost);
            System.out.println("V1"+"--"+LocalDateTime.now()+" - "+parkingCost.getParkingActivity().getActivityId());
        }
        else{
            System.out.println("V2"+"--"+LocalDateTime.now()+" - "+currentParkingCost.getParkingActivity().getActivityId());
            currentParkingCost.setAmount(calculateAmountToBePaid(activity));
            parkingCostRepository.save(currentParkingCost);
            System.out.println("V2"+"--"+LocalDateTime.now()+" - "+currentParkingCost.getParkingActivity().getActivityId());
        }
    }

}
