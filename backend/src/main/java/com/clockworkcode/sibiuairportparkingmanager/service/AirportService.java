package com.clockworkcode.sibiuairportparkingmanager.service;

import com.clockworkcode.sibiuairportparkingmanager.model.Airport;
import com.clockworkcode.sibiuairportparkingmanager.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public Airport getAirportByAirportCode(String airportCode){
        return airportRepository.getAirportByAirportCode(airportCode);
    }

    public Airport getAiportByAirportName(String airportName){
        return airportRepository.getAirportByAirportName(airportName);
    }
}
