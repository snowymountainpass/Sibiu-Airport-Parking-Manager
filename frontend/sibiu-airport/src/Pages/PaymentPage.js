// PaymentPage.js
import React from 'react';
import Header from "../Sections/Header";
import Body from "../Sections/Body";
import Footer from "../Sections/Footer";
import PaymentPageBody from "../Components/PaymentPageBody";

const PaymentPage = ({paymentDetails}) => {
    return (
        <div>
            <Header />
            <Body>
                <PaymentPageBody paymentDetails={paymentDetails} />
            </Body>
            <Footer />
        </div>
    );
};

export default PaymentPage;
