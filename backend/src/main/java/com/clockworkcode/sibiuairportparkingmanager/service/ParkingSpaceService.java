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

    public ParkingSpace getParkingSpaceByParkingSpaceNumber(String parkingSpaceNumber){
        return null;
    }

    public boolean isParkingSpaceOccupied(String parkingSpaceNumber, boolean isOccupied){
        ParkingSpace parkingSpace = getParkingSpaceByParkingSpaceNumber(parkingSpaceNumber);

//        return parkingSpace.isSpaceOccupied();
        return true;
    }

    public List<ParkingSpace> getParkingSpacesByByAirportCode(String airportCode){
        return null;
    }

}
//TODO: 2)see todo 1