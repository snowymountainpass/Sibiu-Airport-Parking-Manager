// LandingPage.js
import React from 'react';
import Header from "../Sections/Header";
import Body from "../Sections/Body";
import Footer from "../Sections/Footer";
import InputField from "../Components/InputField";
import TableComponent from "../Components/Table";
import {Link} from "react-router-dom";
import SectionHeader from "../Components/SectionHeader";

const LandingPage = () => {
    return (
        <div>
            <Header />
            <Body>
                <div className="landing-page-content">
                    <SectionHeader>Parking Payment</SectionHeader>
                    <InputField/>
                    <SectionHeader>Pricing</SectionHeader>
                    <TableComponent />
                    <SectionHeader>Dashboard</SectionHeader>
                    <Link to={"/dashboard"}>Dashboard</Link>
                </div>
            </Body>
            <Footer />
        </div>
    );
};

export default LandingPage;
