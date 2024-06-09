import React, {useEffect, useState} from 'react';
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import axios from "axios";
import {Alert, FormControl, FormHelperText, InputLabel, MenuItem, Select, Snackbar} from "@mui/material";
import {useAtomValue} from "jotai";
import {listOfAirportNamesAtom} from "../Pages/DashboardPage";


const DashboardParkingSpaceInput = () => {

    const listAirportNames = useAtomValue(listOfAirportNamesAtom);
    const [airportSelection, setAirportSelection] = useState('');
    const [isValidAirportSelection, setIsValidAirportSelection] = useState(false);
    const [isValidParkingSpaceName, setIsValidParkingSpaceName] = useState(false);
    const airportNamePattern = new RegExp("^(?!.*(?:DROP\\s+(?:TABLE|DATABASE)|TRUNCATE\\s+TABLE|ALTER\\s+TABLE|UPDATE|DELETE|GRANT|REVOKE|INSERT\\s+INTO|CREATE\\s+(?:TABLE|INDEX)|DROP\\s+INDEX))(?=[a-zA-Z0-9\\s-]{1,150}$).*");

    const [parkingSpaceName,setParkingSpaceName]=useState('');
    const parkingSpacePattern = RegExp("^(?!0000)[0-9]{4}$");

    const [isSectionVisible,setIsSectionVisible]=useState(false);
    const [isButtonVisible,setIsButtonVisible] = useState(false);
    const [isSnackBarVisible,setIsSnackBarVisible] = useState(false);
    const numberOfAirports = Array.isArray(listAirportNames) ? listAirportNames.length : 0;
    const dataMap = new Map();

    useEffect(() => {
        if(numberOfAirports>0){
            setIsSectionVisible(true);
        }
    }, [numberOfAirports]);

    useEffect(()=>{
        if(isValidParkingSpaceName&&isValidAirportSelection){
            setIsButtonVisible(true);
        }else{
            setIsButtonVisible(false);
        }
    },[isValidParkingSpaceName,isValidAirportSelection])

    const handleParkingSpaceInput = (event) =>{
        const value = event.target.value;
        setParkingSpaceName(value);
        if(value!==''){
            setIsValidParkingSpaceName(parkingSpacePattern.test(value));
        }
        else {
            setIsValidParkingSpaceName(false);
            setIsButtonVisible(false);
        }
    }

    const handleAirportSelectionChange = (event) => {
        const value = event.target.value;
        setAirportSelection(value);
        if(value!==''){
            if(airportNamePattern.test(value)){
                setIsValidAirportSelection(airportNamePattern.test(value));
            }
        }
    };
    const handleClose = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }
        setIsSnackBarVisible(false);
    };

    const addParkingSpace = async (e) => {

        e.preventDefault();
        dataMap.set("parkingSpaceName",parkingSpaceName);
        dataMap.set("airportName",airportSelection);

        const dataObject = Object.fromEntries(dataMap);

        axios.post('http://localhost:8080/parkingSpaces/addParkingSpace',dataObject,{headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
            }})//
            .then((response)=>{
                setIsSnackBarVisible(response.data.result);
                setParkingSpaceName('');
                setAirportSelection('');
                setIsValidParkingSpaceName(false);
                setIsValidAirportSelection(false)
                setIsButtonVisible(false);
            })
            .catch(function (error) {
                console.log('Error fetching data:', error);
            });

    }

    return (
        <div className="input-field">
            {isSectionVisible&&(<TextField
                label="Parking Space Number"
                variant="outlined"
                value={parkingSpaceName}
                onChange={handleParkingSpaceInput}
                error={!isValidParkingSpaceName && parkingSpaceName!==''}
                helperText={!isValidParkingSpaceName ? 'Valid format - try 0001 to 9999' : ''}
            />)}

            {isSectionVisible&&(
                <FormControl fullWidth>
                    <InputLabel id="simple-select-label">Airport</InputLabel>
                    <Select
                        labelId="simple-select-label"
                        id="demo-simple-select"
                        label="Airports"
                        onChange={handleAirportSelectionChange}
                        defaultValue=""
                        displayEmpty
                        style={{ minWidth: 223 }}
                        fullWidth
                    >
                        <MenuItem value="" disabled/>
                        {listAirportNames.map((value, index) => (
                            <MenuItem key={index} value={value}>
                                {value}
                            </MenuItem>
                        ))}
                    </Select>
                    <FormHelperText>Select an Airport</FormHelperText>
                </FormControl>
            )}

            {isButtonVisible && (
                <Button variant="contained" onClick={addParkingSpace}>
                    Add Parking Space
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
                    New Parking Space Added!
                </Alert>
            </Snackbar>
        </div>
    );
};

export default DashboardParkingSpaceInput;