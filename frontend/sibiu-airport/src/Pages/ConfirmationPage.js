// ConfirmationPage.js
import React from 'react';
import Header from "../Sections/Header";
import Body from "../Sections/Body";
import Footer from "../Sections/Footer";
import ConfirmationPageBody from "../Components/ConfirmationPageBody";

const ConfirmationPage = ({paymentDetails}) => {
    return (
        <div>
            <Header />
            <Body>
                <ConfirmationPageBody paymentDetails={paymentDetails} />
            </Body>
            <Footer />
        </div>
    );
};

export default ConfirmationPage;
