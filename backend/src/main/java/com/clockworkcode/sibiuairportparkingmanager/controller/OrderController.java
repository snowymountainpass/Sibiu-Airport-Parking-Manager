package com.clockworkcode.sibiuairportparkingmanager.controller;

import com.clockworkcode.sibiuairportparkingmanager.DTO.CarDTO;
import com.clockworkcode.sibiuairportparkingmanager.DTO.PaymentDTO;
import com.clockworkcode.sibiuairportparkingmanager.model.Car;
import com.clockworkcode.sibiuairportparkingmanager.model.ParkingActivity;
import com.clockworkcode.sibiuairportparkingmanager.model.StripeResponse;
import com.clockworkcode.sibiuairportparkingmanager.service.CarService;
import com.clockworkcode.sibiuairportparkingmanager.service.OrderService;
import com.clockworkcode.sibiuairportparkingmanager.service.ParkingActivityService;
import com.clockworkcode.sibiuairportparkingmanager.service.ParkingCostService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private Environment env;

    @Autowired
    private CarService carService;

    @Autowired
    private ParkingActivityService parkingActivityService;

    @Autowired
    private ParkingCostService parkingCostService;

    @Autowired
    private OrderService orderService;

//    @PostMapping("/payment_details")
    public PaymentDTO getPaymentDetails(CarDTO carDTO){

        Car car = carService.getCarByLicensePlate(carDTO.getLicensePlate());
        ParkingActivity parkingActivity = parkingActivityService.getLatestParkingActivity(car);
        if(parkingActivity.getEndTime()==null){
            parkingActivityService.setDepartureTime(car);
            parkingCostService.addParkingCostForCar(parkingActivity);
        }

        Long amountToBePaid = parkingCostService.getAmountToBePaid(car);

        final PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setLicensePlate(carDTO.getLicensePlate());
        paymentDTO.setStartTime(parkingActivity.getStartTime());
        paymentDTO.setEndTime(parkingActivity.getEndTime());
        paymentDTO.setBillableAmount(amountToBePaid);
        paymentDTO.setAirportCode(parkingActivity.getParkingSpace().getAirport().getAirportCode());
        paymentDTO.setAirportName(parkingActivity.getParkingSpace().getAirport().getAirportName());
        paymentDTO.setParkingSpaceNumber(parkingActivity.getParkingSpace().getParkingSpaceNumber());

        return paymentDTO;
    }

    @PostMapping("/checkout")
    public ResponseEntity<Map<String, String>> checkout(@RequestBody CarDTO carDTO) throws StripeException {

        PaymentDTO paymentDTO = getPaymentDetails(carDTO);

        Session session = orderService.createSession(paymentDTO);

        // Prepare response map with client secret
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("clientSecret", session.getClientSecret());

        Car car = carService.getCarByLicensePlate(carDTO.getLicensePlate());
        ParkingActivity parkingActivity = parkingActivityService.getLatestParkingActivity(car);

        parkingActivityService.clearParkingSpace(parkingActivity);

        return new ResponseEntity<>(responseMap,HttpStatus.OK);


    }

    @GetMapping("/session-status")
    public Map<String, String> getSessionStatus(@RequestParam("session_id") String sessionId) throws StripeException {
        // Set your Stripe API key
//        Stripe.apiKey = "sk_test_TyMeY76Ef4pXsM1rA5rznKax";

        // Retrieve the session from Stripe using the session ID
        Session session = Session.retrieve(sessionId);

        // Extract status and customer email from the session
        String status = session.getStatus();
        String customerEmail = session.getCustomerEmail();

        // Prepare response map with status and customer email
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("status", status);
        responseMap.put("customer_email", customerEmail);

        return responseMap;
    }

//    Map<String, String> map = new HashMap();
//        map.put("clientSecret", session.getRawJsonObject().getAsJsonPrimitive("client_secret").getAsString());
}
