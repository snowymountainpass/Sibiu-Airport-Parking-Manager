package com.clockworkcode.sibiuairportparkingmanager.controller;

import com.clockworkcode.sibiuairportparkingmanager.DTO.ParkingActivityDTO;
import com.clockworkcode.sibiuairportparkingmanager.service.ParkingActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/parkingActivities")
@CrossOrigin
public class ParkingActivityController {

    @Autowired
    private ParkingActivityService parkingActivityService;

    @GetMapping("/getParkedCars")
    public ResponseEntity<Map<String, List<ParkingActivityDTO>>> getParkedCars(){
        Map<String, List<ParkingActivityDTO>> responseMap = new HashMap<>();
        List<ParkingActivityDTO> listParkedCars = parkingActivityService.getParkedCars();
        responseMap.put("result",listParkedCars);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

}
