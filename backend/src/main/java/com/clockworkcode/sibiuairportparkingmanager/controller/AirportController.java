package com.clockworkcode.sibiuairportparkingmanager.controller;

import com.clockworkcode.sibiuairportparkingmanager.DTO.AirportDTO;
import com.clockworkcode.sibiuairportparkingmanager.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/airports")
@CrossOrigin
public class AirportController {

    @Autowired
    private AirportService airportService;

    @PostMapping("/addAirport")
    public ResponseEntity<Map<String,Boolean>> addAirport(@RequestBody AirportDTO airportDTO){
        Map<String, Boolean> responseMap = new HashMap<>();

        boolean result = airportService.addAirport(airportDTO.getAirportName(), airportDTO.getAirportCode(), Long.parseLong(airportDTO.getCostPerMinute()));

        responseMap.put("result",result);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @GetMapping("/airportsNames")
    public ResponseEntity<Map<String,List<String>>> getAirportNames(){
        Map<String, List<String>> responseMap = new HashMap<>();
        List<String> airportNamesList = airportService.getAllAirportNames();
        responseMap.put("result",airportNamesList);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @GetMapping("/airportsWithEmptyParkingSpaces")
    public ResponseEntity<Map<String,List<String>>> getAirportsWithEmptyParkingSpaces(){
        Map<String, List<String>> responseMap = new HashMap<>();
        List<String> airportNamesList = airportService.getAirportsWithEmptyParkingSpaces();
        responseMap.put("result",airportNamesList);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}
