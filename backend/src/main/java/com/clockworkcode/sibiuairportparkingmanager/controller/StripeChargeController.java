package com.clockworkcode.sibiuairportparkingmanager.controller;

import com.clockworkcode.sibiuairportparkingmanager.DTO.PaymentDTO;
import com.clockworkcode.sibiuairportparkingmanager.model.Car;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingActivity;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingCost;
import com.clockworkcode.sibiuairportparkingmanager.model.StripeCharge;
import com.clockworkcode.sibiuairportparkingmanager.service.CarService;
import com.clockworkcode.sibiuairportparkingmanager.service.ParkingActivityService;
import com.clockworkcode.sibiuairportparkingmanager.service.ParkingCostService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class StripeChargeController {

    @Autowired
    private Environment env;

    @Autowired
    private CarService carService;

    @Autowired
    private ParkingActivityService parkingActivityService;

    @Autowired
    private ParkingCostService parkingCostService;

    @GetMapping("/payment_details")
    public ResponseEntity<PaymentDTO> getPaymentDetails(@RequestBody String licensePlate){

        Car car = carService.getCarByLicensePlate(licensePlate);
        ParkingActivity parkingActivity = parkingActivityService.getLatestParkingActivity(car);
        parkingActivityService.setDepartureTime(car);

        //Retrieve the details from the ParkingCost
        parkingCostService.addParkingCostForCar(car);
        Float amountToBePaid = parkingCostService.getAmountToBePaid(car);

        final PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setLicensePlate(licensePlate);
        paymentDTO.setStartTime(parkingActivity.getStartTime());
        paymentDTO.setEndTime(parkingActivity.getEndTime());
        paymentDTO.setBillableAmount(amountToBePaid);
        paymentDTO.setAirportCode(parkingActivity.getParkingSpace().getAirport().getAirportCode());
        paymentDTO.setAirportName(parkingActivity.getParkingSpace().getAirport().getAirportName());
        paymentDTO.setParkingSpaceNumber(parkingActivity.getParkingSpace().getParkingSpaceNumber());

        return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
    }

    @PostMapping("/payment")
    public ResponseEntity<String> createCharge(@RequestBody StripeCharge stripeCharge) {
        try {

            // creating the charge
            Stripe.apiKey = env.getProperty("SK_TEST_KEY");
            Charge charge = Charge.create(stripeCharge.getCharge());
            System.out.println(charge);
            return new ResponseEntity<String>("Success", HttpStatus.CREATED);
        } catch (StripeException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Failure", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
