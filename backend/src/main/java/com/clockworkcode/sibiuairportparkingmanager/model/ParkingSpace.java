package com.clockworkcode.sibiuairportparkingmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ParkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingSpaceId;

    private String parkingNumber;

    private boolean availability;

    public ParkingSpace(String parkingNumber, boolean availability) {
        this.parkingNumber = parkingNumber;
        this.availability = availability;
    }

    public ParkingSpace() {
    }

    public Long getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(Long parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }

    public String getParkingNumber() {
        return parkingNumber;
    }

    public void setParkingNumber(String parkingNumber) {
        this.parkingNumber = parkingNumber;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

}
