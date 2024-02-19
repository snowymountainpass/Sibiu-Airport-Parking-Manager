package com.clockworkcode.sibiuairportparkingmanager.controller;

import com.clockworkcode.sibiuairportparkingmanager.DTO.CarDTO;
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
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
@CrossOrigin
public class StripeChargeController {

    @Autowired
    private Environment env;

    @Autowired
    private CarService carService;

    @Autowired
    private ParkingActivityService parkingActivityService;

    @Autowired
    private ParkingCostService parkingCostService;

    @PostMapping("/payment_details")
    public ResponseEntity<PaymentDTO> getPaymentDetails(@RequestBody CarDTO carDTO){

        Car car = carService.getCarByLicensePlate(carDTO.getLicensePlate());
        ParkingActivity parkingActivity = parkingActivityService.getLatestParkingActivity(car);
        if(parkingActivity.getEndTime()==null){
            parkingActivityService.setDepartureTime(car);
        }

        //Retrieve the details from the ParkingCost
        parkingCostService.addParkingCostForCar(car);
        Float amountToBePaid = parkingCostService.getAmountToBePaid(car);

        final PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setLicensePlate(carDTO.getLicensePlate());
        paymentDTO.setStartTime(parkingActivity.getStartTime());
        paymentDTO.setEndTime(parkingActivity.getEndTime());
        paymentDTO.setBillableAmount(amountToBePaid);
        paymentDTO.setAirportCode(parkingActivity.getParkingSpace().getAirport().getAirportCode());
        paymentDTO.setAirportName(parkingActivity.getParkingSpace().getAirport().getAirportName());
        paymentDTO.setParkingSpaceNumber(parkingActivity.getParkingSpace().getParkingSpaceNumber());

        return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
    }

    @PostMapping("/make_payment")
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

    @PostMapping("/create-checkout-session")
    public Map<String, String> createCheckoutSession(@RequestBody Map<String, Object> payload) throws StripeException {
        String YOUR_DOMAIN = "http://localhost:3000";

        SessionCreateParams.Builder builder = SessionCreateParams.builder()
                .setUiMode(SessionCreateParams.UiMode.EMBEDDED)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setReturnUrl(YOUR_DOMAIN + "/return?session_id={CHECKOUT_SESSION_ID}");

        // Extract any necessary parameters from the payload
        // For example:
        String priceId = (String) payload.get("priceId");
        if (priceId != null) {
            builder.addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPrice("")
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    .setCurrency("eur")
                                                    .build())
                                    .build())
                    .build();
        }

        SessionCreateParams params = builder.build();
        Session session = Session.create(params);

        Map<String, String> response = new HashMap<>();
        response.put("clientSecret", session.getClientSecret());

        return response;
    }

    @GetMapping("/session-status")
    public Map<String, String> getSessionStatus(@RequestParam("session_id") String sessionId) throws StripeException {
        Session session = Session.retrieve(sessionId);

        Map<String, String> map = new HashMap<>();
        map.put("status", session.getStatus());
        map.put("customer_email", session.getCustomerDetails().getEmail());

        return map;
    }

}
