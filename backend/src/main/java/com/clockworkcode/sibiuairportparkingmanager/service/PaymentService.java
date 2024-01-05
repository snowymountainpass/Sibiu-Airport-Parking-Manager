package com.clockworkcode.sibiuairportparkingmanager.service;

import com.clockworkcode.sibiuairportparkingmanager.model.Car;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingSpace;
import com.clockworkcode.sibiuairportparkingmanager.model.PaymentDetail;
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

    public void processPaymentAndUpdateEntities(PaymentDetail paymentDetail) {

        // Business logic - processing payment


//        Car car = paymentDetail.getCar();
//        ParkingSpace parkingSpace = paymentDetail.getParkingSpace();

    }

}
