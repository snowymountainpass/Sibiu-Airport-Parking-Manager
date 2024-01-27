package com.clockworkcode.sibiuairportparkingmanager.DTO;

public class CarDTO {

    private String licensePlate;
    private boolean paymentFlag;

    // Default constructor for Jackson (used for JSON serialization/deserialization)
    public CarDTO() {
    }

    public CarDTO(String licensePlate, boolean paymentFlag) {
        this.licensePlate = licensePlate;
        this.paymentFlag = paymentFlag;
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

}
