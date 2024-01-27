package com.clockworkcode.sibiuairportparkingmanager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class ParkingCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingCostId;

    @OneToOne
    @JoinColumn(name = "parking_space_id")
    private ParkingSpace parkingSpace;

    @OneToOne
    @JoinColumn(name = "parking_activity_id")
    private ParkingActivity parkingActivity;

    private Float amount;

    @OneToOne(mappedBy = "parkingCost", cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment;

    public ParkingCost(ParkingSpace parkingSpace, ParkingActivity parkingActivity, Float amount) {
        this.parkingSpace = parkingSpace;
        this.parkingActivity = parkingActivity;
        this.amount = amount;

        parkingSpace.setParkingCost(this);
    }
    public ParkingCost() {}

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public ParkingActivity getParkingActivity() {
        return parkingActivity;
    }

    public void setParkingActivity(ParkingActivity parkingActivity) {
        this.parkingActivity = parkingActivity;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}


//TODO: 3) create service & repository class to add a new ParkingCost (add the moment when the endTime is set
// TODO: 4) must be sent (together with Car object) to the frontend