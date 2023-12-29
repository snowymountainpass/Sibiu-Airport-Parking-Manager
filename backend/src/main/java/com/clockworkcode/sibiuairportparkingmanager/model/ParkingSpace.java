package com.clockworkcode.sibiuairportparkingmanager.model;

import jakarta.persistence.*;

@Entity
public class ParkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingSpaceId;

    private String parkingNumber;

    private boolean parkingSpaceAvailable;

    @ManyToOne
    @JoinColumn
    private Airport airport;

    @OneToOne(mappedBy = "parkingSpace")
    private Car car;

    public ParkingSpace(String parkingNumber, boolean parkingSpaceAvailable, Airport airport) {
        this.parkingNumber = parkingNumber;
        this.parkingSpaceAvailable = parkingSpaceAvailable;
        this.airport = airport;
    }

    public ParkingSpace(String parkingNumber, boolean parkingSpaceAvailable, Airport airport, Car car) {
        this.parkingNumber = parkingNumber;
        this.parkingSpaceAvailable = parkingSpaceAvailable;
        this.airport = airport;
        this.car = car;
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

    public boolean isParkingSpaceAvailable() {
        return parkingSpaceAvailable;
    }

    public void setParkingSpaceAvailable(boolean parkingSpaceAvailable) {
        this.parkingSpaceAvailable = parkingSpaceAvailable;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
