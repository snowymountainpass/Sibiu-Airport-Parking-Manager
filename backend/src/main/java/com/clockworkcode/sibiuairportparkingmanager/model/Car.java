package com.clockworkcode.sibiuairportparkingmanager.model;

import jakarta.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    private String numberPlate;

    @OneToOne(mappedBy = "parkedCar", cascade = CascadeType.ALL)
    private ParkingSpace parkingSpace;

    private boolean paymentFlag;

    public Car(Long carId, String numberPlate) {
        this.carId = carId;
        this.numberPlate = numberPlate;
    }

    public Car(String numberPlate, ParkingSpace parkingSpace, boolean paymentFlag) {
        this.numberPlate = numberPlate;
        this.parkingSpace = parkingSpace;
        this.paymentFlag = paymentFlag;
    }

    public Car() {
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public boolean isPaymentFlag() {
        return paymentFlag;
    }

    public void setPaymentFlag(boolean paymentFlag) {
        this.paymentFlag = paymentFlag;
    }
}
