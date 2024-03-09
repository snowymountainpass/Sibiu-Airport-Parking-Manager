package com.clockworkcode.sibiuairportparkingmanager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class ParkingCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingCostId;

    @OneToOne
    @JoinColumn(name = "parking_activity_id")
    private ParkingActivity parkingActivity;

    private Long amount;

    @OneToOne(mappedBy = "parkingCost", cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment;

    public ParkingCost( ParkingActivity parkingActivity, Long amount) {
        this.parkingActivity = parkingActivity;
        this.amount = amount;

    }
    public ParkingCost() {}

//    public ParkingSpace getParkingSpace() {
//        return parkingSpace;
//    }
//
//    public void setParkingSpace(ParkingSpace parkingSpace) {
//        this.parkingSpace = parkingSpace;
//    }

    public ParkingActivity getParkingActivity() {
        return parkingActivity;
    }

    public void setParkingActivity(ParkingActivity parkingActivity) {
        this.parkingActivity = parkingActivity;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}