package com.clockworkcode.sibiuairportparkingmanager.model;

import jakarta.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airportId;
    private String numberPlate;

    public Car(Long airportId, String numberPlate) {
        this.airportId = airportId;
        this.numberPlate = numberPlate;
    }

    public Car() {
    }
    @OneToOne
    @JoinColumn(name = "parking_space_id")
    private ParkingSpace parkingSpace;

    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }
}
