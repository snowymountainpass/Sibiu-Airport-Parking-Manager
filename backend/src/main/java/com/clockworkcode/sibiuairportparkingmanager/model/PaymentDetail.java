package com.clockworkcode.sibiuairportparkingmanager.model;

public class PaymentDetail {
    private String licensePlate;
    private int spaceNumber;
    private double totalCost;

    public PaymentDetail(String licensePlate, int spaceNumber, double totalCost) {
        this.licensePlate = licensePlate;
        this.spaceNumber = spaceNumber;
        this.totalCost = totalCost;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getSpaceNumber() {
        return spaceNumber;
    }

    public void setSpaceNumber(int spaceNumber) {
        this.spaceNumber = spaceNumber;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
