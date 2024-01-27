package com.clockworkcode.sibiuairportparkingmanager.DTO;

import java.util.Date;

public class PaymentDetailDTO {

    private String licensePlate;
    private String parkingSpaceNumber;
    private Date startTime;
    private Date endTime;
    private String airportCode;
    private String airportName;

    public PaymentDetailDTO() {
    }

    public PaymentDetailDTO(String licensePlate, String parkingSpaceNumber, Date startTime, Date endTime, String airportCode, String airportName) {
        this.licensePlate = licensePlate;
        this.parkingSpaceNumber = parkingSpaceNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.airportCode = airportCode;
        this.airportName = airportName;
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
}
