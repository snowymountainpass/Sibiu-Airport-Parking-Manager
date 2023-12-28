package com.clockworkcode.sibiuairportparkingmanager.repository;

import com.clockworkcode.sibiuairportparkingmanager.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport,Integer> {
}
