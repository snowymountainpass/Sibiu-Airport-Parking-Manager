package com.clockworkcode.sibiuairportparkingmanager.integration;

import com.clockworkcode.sibiuairportparkingmanager.model.Airport;
import com.clockworkcode.sibiuairportparkingmanager.repository.AirportRepository;
import com.clockworkcode.sibiuairportparkingmanager.service.AirportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@TestPropertySource()
public class AirportServiceIntegrationTest {

    @MockBean
    private AirportRepository airportRepository;

    @Autowired
    private AirportService airportService;

    @Test
    public void testAddAirport_ValidData(){
        String airportName="Sibiu Airport";
        String airportCode="SIA";
        Long costPerMinute=5L;

        when(airportRepository.save(any(Airport.class))).thenReturn(new Airport(airportName, airportCode, costPerMinute));

        boolean result = airportService.addAirport(airportName, airportCode, costPerMinute);

        verify(airportRepository, times(1)).save(any(Airport.class));

        assertTrue(result);
    }

    @Test
    public void testAddAirport_InvalidData() {
        String airportName="Sibiu@ Airport#"; //invalid characters
        String airportCode="SIA";
        Long costPerMinute=5L;

        boolean result = airportService.addAirport(airportName, airportCode, costPerMinute);

        verify(airportRepository, never()).save(any(Airport.class));

        assertFalse(result);
    }

}
