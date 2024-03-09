// PaymentPage.js
import React, {useEffect, useState} from 'react';
import Header from "../Sections/Header";
import Body from "../Sections/Body";
import Footer from "../Sections/Footer";
import {EmbeddedCheckoutProvider,EmbeddedCheckout} from "@stripe/react-stripe-js";
import {loadStripe} from "@stripe/stripe-js";
import { useNavigate } from 'react-router-dom';

const stripePromise = loadStripe("pk_test_51KtS1vIBvoqhBs9FLAT83yqmV0oLxIprG0qQuGaLfvKZcu3c9ZVZGATygdBDOkTjLxoFPsq06sqijzLJagg65YJ400GnDhe2av");

const PaymentPage = () => {

    const [clientSecret, setClientSecret] = useState("");
    const options = {clientSecret};
    const navigate = useNavigate();

    useEffect(() => {
        // Create a Checkout Session as soon as the page loads
        console.log("Reached the useEffect in the PaymentPage!")
        fetch("http://localhost:8080/order/checkout", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
            },
            // Send the string in the request body as JSON
            body: JSON.stringify({ licensePlate: JSON.parse(localStorage.getItem('licensePlate')) })
        })
            .then((res) => res.json())
            .then((data) => setClientSecret(data.clientSecret));
            // .then(navigate('/return'));
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


//V1
// const PaymentPage = () => {
//
//     const paymentDetails={
//         amount:500
//     }
//
//     return (
//         <div>
//             <Header />
//             <Body>
//                 <PaymentPageBody paymentDetails={paymentDetails} />
//             </Body>
//             <Footer />
//         </div>
//     );
// };

//V1