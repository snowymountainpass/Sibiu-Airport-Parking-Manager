package com.clockworkcode.sibiuairportparkingmanager.repository;

import com.clockworkcode.sibiuairportparkingmanager.DTO.ParkingActivityDTO;
import com.clockworkcode.sibiuairportparkingmanager.model.Car;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingActivity;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ParkingActivityRepository extends JpaRepository<ParkingActivity,Long> {

    List<ParkingActivity> findParkingActivitiesByParkingSpace(ParkingSpace parkingSpace);
    List<ParkingActivity> findParkingActivitiesByCar(Car car);

    List<ParkingActivity> findParkingActivitiesByStartTime(Date startTime);
    List<ParkingActivity> findParkingActivitiesByStartTimeAndEndTime(Date startTime,Date endTime);

    List<ParkingActivity> findParkingActivitiesByCarOrderByStartTimeDesc(Car car);

@Query("""
       SELECT new com.clockworkcode.sibiuairportparkingmanager.DTO.ParkingActivityDTO (
           Car.carLicensePlate,
           a.airportName,
           ps.parkingSpaceNumber,
           pa.startTime
       )
       FROM Car Car
       JOIN ParkingActivity pa ON Car.carId = pa.car.carId
       JOIN ParkingSpace ps ON ps.parkingSpaceId = pa.parkingSpace.parkingSpaceId
       JOIN Airport a ON a.airportId = ps.airport.airportId
       WHERE pa.endTime IS null
       """)
    List<ParkingActivityDTO> getListParkedCars();

}
