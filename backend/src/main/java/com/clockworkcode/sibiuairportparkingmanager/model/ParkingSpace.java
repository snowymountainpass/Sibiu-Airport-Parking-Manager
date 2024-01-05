package com.clockworkcode.sibiuairportparkingmanager.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ParkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingSpaceId;

    private String parkingSpaceNumber;

    private boolean spaceIsOccupied;
    private Date startTime;

    @ManyToOne
    @JoinColumn
    private Airport airport;

    @OneToOne
    @JoinColumn
    private Car parkedCar;


    public ParkingSpace(String parkingNumber, boolean spaceIsOccupied, Airport airport) {
        this.parkingSpaceNumber = parkingNumber;
        this.spaceIsOccupied = spaceIsOccupied;
        this.airport = airport;
    }

    public ParkingSpace(String parkingNumber, boolean spaceIsOccupied, Airport airport, Car parkedCar) {
        this.parkingSpaceNumber = parkingNumber;
        this.spaceIsOccupied = spaceIsOccupied;
        this.airport = airport;
        this.parkedCar = parkedCar;
    }

    public ParkingSpace() {
    }

    public String getParkingSpaceNumber() {
        return parkingSpaceNumber;
    }

    public void setParkingSpaceNumber(String parkingSpaceNumber) {
        this.parkingSpaceNumber = parkingSpaceNumber;
    }

    public boolean isSpaceOccupied() {
        return spaceIsOccupied;
    }

    public void setOccupied(boolean spaceIsOccupied) {
        this.spaceIsOccupied = spaceIsOccupied;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Car getParkedCar() {
        return parkedCar;
    }

    public void setParkedCar(Car parkedCar) {
        this.parkedCar = parkedCar;
    }


}
