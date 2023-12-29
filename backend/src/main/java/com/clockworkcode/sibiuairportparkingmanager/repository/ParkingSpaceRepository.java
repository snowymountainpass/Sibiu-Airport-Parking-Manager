package com.clockworkcode.sibiuairportparkingmanager.repository;

import com.clockworkcode.sibiuairportparkingmanager.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace,Long> {

    List<ParkingSpace>  findParkingSpacesByParkingSpaceAvailable(Boolean parkingSpaceAvailable);
    ParkingSpace findParkingSpaceByParkingNumber(String parkingNumber);

}
