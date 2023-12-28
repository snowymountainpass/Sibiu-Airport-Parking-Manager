package com.clockworkcode.sibiuairportparkingmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airportId;
    private String airportLocation;
    private String airportIBAN;

    public Airport(String location, String airportIBAN) {
        this.airportLocation = location;
        this.airportIBAN = airportIBAN;
    }

    public Airport() {
    }

    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    public String getLocation() {
        return airportLocation;
    }

    public void setLocation(String location) {
        this.airportLocation = location;
    }

    public String getAirportIBAN() {
        return airportIBAN;
    }

    public void setAirportIBAN(String airportIBAN) {
        this.airportIBAN = airportIBAN;
    }
}
