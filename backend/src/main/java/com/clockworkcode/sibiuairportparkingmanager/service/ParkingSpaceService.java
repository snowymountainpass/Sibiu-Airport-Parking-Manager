package com.clockworkcode.sibiuairportparkingmanager.service;

import com.clockworkcode.sibiuairportparkingmanager.model.Airport;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingSpace;
import com.clockworkcode.sibiuairportparkingmanager.repository.AirportRepository;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingSpaceRepository;
import com.clockworkcode.sibiuairportparkingmanager.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParkingSpaceService {

    private final ParkingSpaceRepository parkingSpaceRepository;
    private final AirportRepository airportRepository;

    @Autowired
    public ParkingSpaceService(ParkingSpaceRepository parkingSpaceRepository,AirportRepository airportRepository) {
        this.parkingSpaceRepository = parkingSpaceRepository;
        this.airportRepository = airportRepository;
    }
    @Transactional
    public boolean addParkingSpace(String parkingSpaceName,String airportName){
        boolean result = false;

        boolean validParkingSpaceName= Validator.isValidPattern(airportName, Validator.airportNameValidPattern);
        boolean validAirportName= Validator.isValidPattern(parkingSpaceName, Validator.parkingSpaceNamePattern);

        Airport airport=null;
        if(validAirportName){
            airport = airportRepository.getAirportByAirportName(airportName);
        }

        if(validParkingSpaceName && airport!=null){
            ParkingSpace parkingSpace = new ParkingSpace(parkingSpaceName, false, airport);
            parkingSpaceRepository.save(parkingSpace);
            parkingSpaceRepository.flush();
            result=true;
        }

        return result;
    }

    public ParkingSpace getParkingSpaceByParkingSpaceNumber(String airportName,String parkingSpaceNumber){
        return parkingSpaceRepository.findParkingSpaceByAirport_AirportNameAndParkingSpaceNumber(airportName,parkingSpaceNumber);
    }

    public boolean isParkingSpaceNumberUnique(String airportName,String parkingSpaceNumber){
        ParkingSpace parkingSpace = getParkingSpaceByParkingSpaceNumber(airportName, parkingSpaceNumber);
        if(parkingSpace!=null){
            return false;
        }
        return true;
    }

    public List<ParkingSpace> getEmptyParkingSpacesByAirportName(String airportName){
        return parkingSpaceRepository.findParkingSpacesByAirport_AirportNameAndIsOccupied(airportName,false);
    }

}