package com.clockworkcode.sibiuairportparkingmanager.repository;

import com.clockworkcode.sibiuairportparkingmanager.model.ParkingCost;
import com.clockworkcode.sibiuairportparkingmanager.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findPaymentDetailsByParkingCost(ParkingCost parkingCost);
}
