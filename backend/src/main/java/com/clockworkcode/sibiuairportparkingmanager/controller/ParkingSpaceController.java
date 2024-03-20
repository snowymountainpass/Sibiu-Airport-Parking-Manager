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

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/parkingSpaces")
@CrossOrigin
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

    @PostMapping("/getParkingSpaces/{airportName}")
    public ResponseEntity<Map<String,List<String>>> getParkingSpacesByAirport(@PathVariable String airportName){
        Map<String,List<String>> responseMap = new HashMap<>();

        List<ParkingSpace> parkingSpaces = parkingSpaceService.getEmptyParkingSpacesByAirportName(airportName);
        List<String> listParkingSpaceNames =parkingSpaces.stream().map(parkingSpace -> parkingSpace.getParkingSpaceNumber()).collect(Collectors.toList());
        responseMap.put("result",listParkingSpaceNames);
        //TODO: if list is empty
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

}
