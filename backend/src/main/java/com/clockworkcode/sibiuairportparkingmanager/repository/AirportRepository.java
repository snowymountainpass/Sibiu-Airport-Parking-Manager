package com.clockworkcode.sibiuairportparkingmanager.repository;

import com.clockworkcode.sibiuairportparkingmanager.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport,Long> {

    Airport findAirportByAirportId(Long airportId);
    Airport findAirportByAirportIBAN(String airportIban);
    Airport findAirportByAirportLocation (String airportLocation);



}
