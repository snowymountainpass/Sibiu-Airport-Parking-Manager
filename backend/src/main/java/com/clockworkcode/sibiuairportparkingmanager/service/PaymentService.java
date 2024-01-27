package com.clockworkcode.sibiuairportparkingmanager.service;

import com.clockworkcode.sibiuairportparkingmanager.model.Payment;
import com.clockworkcode.sibiuairportparkingmanager.repository.CarRepository;
import com.clockworkcode.sibiuairportparkingmanager.repository.ParkingSpaceRepository;
import com.clockworkcode.sibiuairportparkingmanager.repository.PaymentDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentDetailRepository paymentDetailRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    public void processPaymentAndUpdateEntities(Payment paymentDetail) {

        // Business logic - processing payment


//        Car car = paymentDetail.getCar();
//        ParkingSpace parkingSpace = paymentDetail.getParkingSpace();

    }

}
