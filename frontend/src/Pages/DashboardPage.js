import React from 'react';
import Header from "../Sections/Header";
import Body from "../Sections/Body";
import DashboardInputField from "../Components/DashboardInputField";
import TableComponent from "../Components/Table";
import Footer from "../Sections/Footer";



const DashboardPage = () => {

    return (
        <div>
            <Header />
            <Body>
                <div className="landing-page-content">
                    <DashboardInputField/>
                </div>
            </Body>
            <Footer />
        </div>
    );
};
export default DashboardPage;
