package com.clockworkcode.sibiuairportparkingmanager.DTO;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ParkingActivityDTO {
    private String id;
    private String licensePlate;
    private String airportName;
    private String parkingSpaceName;
    private Date startTime;
    private String timeSpent;

    public ParkingActivityDTO(String licensePlate, String airportName, String parkingSpaceName, Date startTime) {
        this.id = UUID.randomUUID().toString();
        this.licensePlate = licensePlate;
        this.airportName = airportName;
        this.parkingSpaceName = parkingSpaceName;
        this.startTime = startTime;
        this.timeSpent=setTimeSpent();
    }

    public ParkingActivityDTO() {
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getAirportName() {
        return airportName;
    }

    public String getParkingSpaceName() {
        return parkingSpaceName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public String getId() {
        return id;
    }

    public String getTimeSpent() {
        return timeSpent;
    }

    public String setTimeSpent(){
        long differenceInMilliseconds = Math.abs(new Date().getTime()-this.startTime.getTime());
        long differenceInMinutes = TimeUnit.MINUTES.convert(differenceInMilliseconds, TimeUnit.MILLISECONDS);

        return Long.toString(differenceInMinutes);
    }


}
