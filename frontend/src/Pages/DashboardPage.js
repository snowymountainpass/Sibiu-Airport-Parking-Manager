import React, {useEffect, useState} from 'react';
import Header from "../Sections/Header";
import Body from "../Sections/Body";
import Footer from "../Sections/Footer";
import DashboardCarInput from "../Components/DashboardCarInput";
import DashboardAirportInput from "../Components/DashboardAirportInput";
import DashboardParkingSpaceInput from "../Components/DashboardParkingSpaceInput";
import {Divider, Grid} from "@mui/material";

const DashboardPage = () => {

    return (
        <div>
            <Header />
            <Body>
                <Grid container spacing={2} direction="column">
                    <Grid item container spacing={2}>
                        <DashboardAirportInput/>
                    </Grid>
                    <Divider orientation="horizontal" />
                    <Grid item container spacing={2}>
                        <DashboardParkingSpaceInput/>
                    </Grid>
                    <Divider orientation="horizontal" />
                    <Grid item container spacing={2}>
                        <DashboardCarInput/>
                    </Grid>
                </Grid>
                {/*<div className="landing-page-content"></div>*/}
            </Body>
            <Footer />
        </div>
    );
};
export default DashboardPage;


// <div>
//     <Header />
//     <Body>
//         <div className="landing-page-content">
//             <DashboardAirportInput/>
//             <Divider orientation="horizontal" />
//             <DashboardParkingSpaceInput/>
//             <Divider orientation="horizontal" />
//             <DashboardCarInput/>
//         </div>
//     </Body>
//     <Footer />
// </div>

// <Grid container spacing={2}>
//     {/* First Section */}
//     <Grid item xs={4}>
//         <DashboardAirportInput/>
//     </Grid>
//     <Divider orientation="horizontal" />
//     {/* Second Section */}
//     <Grid item xs={4}>
//         <DashboardParkingSpaceInput/>
//     </Grid>
//     <Divider orientation="horizontal" />
//     {/* Third Section */}
//     <Grid item xs={4}>
//         <DashboardCarInput/>
//     </Grid>
// </Grid>

// {/* First Row */}
// <Grid item container spacing={2}>
//     {/* First Section */}
//     <Grid item xs={12} md={4}>
//         <DashboardAirportInput/>
//     </Grid>
//
//     {/* Second Section */}
//     <Grid item xs={12} md={4}>
//         <DashboardParkingSpaceInput/>
//     </Grid>
//
//     {/* Third Section */}
//     <Grid item xs={12} md={4}>
//         <DashboardCarInput/>
//     </Grid>
// </Grid>
// <Divider orientation="horizontal" />