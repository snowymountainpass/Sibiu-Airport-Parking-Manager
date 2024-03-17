package com.clockworkcode.sibiuairportparkingmanager.repository;

import com.clockworkcode.sibiuairportparkingmanager.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace,Long> {

    List<ParkingSpace> findParkingSpacesByAirport_AirportCode(String airportCode);
    List<ParkingSpace> findParkingSpacesByAirport_AirportNameAndIsOccupied(String airportName,boolean isOccupied);

    ParkingSpace findParkingSpaceByAirport_AirportCodeAndParkingSpaceNumber(String airportCode,String parkingSpaceNumber);
    ParkingSpace findParkingSpaceByAirport_AirportNameAndParkingSpaceNumber(String airportName,String parkingSpaceNumber);

    ParkingSpace findFirstByAirport_AirportCodeAndIsOccupied(String airportCode,Boolean isOccupied);
    ParkingSpace findParkingSpaceByAirport_AirportNameAndIsOccupied(String airportName,Boolean isOccupied);

    Integer countParkingSpaceByAirport_AirportCode(String airportCode);
    Integer countParkingSpaceByAirport_AirportName(String airportName);

    Integer countParkingSpaceByAirport_AirportCodeAndIsOccupiedFalse(String airportCode);
    Integer countParkingSpaceByAirport_AirportNameAndIsOccupiedFalse(String airportName);

}

