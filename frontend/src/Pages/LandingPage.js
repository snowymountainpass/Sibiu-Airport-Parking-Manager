// LandingPage.js
import React from 'react';
import Header from "../Sections/Header";
import Body from "../Sections/Body";
import Footer from "../Sections/Footer";
import InputField from "../Components/InputField";
import TableComponent from "../Components/Table";
import {redirect} from "react-router-dom";

const LandingPage = () => {

    const handlePayment = (licensePlate) => {

        localStorage.setItem("licensePlateValue", `${licensePlate}`);
        // Redirect to the "/payment" route
        redirect('/payment');
        console.log('Redirecting to Payment Page');
    }

    return (
        <div>
            <Header />
            <Body>
                <div className="landing-page-content">
                    <InputField onPayment={handlePayment} />
                    <TableComponent />
                </div>
            </Body>
            <Footer />
        </div>
    );
};

export default LandingPage;
