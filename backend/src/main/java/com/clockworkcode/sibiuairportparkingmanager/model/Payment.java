package com.clockworkcode.sibiuairportparkingmanager.model;

import jakarta.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @OneToOne
    @JoinColumn(name = "parking_cost_id")
    private ParkingCost parkingCost;

    private boolean successful;

    public Payment(ParkingCost parkingCost, boolean successful) {
        this.parkingCost = parkingCost;
        this.successful = successful;
    }

    public Payment() {}

    public ParkingCost getParkingCost() {
        return parkingCost;
    }

    public void setParkingCost(ParkingCost parkingCost) {
        this.parkingCost = parkingCost;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
