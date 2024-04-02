package com.clockworkcode.sibiuairportparkingmanager.integration;

import com.clockworkcode.sibiuairportparkingmanager.model.Car;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingActivity;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingSpace;
import com.clockworkcode.sibiuairportparkingmanager.repository.CarRepository;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingActivityRepository;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingSpaceRepository;
import com.clockworkcode.sibiuairportparkingmanager.service.ParkingActivityService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
public class ParkingActivityServiceIntegrationTest {

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private ParkingSpaceRepository parkingSpaceRepository;

    @MockBean
    private ParkingActivityRepository parkingActivityRepository;

    @Autowired
    private ParkingActivityService parkingActivityService;

    @Test
    public void testAddCar_CarExists() {
        String licensePlate = "B01NPP";
        String airportName = "International Test Airport";
        String parkingSpaceName = "0025";

        Car existingCar = new Car(licensePlate);
        when(carRepository.getCarByCarLicensePlate(licensePlate)).thenReturn(existingCar);

        parkingActivityService.addCar(licensePlate,airportName,parkingSpaceName);

        verify(parkingActivityService,times(1))//
                .assignToAirportAndParkingSpace(eq(existingCar),eq(airportName),eq(parkingSpaceName));
    }

    @Test
    public void testAddCar_CarDoesNotExist() {

        String licensePlate = "B01NPR";
        String airportName = "International Test Airport";
        String parkingSpaceName = "0026";

        when(carRepository.getCarByCarLicensePlate(licensePlate)).thenReturn(null);

        parkingActivityService.addCar(licensePlate,airportName,parkingSpaceName);

        ArgumentCaptor<Car> carCaptor = ArgumentCaptor.forClass(Car.class);

        verify(carRepository,times(1)).save(carCaptor.capture());

        assertEquals(licensePlate,carCaptor.getValue().getCarLicensePlate());

        verify(parkingActivityService,times(1))//
                .assignToAirportAndParkingSpace(any(Car.class),eq(airportName),eq(parkingSpaceName));
    }

    @Test
    public void testAssignToAirportAndParkingSpace() {

        Car car = new Car("B03NPS");
        String airportName = "International Test Airport";
        String parkingSpaceName = "0027";

        ParkingSpace parkingSpace = new ParkingSpace();
        when(parkingSpaceRepository.findParkingSpaceByAirport_AirportNameAndParkingSpaceNumber(airportName,parkingSpaceName)).thenReturn(parkingSpace);

        parkingActivityService.assignToAirportAndParkingSpace(car,airportName,parkingSpaceName);

        assertTrue(parkingSpace.getOccupied());

        ArgumentCaptor<Car> carCaptor = ArgumentCaptor.forClass(Car.class);
        ArgumentCaptor<ParkingSpace> parkingSpaceCaptor = ArgumentCaptor.forClass(ParkingSpace.class);
        verify(parkingActivityService,times(1)).assignParkingActivity(carCaptor.capture(),parkingSpaceCaptor.capture());
        assertEquals(car,carCaptor.getValue());
        assertEquals(parkingSpace,parkingSpaceCaptor.getValue());
    }

    @Test
    public void testAssignParkingActivity() {

        ParkingActivityService parkingActivityService = new ParkingActivityService(carRepository,parkingSpaceRepository,parkingActivityRepository);

        Car car = new Car("B04NPT");
        ParkingSpace parkingSpace = new ParkingSpace("0001",false,null);

        parkingActivityService.assignParkingActivity(car,parkingSpace);

        ArgumentCaptor<ParkingActivity> parkingActivityCaptor = ArgumentCaptor.forClass(ParkingActivity.class);
        verify(parkingActivityRepository,times(1)).save(parkingActivityCaptor.capture());

        ParkingActivity savedParkingActivity = parkingActivityCaptor.getValue();


        assertEquals(car,savedParkingActivity.getCar());
        assertEquals(parkingSpace,savedParkingActivity.getParkingSpace());
        assertNotNull(savedParkingActivity.getStartTime());
    }

    @Test
    public void testClearParkingSpace() {

        ParkingActivityService parkingActivityService = new ParkingActivityService(carRepository,parkingSpaceRepository,parkingActivityRepository);

        ParkingSpace parkingSpace = new ParkingSpace("0001",true,null);
        Car car = new Car("B05NPU");
        ParkingActivity parkingActivity = new ParkingActivity(parkingSpace,car,new Date());

        parkingActivityService.clearParkingSpace(parkingActivity);

        ArgumentCaptor<ParkingActivity> parkingActivityCaptor = ArgumentCaptor.forClass(ParkingActivity.class);
        verify(parkingActivityRepository,times(1)).save(parkingActivityCaptor.capture());

        ParkingActivity savedParkingActivity = parkingActivityCaptor.getValue();

        assertEquals(parkingActivity,savedParkingActivity);
    }

    @Test
    public void testSetDepartureTime() {

        ParkingActivityService parkingActivityService = new ParkingActivityService(carRepository,parkingSpaceRepository,parkingActivityRepository);

        Car car = new Car("B06NPV");
        ParkingSpace parkingSpace = new ParkingSpace("0001",true,null);

        ParkingActivity latestParkingActivity = new ParkingActivity(parkingSpace,car,new Date());

        when(parkingActivityService.getLatestParkingActivity(car)).thenReturn(latestParkingActivity);

        parkingActivityService.setDepartureTime(car);

        ArgumentCaptor<ParkingActivity> parkingActivityCaptor = ArgumentCaptor.forClass(ParkingActivity.class);
        verify(parkingActivityRepository,times(1)).save(parkingActivityCaptor.capture());

        ParkingActivity savedParkingActivity = parkingActivityCaptor.getValue();

        // Calculate the expected departure time (current time + 5 minutes)
        LocalDateTime expectedEndTime = LocalDateTime.now().plus(Duration.ofMinutes(5));
        Date expectedDepartureTime = Date.from(expectedEndTime.atZone(ZoneId.systemDefault()).toInstant());

        assertEquals(expectedDepartureTime, savedParkingActivity.getEndTime());
    }

    @Test
    public void testGetLatestParkingActivity() {

        ParkingActivityService parkingActivityService = new ParkingActivityService(carRepository,parkingSpaceRepository,parkingActivityRepository);

        Car car = new Car("B07NPW");
        ParkingSpace parkingSpace1 = new ParkingSpace("0001",false,null);
        ParkingSpace parkingSpace2 = new ParkingSpace("0002",true,null);
        ParkingSpace parkingSpace3 = new ParkingSpace("0003",true,null);

        ParkingActivity parkingActivity1 = new ParkingActivity(parkingSpace1,car,new Date()); // Current time
        ParkingActivity parkingActivity2 = new ParkingActivity(parkingSpace2,car, new Date(System.currentTimeMillis() - 1000)); // 1 second ago
        ParkingActivity parkingActivity3 = new ParkingActivity(parkingSpace3,car, new Date(System.currentTimeMillis() - 2000)); // 2 seconds ago

        List<ParkingActivity> parkingActivities = new ArrayList<>(Arrays.asList(parkingActivity1, parkingActivity2, parkingActivity3));

        when(parkingActivityRepository.findParkingActivitiesByCarOrderByStartTimeDesc(car)).thenReturn(parkingActivities);

        ParkingActivity latestParkingActivity = parkingActivityService.getLatestParkingActivity(car);

        assertEquals(parkingActivity1, latestParkingActivity);
    }

}
