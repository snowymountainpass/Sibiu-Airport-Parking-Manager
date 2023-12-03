// LandingPage.js
import React from 'react';
import Header from "../Sections/Header";
import Body from "../Sections/Body";
import Footer from "../Sections/Footer";
import InputField from "../Components/InputField";
import TableComponent from "../Components/Table";

const LandingPage = () => {
    
    return (
        <div>
            <Header />
            <Body>
                <div className="landing-page-content">
                    <InputField/>
                    <TableComponent />
                </div>
            </Body>
            <Footer />
        </div>
    );
};

export default LandingPage;
