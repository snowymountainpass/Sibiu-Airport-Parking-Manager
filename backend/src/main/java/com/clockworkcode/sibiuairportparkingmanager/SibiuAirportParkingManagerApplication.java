package com.clockworkcode.sibiuairportparkingmanager;

import com.clockworkcode.sibiuairportparkingmanager.model.Airport;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingSpace;
import com.clockworkcode.sibiuairportparkingmanager.repository.AirportRepository;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingSpaceRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SibiuAirportParkingManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SibiuAirportParkingManagerApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(AirportRepository airportRepository,ParkingSpaceRepository parkingSpaceRepository){
        return args -> {
            Airport airport1 = new Airport("Sibiu","1111");
            airportRepository.save(airport1);
            ParkingSpace parkingSpace1 = new ParkingSpace("P0001",true,airport1,null);
            parkingSpaceRepository.save(parkingSpace1);

        };

    }



}
