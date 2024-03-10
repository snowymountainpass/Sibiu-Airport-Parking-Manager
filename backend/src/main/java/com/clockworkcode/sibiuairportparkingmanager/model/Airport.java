package com.clockworkcode.sibiuairportparkingmanager.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airportId;

    private String airportName;
    private String airportCode;
    private Long costPerMinute;

    @OneToMany(mappedBy = "airport", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ParkingSpace> parkingSpaces = new ArrayList<>();

    public Long getCostPerMinute() {
        return costPerMinute;
    }

    public Airport(String airportName, String airportCode, Long costPerMinute, List<ParkingSpace> parkingSpaces) {
        this.airportName = airportName;
        this.airportCode = airportCode;
        this.costPerMinute = costPerMinute;
        this.parkingSpaces = parkingSpaces;
    }

    public Airport() {}

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String aiportName) {
        this.airportName = aiportName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String aiportCode) {
        this.airportCode = aiportCode;
    }

    public void setCostPerMinute(Long costPerMinute) {
        this.costPerMinute = costPerMinute;
    }

    public List<ParkingSpace> getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(List<ParkingSpace> parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public Long getAirportId() {
        return airportId;
    }
}