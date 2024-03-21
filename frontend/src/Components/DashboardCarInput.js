import React, {useState,useEffect} from 'react';
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import {FormControl, FormHelperText, InputLabel, MenuItem, Select, Snackbar} from "@mui/material";
import {atom, useAtom,useAtomValue} from "jotai";
import {listOfAirportNamesAtom, numberOfAirportsAtom, numberOfParkingSpacesAtom} from "../Pages/DashboardPage";
import axios from "axios";
import {useNavigate} from "react-router-dom";

const DashboardCarInput = () => {

    // const [numberOfAirports,setNumberOfAirports] = useAtom(numberOfAirportsAtom);
    // const [numberOfParkingSpaces,setNumberOfParkingSpaces] = useAtom(numberOfParkingSpacesAtom);

    const listAirportNames = useAtomValue(listOfAirportNamesAtom);
    const numberOfAirports = Array.isArray(listAirportNames) ? listAirportNames.length : 0;
    const[listAirportsWithEmptyParkingSpaces,setListAirportsWithEmptyParkingSpaces]= useState([]);
    const [licensePlate, setLicensePlate] = useState('');
    // const licensePlateValidPattern = new RegExp("^(?!.*(?:DROP\s+(?:TABLE|DATABASE)|TRUNCATE\s+TABLE|ALTER\s+TABLE|UPDATE|DELETE|GRANT|REVOKE|INSERT\s+INTO|CREATE\s+(?:TABLE|INDEX)|DROP\s+INDEX))[\p{L}\p{N}\s-]+$/");
    const licensePlateValidPattern = /^(?!.*(?:DROP\s+(?:TABLE|DATABASE)|TRUNCATE\s+TABLE|ALTER\s+TABLE|UPDATE|DELETE|GRANT|REVOKE|INSERT\s+INTO|CREATE\s+(?:TABLE|INDEX)|DROP\s+INDEX))[\p{L}\d\s-]+$/u;

    const [validLicensePlate, setValidLicensePlate] = useState(false);

    const [airportSelection, setAirportSelection] = useState('');
    const [isValidAirportSelection, setIsValidAirportSelection] = useState(false);

    const [parkingSpaceNames, setParkingSpaceNames] = useState([]);
    const [parkingSpaceSelection, setParkingSpaceSelection] = useState('');
    const [isValidParkingSpaceSelection, setIsValidParkingSpaceSelection] = useState(false);

    const [isSectionVisible,setIsSectionVisible]=useState(false);
    const [isSnackBarVisible,setIsSnackBarVisible] = useState(false);
    const [isButtonVisible,setIsButtonVisible] = useState(false);

    const dataMap = new Map();
    const navigate = useNavigate();

    const handleInputChange = (event) => {
        setLicensePlate(event.target.value);
        // console.log(licensePlate);
        if(licensePlateValidPattern.test(licensePlate)){
            setValidLicensePlate(true);
            // console.log("ValidLicensePlate (true?):"+validLicensePlate);
        }
        else {
            setValidLicensePlate(false);
            // console.log("ValidLicensePlate (true?):"+validLicensePlate);
        }
    };

    const getAirportsWithEmptyParkingSpaces = () => {
        axios.get('http://localhost:8080/airports/airportsWithEmptyParkingSpaces')
            .then(response => {
                // console.log(response.data.result);
                setListAirportsWithEmptyParkingSpaces(response.data.result);
            }).catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
        // return '';
    }

    const handleAirportSelectionChange = (event) => {
        const result = event.target.value;
        setAirportSelection(result);
        if(result!==''){
            setIsValidAirportSelection(true);
        }
        else {
            setIsValidAirportSelection(false);
        }
    };

    const handleAParkingSpaceSelectionChange = (event) => {
        const result = event.target.value;
        if(result!=='' || result!==null){
            setParkingSpaceSelection(result)
            setIsValidParkingSpaceSelection(true);
        }
        else {
            setIsValidParkingSpaceSelection(false);
        }
    }

    useEffect(() => {
        if(airportSelection!==''){
            axios.post('http://localhost:8080/parkingSpaces/getParkingSpaces/'+airportSelection,{headers: {
                'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*",
                    "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
            }})
                .then(response => {
                    // console.log("airport selection result: "+response.data.result);
                    // console.log(response.data.result);
                    setParkingSpaceNames(response.data.result); //TODO: test it
                })//
                .catch(error => {
                    console.error('There was a problem with the fetch operation:', error);
                });
        }

    },[airportSelection]);

    useEffect(() => {
        if(isValidAirportSelection&&validLicensePlate){
            setIsSectionVisible(true);
        }
        else{
            setIsSectionVisible(false);
        }
    },[isValidAirportSelection]);

    useEffect(()=>{
        if(validLicensePlate&&isValidAirportSelection){ //TODO: add this as well -> isValidParkingSpaceName
            setIsButtonVisible(true);
        }else{
            setIsButtonVisible(false);
        }
    },[validLicensePlate,isValidAirportSelection]) //isValidParkingSpaceName

    const addCar = async (e) => {
        e.preventDefault();

        // console.log("licensePlate",licensePlate);
        // console.log("parkingSpaceName",parkingSpaceSelection);
        // console.log("airportName",airportSelection);

        dataMap.set("licensePlate",licensePlate);
        dataMap.set("parkingSpaceName",parkingSpaceSelection);
        dataMap.set("airportName",airportSelection);

        // console.log("licensePlate",dataMap.get(licensePlate.toString()));
        // console.log("parkingSpaceName",dataMap.get(parkingSpaceSelection));
        // console.log("airportName",dataMap.get(airportSelection));

        const dataObject = Object.fromEntries(dataMap);

        axios.post('http://localhost:8080/parkedCars/addCar',dataObject,{headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
            }})//
            .then((response)=>{
                // console.log(response.data);
                // console.log(response.data.result);
                // setIsSnackBarVisible(true);//V1 - hardcoded - works out of the box (test it!)
                // setNumberOfParkingSpaces((nr)=>nr++);
                setIsSnackBarVisible(response.data.result);
            })
            .catch(function (error) {
                console.log('Error fetching data:', error);
            });

        navigate('/');

    }

    return (
        <div>
            {
                (numberOfAirports>0)&&<TextField
                    label="Car License Plate"
                    helperText="Enter your car license plate"
                    value={licensePlate}
                    onChange={handleInputChange}
                    error={licensePlate!==''?!validLicensePlate:validLicensePlate}
                    onKeyDown={
                    (event =>
                        {
                            if(event.key==='Enter'){
                                console.log('Enter pressed');
                                getAirportsWithEmptyParkingSpaces();
                            }
                        }
                    )}
                />
            }

            {(validLicensePlate )&&(
                <FormControl fullWidth>
                    <InputLabel id="demo-simple-select-label">Airport</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        label="Airports"
                        onChange={handleAirportSelectionChange}
                        defaultValue=""
                        displayEmpty
                        style={{ minWidth: 223 }}
                        fullWidth
                    >
                        <MenuItem value="" disabled/>
                        {listAirportsWithEmptyParkingSpaces.map((value, index) => ( //listAirportNames //listAirportsWithEmptyParkingSpaces
                            <MenuItem key={index} value={value}>
                                {value}
                            </MenuItem>
                        ))}
                    </Select>
                    <FormHelperText>Select an Airport</FormHelperText>
                </FormControl>
            )}

            {isSectionVisible&&(
                <FormControl fullWidth>
                    <InputLabel id="demo-simple-select-label">Parking Space</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        label="Parking Space"
                        onChange={handleAParkingSpaceSelectionChange}
                        defaultValue=""
                        displayEmpty
                        style={{ minWidth: 223 }}
                        fullWidth
                    >
                        <MenuItem value="" disabled/>
                        {parkingSpaceNames.map((value, index) => (
                            <MenuItem key={index} value={value}>
                                {value}
                            </MenuItem>
                        ))}
                    </Select>
                    <FormHelperText>Select an Parking Space</FormHelperText>
                </FormControl>
            )}

            {isButtonVisible && (
                <Button variant="contained" onClick={addCar}>
                    Add Car
                </Button>
            )}
            {
                isSnackBarVisible && (<Snackbar
                    color="primary"
                    size="md"
                    variant="soft"
                    onClose={(event, reason) => {
                        if (reason === 'clickaway') {
                            console.log("New Car Added: "+new Date().getTime());
                        }
                    }}
                >
                    New Airport Added
                </Snackbar>)
            }
        </div>
    );
};
export default DashboardCarInput;