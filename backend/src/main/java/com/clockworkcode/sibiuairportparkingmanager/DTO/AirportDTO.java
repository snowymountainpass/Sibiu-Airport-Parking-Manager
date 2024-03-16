package com.clockworkcode.sibiuairportparkingmanager.DTO;

public class AirportDTO {

    private String airportName;
    private String airportCode;
    private String costPerMinute;

    public AirportDTO() {
    }

    public String getAirportName() {
        return airportName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getCostPerMinute() {
        return costPerMinute;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public void setCostPerMinute(String costPerMinute) {
        this.costPerMinute = costPerMinute;
    }
}
