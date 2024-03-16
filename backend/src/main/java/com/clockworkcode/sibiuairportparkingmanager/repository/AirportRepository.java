package com.clockworkcode.sibiuairportparkingmanager.repository;

import com.clockworkcode.sibiuairportparkingmanager.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport,Long> {
    Airport getAirportByAirportCode(String airportCode);
    Airport getAirportByAirportName(String airportName);

    @Query("select a.airportName from Airport a")
    List<String> getAllAirportNames();

}
