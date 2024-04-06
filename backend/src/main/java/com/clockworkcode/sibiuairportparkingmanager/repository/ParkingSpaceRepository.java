package com.clockworkcode.sibiuairportparkingmanager.repository;

import com.clockworkcode.sibiuairportparkingmanager.DTO.ParkingSpaceDTO;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace,Long> {

    List<ParkingSpace> findParkingSpacesByAirport_AirportCode(String airportCode);
    List<ParkingSpace> findParkingSpacesByAirport_AirportNameAndIsOccupied(String airportName,boolean isOccupied);

    ParkingSpace findParkingSpaceByAirport_AirportCodeAndParkingSpaceNumber(String airportCode,String parkingSpaceNumber);
    ParkingSpace findParkingSpaceByAirport_AirportNameAndParkingSpaceNumber(String airportName,String parkingSpaceNumber);


    @Query("""
       SELECT new com.clockworkcode.sibiuairportparkingmanager.DTO.ParkingSpaceDTO(
           a.airportName,
           ps.parkingSpaceNumber
       )
       FROM Airport a
       JOIN ParkingSpace ps on a.airportId = ps.airport.airportId
       WHERE ps.isOccupied = false
       """)
    List<ParkingSpaceDTO> getEmptyParkingSpaces();

}

