import React, {useState,useEffect} from 'react';
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import {FormControl, InputLabel, MenuItem, Select, Snackbar} from "@mui/material";
import {atom, useAtom,useAtomValue} from "jotai";
import {listOfAirportNamesAtom, numberOfAirportsAtom, numberOfParkingSpacesAtom} from "../Pages/DashboardPage";
import axios from "axios";

const DashboardCarInput = () => {

    // const [numberOfAirports,setNumberOfAirports] = useAtom(numberOfAirportsAtom);
    // const [numberOfParkingSpaces,setNumberOfParkingSpaces] = useAtom(numberOfParkingSpacesAtom);

    const listAirportNames = useAtomValue(listOfAirportNamesAtom);
    const numberOfAirports = Array.isArray(listAirportNames) ? listAirportNames.length : 0;

    const [licensePlate, setLicensePlate] = useState('');
    const licensePlateValidPattern = new RegExp("^(?!.*(?:DROP\s+(?:TABLE|DATABASE)|TRUNCATE\s+TABLE|ALTER\s+TABLE|UPDATE|DELETE|GRANT|REVOKE|INSERT\s+INTO|CREATE\s+(?:TABLE|INDEX)|DROP\s+INDEX))[\p{L}\p{N}\s-]+$/");
    //TODO: licensePlateValidPattern - apply it
    const [validLicensePlate, setValidLicensePlate] = useState(true);
    const [isSnackBarVisible,setIsSnackBarVisible] = useState(false);


    const [airportSelection, setAirportSelection] = useState('');
    const [isValidAirportSelection, setIsValidAirportSelection] = useState(false);
    const [isButtonVisible,setIsButtonVisible] = useState(false);

    const [parkingSpaceNames, setParkingSpaceNames] = useState([]);
    const [parkingSpaceSelection, setParkingSpaceSelection] = useState('');
    const [isValidParkingSpaceSelection, setIsValidParkingSpaceSelection] = useState(false);

    const [isSectionVisible,setIsSectionVisible]=useState(false);

    const dataMap = new Map();

    const handleInputChange = (event) => {
        setLicensePlate(event.target.value);
    };

    const handleAirportSelectionChange = (event) => {
        const result = event.target.value;
        if(result!=='' || result!==null){
            setAirportSelection(result);
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

    // useEffect(() => {
    //     axios.get('http://localhost:8080/parkingSpaces/getParkingSpace/${airportSelection}')
    //         .then(response => {
    //             setParkingSpaceNames(response.data.result); //TODO: test it
    //         })//
    //         .catch(error => {
    //             console.error('There was a problem with the fetch operation:', error);
    //         });
    // },[airportSelection]);

    useEffect(() => {
        if(isValidAirportSelection&&isValidParkingSpaceSelection){
            setIsSectionVisible(true);
        }
        else{
            setIsSectionVisible(false);
        }
    },[isValidAirportSelection]);

    const addCar = async (e) => {
        e.preventDefault();

        dataMap.set("licensePlate",licensePlate);
        dataMap.set("parkingSpaceName",parkingSpaceSelection);
        dataMap.set("airportName",airportSelection);

        const dataObject = Object.fromEntries(dataMap);

        axios.post('http://localhost:8080/parkedCars/addCar',{dataObject},{headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
            }})//
            .then((response)=>{
                console.log(response.data);
                console.log(response.data.result);
                // setIsSnackBarVisible(true);//V1 - hardcoded - works out of the box (test it!)
                // setNumberOfParkingSpaces((nr)=>nr++);//TODO: TEST IT
                setIsSnackBarVisible(response.data.result); //TODO: TEST IT -- V2 - dynamic (test it!)
            })
            .catch(function (error) {
                console.log('Error fetching data:', error);
            });


        await fetch('http://localhost:8080/parkedCars/addCar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
            },
            // Send the string in the request body as JSON
            body: JSON.stringify({licensePlate: licensePlate})
        }).then((response) => response.json())
            .then((data)=>{
                setValidLicensePlate(data.validLicensePlate);
                setIsSnackBarVisible(true);
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });


    }

    return (
        <div>
            {
                (numberOfAirports>0)&&<TextField //&& numberOfParkingSpaces>0 (TODO: Fetch if there is at least one airport with an empty parking space)
                    label="Car License Plate"
                    helperText="Enter your car license plate"
                    value={licensePlate}
                    onChange={handleInputChange}
                    error={!validLicensePlate}
                />
            }

            {/*{(numberOfAirports>0 && validLicensePlate)&&(*/}
            {/*    <FormControl fullWidth>*/}
            {/*        <InputLabel id="demo-simple-select-label">Airport</InputLabel>*/}
            {/*        <Select*/}
            {/*            labelId="demo-simple-select-label"*/}
            {/*            id="demo-simple-select"*/}
            {/*            label="Airports"*/}
            {/*            onChange={handleAirportSelectionChange}*/}
            {/*            defaultValue=""*/}
            {/*            displayEmpty*/}
            {/*            style={{ minWidth: 223 }}*/}
            {/*            fullWidth*/}
            {/*        >*/}
            {/*            /!*<MenuItem value="" disabled>*!/*/}
            {/*            /!*    Select an Airport*!/*/}
            {/*            /!*</MenuItem>*!/*/}
            {/*            {airportsNames.map((value, index) => (*/}
            {/*                <MenuItem key={index} value={value}>*/}
            {/*                    {value}*/}
            {/*                </MenuItem>*/}
            {/*            ))}*/}
            {/*        </Select>*/}
            {/*    </FormControl>*/}
            {/*)}*/}

            {/*{isSectionVisible&&(*/}
            {/*    <FormControl fullWidth>*/}
            {/*        <InputLabel id="demo-simple-select-label">Parking Space</InputLabel>*/}
            {/*        <Select*/}
            {/*            labelId="demo-simple-select-label"*/}
            {/*            id="demo-simple-select"*/}
            {/*            label="Parking Space"*/}
            {/*            onChange={handleAParkingSpaceSelectionChange}*/}
            {/*            defaultValue=""*/}
            {/*            displayEmpty*/}
            {/*            style={{ minWidth: 223 }}*/}
            {/*            fullWidth*/}
            {/*        >*/}
            {/*            /!*<MenuItem value="" disabled>*!/*/}
            {/*            /!*    Select an Airport*!/*/}
            {/*            /!*</MenuItem>*!/*/}
            {/*            {parkingSpaceNames.map((value, index) => (*/}
            {/*                <MenuItem key={index} value={value}>*/}
            {/*                    {value}*/}
            {/*                </MenuItem>*/}
            {/*            ))}*/}
            {/*        </Select>*/}
            {/*    </FormControl>*/}
            {/*)}*/}

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

// return (
//     <div>
//         <TextField
//             label="Car License Plate"
//             helperText="Enter your car license plate"
//             value={licensePlate}
//             onChange={handleInputChange}
//             error={!validLicensePlate}
//         />
//         {licensePlate && (
//             <Button variant="contained" onClick={addCar}>
//                 Add Car
//             </Button>
//         )}
//         {
//             isSnackBarVisible && (<Snackbar
//                 color="primary"
//                 size="md"
//                 variant="soft"
//                 onClose={(event, reason) => {
//                     if (reason === 'clickaway') {
//                         console.log("New Car Added: "+new Date().getTime());
//                     }
//                 }}
//             >
//                 New Airport Added
//             </Snackbar>)
//         }
//     </div>
// );