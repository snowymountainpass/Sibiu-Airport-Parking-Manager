package com.clockworkcode.sibiuairportparkingmanager.controller;

import com.clockworkcode.sibiuairportparkingmanager.model.StripeCharge;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StripeChargeController {

    @Autowired
    private Environment env;

    @PostMapping("/charge")
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
