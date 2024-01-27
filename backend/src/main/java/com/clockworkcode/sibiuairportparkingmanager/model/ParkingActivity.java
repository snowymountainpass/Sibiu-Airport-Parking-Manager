package com.clockworkcode.sibiuairportparkingmanager.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ParkingActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    @ManyToOne
    @JoinColumn(name = "parking_space_id")
    private ParkingSpace parkingSpace;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    public ParkingActivity(ParkingSpace parkingSpace, Car car, Date startTime) {
        this.parkingSpace = parkingSpace;
        this.car = car;
        this.startTime = startTime;
    }

    public ParkingActivity() {}

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public Car getCar() {
        return car;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
