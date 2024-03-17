package com.clockworkcode.sibiuairportparkingmanager.DTO;

public class CarDTO {

    private String licensePlate;
    private String airportName;
    private String parkingSpaceName;
    private boolean paymentFlag;

    // Default constructor for Jackson (used for JSON serialization/deserialization)
    public CarDTO() {
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public boolean isPaymentFlag() {
        return paymentFlag;
    }

    public void setPaymentFlag(boolean paymentFlag) {
        this.paymentFlag = paymentFlag;
    }

    public String getAirportName() {return airportName;}

    public void setAirportName(String airportName) {this.airportName = airportName;}

    public String getParkingSpaceName() {return parkingSpaceName;}

    public void setParkingSpaceName(String parkingSpaceName) {this.parkingSpaceName = parkingSpaceName;}
}
