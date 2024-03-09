package com.clockworkcode.sibiuairportparkingmanager.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ParkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingSpaceId;

    private String parkingSpaceNumber;
    private Boolean isOccupied;

    @ManyToOne
    @JoinColumn(name = "airport_id")
    private Airport airport;

    @OneToMany(mappedBy = "parkingSpace",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ParkingActivity> parkingActivities = new ArrayList<>();

    public ParkingSpace(String parkingSpaceNumber, Boolean isOccupied, Airport airport) {
        this.parkingSpaceNumber = parkingSpaceNumber;
        this.isOccupied = isOccupied;
        this.airport = airport;
    }

    public ParkingSpace() {
    }

    public String getParkingSpaceNumber() {
        return parkingSpaceNumber;
    }

    public void setParkingSpaceNumber(String parkingSpaceNumber) {
        this.parkingSpaceNumber = parkingSpaceNumber;
    }

    public Boolean getOccupied() {
        return isOccupied;
    }

    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public List<ParkingActivity> getParkingActivities() {
        return parkingActivities;
    }

    public void setParkingActivities(List<ParkingActivity> parkingActivities) {
        this.parkingActivities = parkingActivities;
    }

}
