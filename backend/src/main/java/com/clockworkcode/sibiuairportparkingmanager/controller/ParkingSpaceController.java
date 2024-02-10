package com.clockworkcode.sibiuairportparkingmanager.controller;

import com.clockworkcode.sibiuairportparkingmanager.model.ParkingSpace;
import com.clockworkcode.sibiuairportparkingmanager.service.ParkingActivityService;
import com.clockworkcode.sibiuairportparkingmanager.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parkingSpaces")
public class ParkingSpaceController {

    @Autowired
    private ParkingSpaceService parkingSpaceService;
    private ParkingActivityService parkingActivityService;

    @GetMapping("/getParkingSpace/{licensePlate}")
    public ResponseEntity<ParkingSpace> getParkingSpace(@PathVariable String licensePlate){
        return null;
    }




}
