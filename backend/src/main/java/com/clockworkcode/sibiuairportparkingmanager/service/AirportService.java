package com.clockworkcode.sibiuairportparkingmanager.service;

import com.clockworkcode.sibiuairportparkingmanager.model.Airport;
import com.clockworkcode.sibiuairportparkingmanager.repository.AirportRepository;
import com.clockworkcode.sibiuairportparkingmanager.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public boolean addAirport(String airportName,String airportCode,Long costPerMinute){
        boolean result=false;
        boolean validAirportName = Validator.isValidPattern(airportName, Validator.airportNameValidPattern);
        boolean validAirportCode = Validator.isValidPattern(airportCode, Validator.airportCodeValidPattern);
        boolean validCost = Validator.isValidPattern(costPerMinute.toString(),Validator.costValidPattern);

        if(validAirportName&&validAirportCode&&validCost){
            airportRepository.save(new Airport(airportName,airportCode, costPerMinute));
            result=true;
        }
        return result;
    }

    public Airport getAirportByAirportCode(String airportCode){
        return airportRepository.getAirportByAirportCode(airportCode);
    }

    public Airport getAiportByAirportName(String airportName){
        return airportRepository.getAirportByAirportName(airportName);
    }

    public List<String> getAllAirportNames(){
//        List<Airport> airportList = airportRepository.getAllByAirportNameExists();
//        List<String> airportNamesList= new ArrayList<>();
//        airportList.forEach(airport -> airportNamesList.add(airport.getAirportName()));
//        return airportNamesList;
        return airportRepository.getAllAirportNames();

    }
}
