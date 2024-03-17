import React, {useEffect, useState} from 'react';
import Header from "../Sections/Header";
import Body from "../Sections/Body";
import Footer from "../Sections/Footer";
import DashboardCarInput from "../Components/DashboardCarInput";
import DashboardAirportInput from "../Components/DashboardAirportInput";
import DashboardParkingSpaceInput from "../Components/DashboardParkingSpaceInput";
import {atom, useAtom, useAtomValue, useSetAtom} from "jotai";
import SectionHeader from "../Components/SectionHeader";
import axios from "axios";


export const numberOfAirportsAtom = atom(0);
export const numberOfParkingSpacesAtom = atom(0);
export const listOfAirportNamesAtom = atom([]);



const DashboardPage = () => {

    // const [listAirportNames,setListAirportNames] = useState([]);//works
    const setListAirportNames = useSetAtom(listOfAirportNamesAtom);
    const listAirportNames = useAtomValue(listOfAirportNamesAtom);

    useEffect(() => {
        axios.get('http://localhost:8080/airports/airportsNames')
            .then(response => {
                // console.log("response.data: "+response.data.result.type);
                setListAirportNames(response.data.result);
            }).catch(error => {
                    console.error('There was a problem with the fetch operation:', error);
                });
    },[]);

    // useEffect(() => {
    //     console.log(listAirportNames);
    //     // console.log("listAirportNames: "+listAirportNames);
    // },[listAirportNames]);

    return (
        <>
            <Header/>
            <Body>
                <SectionHeader>Airport Information</SectionHeader>
                <DashboardAirportInput />
                <SectionHeader>Parking Space Information</SectionHeader>
                <DashboardParkingSpaceInput />
                <SectionHeader>Car Information</SectionHeader>
                {/*<DashboardCarInput />*/}

                {/*<div className="landing-page-content"></div>*/}
            </Body>
            <Footer/>
        </>
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


// <Grid container spacing={2} direction="column">
//     <Grid item container spacing={2}>
//         <SectionHeader>Airport Information</SectionHeader>
//         <DashboardAirportInput/>
//     </Grid>
//     <Divider orientation="horizontal" />
//     <Grid item container spacing={2}>
//         <DashboardParkingSpaceInput/>
//     </Grid>
//     <Divider orientation="horizontal" />
//     <Grid item container spacing={2}>
//         <DashboardCarInput/>
//     </Grid>
// </Grid>

// <Grid container spacing={2} direction="column" >
//     {/* First Section */}
//     <Grid item container spacing={2}>
//         <SectionHeader>Airport Information</SectionHeader>
//         <Grid item xs={12} md={4}>
//             <DashboardAirportInput />
//         </Grid>
//     </Grid>
//     {/* Second Section */}
//     <Grid item container spacing={2}>
//         <SectionHeader>Parking Space Information</SectionHeader>
//         <Grid item xs={12} md={4}>
//             <DashboardParkingSpaceInput />
//         </Grid>
//     </Grid>
//     {/* Third Section */}
//     <Grid item container spacing={2}>
//         <SectionHeader>Car Information</SectionHeader>
//         <Grid item xs={12} md={4}>
//             <DashboardCarInput />
//         </Grid>
//     </Grid>
// </Grid>