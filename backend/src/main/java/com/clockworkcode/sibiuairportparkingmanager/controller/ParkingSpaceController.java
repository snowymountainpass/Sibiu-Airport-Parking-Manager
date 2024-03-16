package com.clockworkcode.sibiuairportparkingmanager.controller;

import com.clockworkcode.sibiuairportparkingmanager.DTO.AirportDTO;
import com.clockworkcode.sibiuairportparkingmanager.DTO.ParkingSpaceDTO;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingSpace;
import com.clockworkcode.sibiuairportparkingmanager.service.ParkingActivityService;
import com.clockworkcode.sibiuairportparkingmanager.service.ParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/addParkingSpace")
    ResponseEntity<Map<String,Boolean>> addAirport(@RequestBody ParkingSpaceDTO parkingSpaceDTO) {
        Map<String, Boolean> responseMap = new HashMap<>();

        if(parkingSpaceService.isParkingSpaceNumberUnique(parkingSpaceDTO.getParkingSpaceName(),parkingSpaceDTO.getAirportName())){
            parkingSpaceService.addParkingSpace(parkingSpaceDTO.getParkingSpaceName(),parkingSpaceDTO.getAirportName());
            responseMap.put("result",true);
        }
        else{
            responseMap.put("result",false);
        }

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}
