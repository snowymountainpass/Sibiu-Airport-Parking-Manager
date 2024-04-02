package com.clockworkcode.sibiuairportparkingmanager.integration;

import com.clockworkcode.sibiuairportparkingmanager.model.Airport;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingSpace;
import com.clockworkcode.sibiuairportparkingmanager.repository.AirportRepository;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingSpaceRepository;
import com.clockworkcode.sibiuairportparkingmanager.service.ParkingSpaceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DataJpaTest
@TestPropertySource()
public class ParkingSpaceServiceIntegrationTest {

    @MockBean
    private ParkingSpaceRepository parkingSpaceRepository;

    @MockBean
    private AirportRepository airportRepository;

    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @Test
    public void testAddParkingSpace_ValidData(){
        String parkingSpaceName="0001";
        String airportName ="Sibiu International Airport";
        String airportCode ="SIA";
        Long costPerMinute=5L;

        Airport airport = new Airport(airportName,airportCode,costPerMinute);
        when(airportRepository.getAirportByAirportName(airportName)).thenReturn(airport);

        boolean result=parkingSpaceService.addParkingSpace(parkingSpaceName,airportName);

        verify(parkingSpaceRepository,times(1)).save(any(ParkingSpace.class));

        assertTrue(result);
    }

    @Test
    public void testAddParkingSpace_InvalidInput() {

        String parkingSpaceName = "a001";
        String airportName = "Test Airport Internation";

        boolean result = parkingSpaceService.addParkingSpace(parkingSpaceName, airportName);

        verify(parkingSpaceRepository, never()).save(any(ParkingSpace.class));

        assertFalse(result);
    }


    @Test
    public void testAddParkingSpace_NoAirportFound(){

        String parkingSpaceName="0001";
        String airportName ="Non Existent Airport";

        when(airportRepository.getAirportByAirportName(airportName)).thenReturn(null);

        boolean result = parkingSpaceService.addParkingSpace(parkingSpaceName,airportName);

        verify(parkingSpaceRepository,never()).save(any(ParkingSpace.class));

        assertFalse(result);
    }

}
