import React, {useEffect, useState} from 'react';
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import axios from "axios";
import {FormControl, Grid, InputLabel, MenuItem, Select, Snackbar} from "@mui/material";
import {atom, useAtom} from "jotai";
import {listOfAirportNamesAtom, numberOfAirportsAtom, numberOfParkingSpacesAtom} from "../Pages/DashboardPage";


const DashboardParkingSpaceInput = () => {

    const [numberOfAirports,setNumberOfAirports] = useAtom(numberOfAirportsAtom);
    const [numberOfParkingSpaces,setNumberOfParkingSpaces] = useAtom(numberOfParkingSpacesAtom);

    const [isSectionVisible,setIsSectionVisible]=useState(false);

    // const [airportsNames, setAirportsNames] = useAtom(listOfAirportNamesAtom);
    const [airportsNames, setAirportsNames] = atom((get)=>get(listOfAirportNamesAtom));
    const [airportSelection, setAirportSelection] = useState('');
    const [isValidAirportSelection, setIsValidAirportSelection] = useState(false);
    const [isButtonVisible,setIsButtonVisible] = useState(false);
    const [isSnackBarVisible,setIsSnackBarVisible] = useState(false);

    const [parkingSpaceName,setParkingSpaceName]=useState('');
    const [isValidParkingSpaceName, setIsValidParkingSpaceName] = useState(true);
    const parkingSpacePattern = RegExp("^(?!0000)[0-9]{4}$");
    const dataMap = new Map();

    // //List of Airport names
    // useEffect(() => {
    //     axios.get('http://localhost:8080/airports/airportsNames')
    //         .then(response => {
    //             setAirportsNames(response.data);
    //             // setNumberOfAirports(airportsNames.length);
    //             setIsSectionVisible(true);
    //         })//
    //         .catch(error => {
    //         console.error('There was a problem with the fetch operation:', error);
    //     });
    // }, []);

    //List of Airport names
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
        if(value!=='' || value!==null){
            setIsValidParkingSpaceName(parkingSpacePattern.test(value));
        }
        else {
            setIsValidParkingSpaceName(false);
        }
    }

    const handleAirportSelectionChange = (event) => {
        const value = event.target.value;
        setAirportSelection(value);
        if(value!=='' || value!==null){
            setIsValidAirportSelection(value !== '');
        }
        else {
            setIsButtonVisible(false);
        }
    };

    const addParkingSpace = async (e) => {

        e.preventDefault();
        dataMap.set("parkingSpaceName",parkingSpaceName);
        dataMap.set("airportName",airportSelection);

        const dataObject = Object.fromEntries(dataMap);

        axios.post('http://localhost:8080/parkingSpaces/addParkingSpace',{dataObject},{headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
            }})//
            .then((response)=>{
                console.log(response.data);
                console.log(response.data.result);
                // setIsSnackBarVisible(true);//V1 - hardcoded - works out of the box (test it!)
                setNumberOfParkingSpaces((nr)=>nr++);//TODO: TEST IT
                setIsSnackBarVisible(response.data.result); //TODO: TEST IT -- V2 - dynamic (test it!)
            })
            .catch(function (error) {
                console.log('Error fetching data:', error);
            });

    }

    return (

        <div>

            {isSectionVisible&&(<TextField
                label="Parking Space Number"
                variant="outlined"
                onChange={handleParkingSpaceInput}
                error={!isValidParkingSpaceName && parkingSpaceName!==''}
                helperText={!isValidParkingSpaceName ? 'Invalid format - try 0001 to 9999' : ''}
            />)}


            {isSectionVisible&&(
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
                        {/*<MenuItem value="" disabled>*/}
                        {/*    Select an Airport*/}
                        {/*</MenuItem>*/}
                        {airportsNames.map((value, index) => (
                            <MenuItem key={index} value={value}>
                                {value}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
            )}


            {isButtonVisible && (
                <Button variant="contained" onClick={addParkingSpace}>
                    Add Parking Space
                </Button>
            )}

            {
                isSnackBarVisible && (<Snackbar
                    color="primary"
                    size="md"
                    variant="soft"
                    onClose={(event, reason) => {
                        if (reason === 'clickaway') {
                            console.log("New Parking Space Added: "+new Date().getTime());
                        }
                    }}
                >
                    New Parking Space Added
                </Snackbar>)
            }
        </div>
    );
};

export default DashboardParkingSpaceInput;


// return (
//
//     <div>
//         {isSectionVisible&&(<TextField
//             label="Parking Space Number"
//             variant="outlined"
//             onChange={handleParkingSpaceInput}
//             error={!isValidParkingSpaceName && parkingSpaceName!==''}
//             helperText={!isValidParkingSpaceName ? 'Invalid format - try 0001 to 9999' : ''}
//         />)}
//
//         {isSectionVisible&&(
//             <FormControl fullWidth>
//                 <InputLabel id="demo-simple-select-label">Airport</InputLabel>
//                 <Select
//                     labelId="demo-simple-select-label"
//                     id="demo-simple-select"
//                     label="Airports"
//                     onChange={handleAirportSelectionChange}
//                     defaultValue=""
//                     displayEmpty
//                 >
//                     {/*<MenuItem value="" disabled>*/}
//                     {/*    Select an Airport*/}
//                     {/*</MenuItem>*/}
//                     {airportsNames.map((value, index) => (
//                         <MenuItem key={index} value={value}>
//                             {value}
//                         </MenuItem>
//                     ))}
//                 </Select>
//             </FormControl>
//         )}
//
//
//         {isButtonVisible && (
//             <Button variant="contained" onClick={addParkingSpace}>
//                 Add Parking Space
//             </Button>
//         )}
//
//         {
//             isSnackBarVisible && (<Snackbar
//                 color="primary"
//                 size="md"
//                 variant="soft"
//                 onClose={(event, reason) => {
//                     if (reason === 'clickaway') {
//                         console.log("New Parking Space Added: "+new Date().getTime());
//                     }
//                 }}
//             >
//                 New Parking Space Added
//             </Snackbar>)
//         }
//     </div>
// );