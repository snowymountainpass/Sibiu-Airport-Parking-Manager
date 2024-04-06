package com.clockworkcode.sibiuairportparkingmanager.DTO;

import java.util.UUID;

public class ParkingSpaceDTO {

    private String id;
    private String parkingSpaceName;
    private String airportName;

    public ParkingSpaceDTO(String airportName, String parkingSpaceName) {
        this.id = UUID.randomUUID().toString();
        this.airportName = airportName;
        this.parkingSpaceName = parkingSpaceName;
    }

    public ParkingSpaceDTO() {
    }

    public String getId() {
        return id;
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
