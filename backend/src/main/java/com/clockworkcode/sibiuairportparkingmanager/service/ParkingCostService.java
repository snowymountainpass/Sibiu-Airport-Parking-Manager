package com.clockworkcode.sibiuairportparkingmanager.service;

import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingActivityRepository;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingCostService {

    private final ParkingCostRepository parkingCostRepository;
    private final ParkingActivityRepository parkingActivityRepository;

    @Autowired
    public ParkingCostService(ParkingCostRepository parkingCostRepository,ParkingActivityRepository parkingActivityRepository){
        this.parkingCostRepository=parkingCostRepository;
        this.parkingActivityRepository=parkingActivityRepository;
    }

    //TODO: 5) here we have to add a method which handles
    // the calculation for the amount to be paid
    // amount will be initiated when ParkingActivity startTime is initiated

}
