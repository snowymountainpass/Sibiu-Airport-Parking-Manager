package com.clockworkcode.sibiuairportparkingmanager.repository;

import com.clockworkcode.sibiuairportparkingmanager.model.ParkingActivity;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingCost;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingSpace;
import com.clockworkcode.sibiuairportparkingmanager.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingCostRepository extends JpaRepository<ParkingCost,Long> {

    ParkingCost findParkingCostByParkingActivity(ParkingActivity parkingActivity);
//    ParkingCost findParkingCostByParkingSpaceAndParkingActivity(ParkingSpace parkingSpace, ParkingActivity parkingActivity);
//
//    ParkingCost findParkingCostByParkingSpaceAndParkingActivityAndPayment(ParkingSpace parkingSpace, ParkingActivity parkingActivity, Payment payment);



}
