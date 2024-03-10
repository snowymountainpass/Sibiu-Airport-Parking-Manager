// PaymentPage.js
import React, {useEffect, useState} from 'react';
import Header from "../Sections/Header";
import Body from "../Sections/Body";
import Footer from "../Sections/Footer";
import {EmbeddedCheckoutProvider,EmbeddedCheckout} from "@stripe/react-stripe-js";
import {loadStripe} from "@stripe/stripe-js";
import axios from "axios";

const stripePubKey = 'pk_test_51KtS1vIBvoqhBs9FLAT83yqmV0oLxIprG0qQuGaLfvKZcu3c9ZVZGATygdBDOkTjLxoFPsq06sqijzLJagg65YJ400GnDhe2av';
const PaymentPage = () => {
    const [stripePromise, setStripePromise] = useState(() => loadStripe(stripePubKey))
    const [clientSecret, setClientSecret] = useState("");
    const options = {clientSecret};
    
    useEffect(() => {
        // Create a Checkout Session as soon as the page loads
        axios.post('http://localhost:8080/order/checkout', {
            licensePlate: JSON.parse(localStorage.getItem('licensePlate'))
        }, {
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
            }
        })
            .then(function (response) {
                setClientSecret(response.data.clientSecret);
            })
            .catch(function (error) {
                console.log('Error fetching data:', error);
            })
    }, []);

    return (
        <div>
            <Header />
            <Body>
                <div id="checkout">
                    {clientSecret && (
                        <EmbeddedCheckoutProvider
                            stripe={stripePromise}
                            options={options}
                        >
                            <EmbeddedCheckout />
                        </EmbeddedCheckoutProvider>
                    )}
                </div>
            </Body>
            <Footer />
        </div>
    );
};
export default PaymentPage;