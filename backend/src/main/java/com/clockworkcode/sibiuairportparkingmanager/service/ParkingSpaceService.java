package com.clockworkcode.sibiuairportparkingmanager.service;

import com.clockworkcode.sibiuairportparkingmanager.model.ParkingSpace;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpaceService {

    private final ParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    public ParkingSpaceService(ParkingSpaceRepository parkingSpaceRepository) {
        this.parkingSpaceRepository = parkingSpaceRepository;
    }

    public ParkingSpace getParkingSpaceByParkingSpaceNumber(String airportCode,String parkingSpaceNumber){

        return parkingSpaceRepository.findParkingSpaceByAirport_AirportCodeAndParkingSpaceNumber(airportCode,parkingSpaceNumber);
    }

    public boolean isParkingSpaceOccupied(String airportCode,String parkingSpaceNumber){
        ParkingSpace parkingSpace = getParkingSpaceByParkingSpaceNumber(airportCode,parkingSpaceNumber);

        return parkingSpace.getOccupied();
    }

    public List<ParkingSpace> getParkingSpacesByByAirportCode(String airportCode){
        return parkingSpaceRepository.findParkingSpacesByAirport_AirportCode(airportCode);
    }

}