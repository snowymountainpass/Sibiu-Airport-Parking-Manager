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
        <div className="landing-page">
            <Header />
            <Body>
                <div className="landing-page-content">
                    <SectionHeader className="section-header">Parking Payment</SectionHeader>
                    <InputField className="input-field"/>
                    <SectionHeader className="section-header">Pricing</SectionHeader>
                    <TableComponent className="table-component" />
                    <SectionHeader className="section-header">Dashboard</SectionHeader>
                    <Link to={"/dashboard"} className="dashboard-link">Dashboard</Link>
                </div>
            </Body>
            <Footer />
        </div>
    );
};

export default LandingPage;


//ORIGINAL

// <Body>
//     <div className="landing-page-content">
//         <SectionHeader>Parking Payment</SectionHeader>
//         <InputField/>
//         <SectionHeader>Pricing</SectionHeader>
//         <TableComponent />
//         <SectionHeader>Dashboard</SectionHeader>
//         <Link to={"/dashboard"}>Dashboard</Link>
//     </div>
// </Body>

//ORIGINAL