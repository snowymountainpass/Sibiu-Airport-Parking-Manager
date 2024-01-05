package com.clockworkcode.sibiuairportparkingmanager.repository;

import com.clockworkcode.sibiuairportparkingmanager.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace,Long> {

    List<ParkingSpace> getParkingSpacesByAirport_AirportCode(String airportCode);
    List<ParkingSpace>  getParkingSpacesBySpaceIsOccupied (Boolean parkingSpaceAvailable);
    ParkingSpace getParkingSpaceByParkingSpaceNumber(String parkingSpaceNumber);

}
