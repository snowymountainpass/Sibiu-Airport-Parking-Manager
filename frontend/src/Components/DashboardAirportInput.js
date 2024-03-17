import React, {useState,useEffect} from 'react';
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import axios from "axios";
import {Alert, Snackbar} from "@mui/material";
import {useAtom} from "jotai";
import {numberOfAirportsAtom} from "../Pages/DashboardPage";

const DashboardAirportInput = () => {

    const [numberOfAirports,setNumberOfAirports] = useAtom(numberOfAirportsAtom);

    const [airportName, setAirportName] = useState('');
    const [airportCode, setAirportCode] = useState('');
    const [costPerMinute, setCostPerMinute] = useState('');
    const [isButtonVisible,setIsButtonVisible] = useState(false);
    const [isSnackBarVisible,setIsSnackBarVisible] = useState(false);

    const airportNamePattern = new RegExp("^(?!.*(?:DROP\\s+(?:TABLE|DATABASE)|TRUNCATE\\s+TABLE|ALTER\\s+TABLE|UPDATE|DELETE|GRANT|REVOKE|INSERT\\s+INTO|CREATE\\s+(?:TABLE|INDEX)|DROP\\s+INDEX))(?=[a-zA-Z0-9\\s-]{1,150}$).*");
    const airportCodePattern = RegExp("^[a-zA-Z]{0,4}$");
    const costPattern = RegExp("^[1-9]\\d*$");
    const dataMap = new Map();

    useEffect(()=>{
        if(airportName!==''&&airportCode!==''&&costPerMinute!==''){
            if(airportNamePattern.test(airportName)&&airportCodePattern.test(airportCode)&&costPattern.test(costPerMinute)){
                setIsButtonVisible(true);
            }
        }else{
            setIsButtonVisible(false);
        }
    },[airportName,airportCode,costPerMinute])

    const handleAirportNameInput = (event) => {
        const result = event.target.value;
        setAirportName(result);
    };
    const handleAirportCodeInput = (event) => {
        const result = event.target.value;
        setAirportCode(result);
    };
    const handleCostInput = (event) => {
        const result = event.target.value;
        setCostPerMinute(result);
    };

    const handleClose = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }
        setIsSnackBarVisible(false);
    };

    const addAirport = async (e) => {
        e.preventDefault();
        dataMap.set("airportName",airportName);
        dataMap.set("airportCode",airportCode);
        dataMap.set("costPerMinute", costPerMinute);
        const dataObject = Object.fromEntries(dataMap);
        console.log("dataMap: "+dataMap);
        axios.post('http://localhost:8080/airports/addAirport',dataObject,{headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
            }})//
            .then((response)=>{
                setIsSnackBarVisible(response.data.result);
                setNumberOfAirports((numberOfAirports) => numberOfAirports++);
                console.log("numberOfAirports: "+numberOfAirports)
                setAirportName('');
                setAirportCode('');
                setCostPerMinute('');
            })
            .catch(function (error) {
                console.log('Error fetching data:', error);
            });

    }

    return (
        <div>
            <TextField
                label="Airport Name"
                helperText="Enter the airport name"
                value={airportName}
                onChange={handleAirportNameInput}
            />

            <TextField
                label="Airport Code"
                helperText="Enter the airport code (3 letters)"
                value={airportCode}
                onChange={handleAirportCodeInput}
            />

            <TextField
                label="Cost per Minute"
                helperText="Enter the parking space cost/minute"
                value={costPerMinute}
                onChange={handleCostInput}
            />

            {isButtonVisible && (
                <Button variant="contained" onClick={addAirport}>
                    Add Airport
                </Button>
            )}

             <Snackbar
                open={isSnackBarVisible}
                autoHideDuration={6000}
                onClose={handleClose}
                // action={SnackbarButton}
                color="primary"
                size="md"
                variant="soft"
                anchorOrigin={{vertical:'bottom', horizontal:'right'}}>
                 <Alert
                     onClose={handleClose}
                     severity="success"
                     variant="filled"
                     sx={{ width: '100%' }}>
                     New Airport Added!
                 </Alert>
             </Snackbar>
        </div>
    );
};
export default DashboardAirportInput;