package com.clockworkcode.sibiuairportparkingmanager.repository;

import com.clockworkcode.sibiuairportparkingmanager.model.Car;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingActivity;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ParkingActivityRepository extends JpaRepository<ParkingActivity,Long> {

    List<ParkingActivity> findParkingActivitiesByParkingSpace(ParkingSpace parkingSpace);
    List<ParkingActivity> findParkingActivitiesByCar(Car car);

    List<ParkingActivity> findParkingActivitiesByStartTime(Date startTime);
    List<ParkingActivity> findParkingActivitiesByStartTimeAndEndTime(Date startTime,Date endTime);

}
