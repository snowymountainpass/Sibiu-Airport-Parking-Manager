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
import DashboardTable from "../Components/DashboardTable";


export const numberOfAirportsAtom = atom(0);
export const numberOfParkingSpacesAtom = atom(0);
export const listOfAirportNamesAtom = atom([]);



const DashboardPage = () => {

    const setListAirportNames = useSetAtom(listOfAirportNamesAtom);
    const listAirportNames = useAtomValue(listOfAirportNamesAtom);

    useEffect(() => {
        axios.get('http://localhost:8080/airports/airportsNames')
            .then(response => {
                setListAirportNames(response.data.result);
            }).catch(error => {
                    console.error('There was a problem with the fetch operation:', error);
                });
    },[]);

    return (
        <>
            <Header/>
            <Body>
                <SectionHeader>Airport Information</SectionHeader>
                <DashboardAirportInput />
                <SectionHeader>Parking Space Information</SectionHeader>
                <DashboardParkingSpaceInput />
                <SectionHeader>Car Activity Information</SectionHeader>
                <DashboardCarInput />
                <SectionHeader>Overview</SectionHeader>
                <DashboardTable/>
            </Body>
            <Footer/>
        </>
    );
};
export default DashboardPage;