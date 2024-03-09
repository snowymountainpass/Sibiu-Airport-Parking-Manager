package com.clockworkcode.sibiuairportparkingmanager.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String carid;

    private String carLicensePlate;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParkingActivity> parkingActivities = new ArrayList<>();

    public Car(String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
    }

    public Car() {
    }

    public String getCarId() {
        return carid;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }


    public void setCarLicensePlate(String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
    }
}