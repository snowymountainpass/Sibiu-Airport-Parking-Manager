package com.clockworkcode.sibiuairportparkingmanager.integration;

import com.clockworkcode.sibiuairportparkingmanager.model.Airport;
import com.clockworkcode.sibiuairportparkingmanager.model.Car;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingActivity;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingSpace;
import com.clockworkcode.sibiuairportparkingmanager.repository.CarRepository;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingActivityRepository;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingCostRepository;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingSpaceRepository;
import com.clockworkcode.sibiuairportparkingmanager.service.ParkingActivityService;
import com.clockworkcode.sibiuairportparkingmanager.service.ParkingCostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@DataJpaTest
public class ParkingCostServiceIntegrationTest {

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private ParkingSpaceRepository parkingSpaceRepository;

    @MockBean
    private ParkingActivityRepository parkingActivityRepository;

    @MockBean
    private ParkingCostRepository parkingCostRepository;

    @Autowired
    private ParkingActivityService parkingActivityService;

    @Autowired
    private ParkingCostService parkingCostService;

    @Test
    public void testCalculateAmountToBePaid(){
        ParkingActivityService parkingActivityService = new ParkingActivityService(carRepository,parkingSpaceRepository,parkingActivityRepository);


        Car car = new Car("B08NPX");

        ParkingSpace parkingSpace = new ParkingSpace("A1", false, new Airport("Airport", "ABC", 5L)); // Cost per minute: 5 euro
        Date startTime = new Date(System.currentTimeMillis() - 3600000); // 1 hour ago
        Date endTime = new Date(); // Current time
        ParkingActivity parkingActivity = new ParkingActivity(parkingSpace,car,startTime);
        parkingActivity.setEndTime(endTime);

        when(parkingActivityService.getLatestParkingActivity(car)).thenReturn(parkingActivity);

        long amountToBePaid = parkingCostService.calculateAmountToBePaid(car);

        assertNotNull(startTime);
        assertNotNull(endTime);
        assertEquals(300L, amountToBePaid); // Parking duration: 1 hour (60 minutes), Cost per minute: 5 euro, Expected amount: 60 * 5 = 300 euro
    }

}
