// PaymentPageBody.js
import React, {useEffect, useState} from 'react';
import { Divider, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography } from '@mui/material';
import CheckoutForm from "./CheckoutForm";
import {Elements} from "@stripe/react-stripe-js";
import {loadStripe} from "@stripe/stripe-js";
// import PaymentForm from './PaymentForm';

const PaymentPageBody = ({ paymentDetails }) => {

    const stripePromise = loadStripe("pk_test_51KtS1vIBvoqhBs9FLAT83yqmV0oLxIprG0qQuGaLfvKZcu3c9ZVZGATygdBDOkTjLxoFPsq06sqijzLJagg65YJ400GnDhe2av");

    const carLicenseNumber = localStorage.getItem('carLicenseNumber');
    const startDate = '2023-11-20T08:00:00'; // TODO: replace with server stored start date & time

    // TODO: replace with current server date & time
    const endDate = new Date().toISOString();

    const DisplayAmount = () => {
      console.log(paymentDetails.amount);
    }

    useEffect(()=>{
        DisplayAmount();
    },[])

    //Stripe Payment Form - Start

    const [clientSecret, setClientSecret] = useState("");

    useEffect(() => {
        // Create PaymentIntent as soon as the page loads
        fetch("/create-payment-intent", {
            method: "POST",
            headers: { "content-type": "application/json" },
            body: JSON.stringify({ items: [{ id: "xl-tshirt" }] }),
        })
            .then((res) => res.json())
            .then((data) => setClientSecret(data.clientSecret));
    }, []);

    const appearance = {
        theme: 'stripe',
    };
    const options = {
        clientSecret,
        appearance,
    };

    //Stripe Payment Form - End


    return (
        <div>
            {/*<PaymentForm />*/}

            <Divider style={{ margin: '20px 0' }} />

            <Typography variant="h6" gutterBottom>
                Payment Details Table
            </Typography>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Car License Number</TableCell>
                            <TableCell>Start Date & Time</TableCell>
                            <TableCell>End Date & Time</TableCell>
                            <TableCell>Payment Amount</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        <TableRow>
                            <TableCell>{carLicenseNumber}</TableCell>
                            <TableCell>{startDate}</TableCell>
                            <TableCell>{endDate}</TableCell>
                            <TableCell>{paymentDetails.amount/100} EUR</TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            </TableContainer>

            <Divider style={{ margin: '20px 0' }} />

            {clientSecret && (
                <Elements options={options} stripe={stripePromise}>
                    <CheckoutForm />
                </Elements>
            )}

        </div>
    );
};

export default PaymentPageBody;
