package com.clockworkcode.sibiuairportparkingmanager.service;

import com.clockworkcode.sibiuairportparkingmanager.DTO.PaymentDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.model.checkout.Session;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService {

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;

    public Product createProduct(PaymentDTO paymentDTO) throws StripeException{
        long differenceInMilliseconds = Math.abs(paymentDTO.getEndTime().getTime()-paymentDTO.getStartTime().getTime());
        long differenceInMinutes = TimeUnit.MINUTES.convert(differenceInMilliseconds, TimeUnit.MILLISECONDS);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String startTimeString = paymentDTO.getStartTime().toInstant().atZone(ZoneId.systemDefault()).format(formatter);
        String endTimeString = paymentDTO.getEndTime().toInstant().atZone(ZoneId.systemDefault()).format(formatter);

        ProductCreateParams params =
                ProductCreateParams.builder()//
                        .setName("Parking Space Cost")//
                        .setDescription("Car "+paymentDTO.getLicensePlate()+" spent "+differenceInMinutes+" minutes ("+startTimeString+" to " +endTimeString+ ") at the "+paymentDTO.getAirportName()+" in the parking space "+paymentDTO.getParkingSpaceNumber()+".")//
                        .build();
        return Product.create(params);
    }

    public Price createPrice(PaymentDTO paymentDTO,Product product) throws StripeException {
        PriceCreateParams params =
                PriceCreateParams.builder()//
                        .setUnitAmount(paymentDTO.getBillableAmount()*100)// //amount * 100 euro cents
                        .setCurrency("eur")//
                        .setNickname(product.getDescription())
                        .setProduct(product.getId())
                        .build();

        return Price.create(params);
    }

    public Session createSession(PaymentDTO paymentDTO) throws StripeException {
        Stripe.apiKey=apiKey;
        Product product = createProduct(paymentDTO);
        Price price = createPrice(paymentDTO, product);

        SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()//
                .setQuantity(1L)//
                .setPrice(price.getId())//
                .build();

        SessionCreateParams params = SessionCreateParams.builder()//
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)//
                .setMode(SessionCreateParams.Mode.PAYMENT)//
                .setUiMode(SessionCreateParams.UiMode.EMBEDDED)//
                .setReturnUrl("http://localhost:3000/return?session_id={CHECKOUT_SESSION_ID}")//
                .addLineItem(lineItem)//
                .build();

        return Session.create(params);
    }
}
