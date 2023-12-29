package com.clockworkcode.sibiuairportparkingmanager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @OneToOne
    @JoinColumn(name = "parking_space_id")
    private ParkingSpace parkingSpace;

    private Timestamp startTime;
    private Timestamp endTime;
    private BigDecimal cost;
    private Timestamp paymentDateTime;

    public Payment(ParkingSpace parkingSpace, Timestamp startTime, Timestamp endTime, BigDecimal cost, Timestamp paymentDateTime) {
        this.parkingSpace = parkingSpace;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = cost;
        this.paymentDateTime = paymentDateTime;
    }

    public Payment() {
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Timestamp getPaymentDateTime() {
        return paymentDateTime;
    }

    public void setPaymentDateTime(Timestamp paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
    }
}
