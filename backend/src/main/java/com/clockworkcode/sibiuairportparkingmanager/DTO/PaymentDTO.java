package com.clockworkcode.sibiuairportparkingmanager.DTO;

import java.util.Date;

public class PaymentDTO {

    private String licensePlate;
    private String parkingSpaceNumber;
    private Date startTime;
    private Date endTime;
    private String airportCode;
    private String airportName;
    private Float billableAmount;

    public PaymentDTO() {
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getParkingSpaceNumber() {
        return parkingSpaceNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setParkingSpaceNumber(String parkingSpaceNumber) {
        this.parkingSpaceNumber = parkingSpaceNumber;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public Float getBillableAmount() {
        return billableAmount;
    }

    public void setBillableAmount(Float billableAmount) {
        this.billableAmount = billableAmount;
    }
}
