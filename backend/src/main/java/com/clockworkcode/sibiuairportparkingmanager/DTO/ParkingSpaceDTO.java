package com.clockworkcode.sibiuairportparkingmanager.DTO;

public class ParkingSpaceDTO {

    private String parkingSpaceName;
    private String airportName;

    public ParkingSpaceDTO() {
    }

    public String getParkingSpaceName() {
        return parkingSpaceName;
    }

    public void setParkingSpaceName(String parkingSpaceName) {
        this.parkingSpaceName = parkingSpaceName;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }
}
