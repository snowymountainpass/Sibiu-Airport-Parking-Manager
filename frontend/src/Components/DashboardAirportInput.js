import React, {useState} from 'react';
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import axios from "axios";
import {Grid, Snackbar} from "@mui/material";
import {atom, useAtom} from "jotai";

export const numberOfAirportsAtom = atom(0);

const DashboardAirportInput = () => {

    const [setNumberOfAirports] = useAtom(numberOfAirportsAtom);

    const [airportName, setAirportName] = useState('');
    const [airportCode, setAirportCode] = useState('');
    const [costPerMinute, setCostPerMinute] = useState('');
    const [isButtonVisible,setIsButtonVisible] = useState(false);
    const [isSnackBarVisible,setIsSnackBarVisible] = useState(false);

    const airportNamePattern = RegExp(
        "^(?!.*(?:DROP\s+(?:TABLE|DATABASE)|TRUNCATE\s+TABLE|ALTER\s+TABLE|UPDATE|DELETE|GRANT|REVOKE|INSERT\s+INTO|CREATE\s+(?:TABLE|INDEX)|DROP\s+INDEX))(?=[\p{L}\p{N}-]{1,150}$).*");
    const airportCodePattern = RegExp("^[a-zA-Z]{0,4}$");
    const costPattern = RegExp("^[1-9]\\d*$");
    const dataMap = new Map();

    const handleInputChange = (event,setterFunction,regexPattern) => {
        const { value } = event.target;
        if(new RegExp(regexPattern).test(value)){
                setterFunction(value);
        }
        if (airportName && airportCode && costPerMinute) {
            setIsButtonVisible(true);
        }
    };

    const addAirport = async (e) => {
        e.preventDefault();
        dataMap.set("airportName",airportName);
        dataMap.set("airportCode",airportCode);
        dataMap.set("costPerMinute", costPerMinute);

        const dataObject = Object.fromEntries(dataMap);

        axios.post('http://localhost:8080/airports/addAirport',{dataObject},{headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
            }})//
            .then((response)=>{
                console.log(response.data);
                console.log(response.data.result);
                // setIsSnackBarVisible(true); //V1 - hardcoded - works out of the box (test it!)
                setIsSnackBarVisible(response.data.result); // V2 - dynamic (test it!)
                setNumberOfAirports(numberOfAirports => numberOfAirports++);
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
                    onChange={(e) => {
                        handleInputChange(e,setAirportName,airportNamePattern);
                        }
                    }
                />

                <TextField
                    label="Airport Code"
                    helperText="Enter the airport code (3 letters)"
                    onChange={(e) => handleInputChange(e,setAirportCode,airportCodePattern)}
                />

                <TextField
                    label="Cost per Minute"
                    helperText="Enter the parking space cost/minute"
                    onChange={(e) => handleInputChange(e,setCostPerMinute,costPattern)}
                />

                {isButtonVisible && (
                    <Button variant="contained" onClick={addAirport}>
                        Add Airport
                    </Button>
                )}

            {
                isSnackBarVisible && (<Snackbar
                    color="primary"
                    size="md"
                    variant="soft"
                    onClose={(event, reason) => {
                        if (reason === 'clickaway') {
                            console.log("New Airport Added: "+new Date().getTime());
                        }
                    }}
                >
                    New Airport Added
                </Snackbar>)
            }
        </div>
    );
};
export default DashboardAirportInput;

//
// return (
//     <div>
//         <TextField
//             label="Airport Name"
//             helperText="Enter the airport name"
//             onChange={(e) => {
//                 handleInputChange(e,setAirportName,airportNamePattern);
//             }
//             }
//         />
//         <TextField
//             label="Airport Code"
//             helperText="Enter the airport code (3 letters)"
//             onChange={(e) => handleInputChange(e,setAirportCode,airportCodePattern)}
//         />
//         <TextField
//             label="Cost per Minute"
//             helperText="Enter the parking space cost/minute"
//             onChange={(e) => handleInputChange(e,setCostPerMinute,costPattern)}
//         />
//         {isButtonVisible && (
//             <Button variant="contained" onClick={addAirport}>
//                 Add Airport
//             </Button>
//         )}
//         {
//             isSnackBarVisible && (<Snackbar
//                 color="primary"
//                 size="md"
//                 variant="soft"
//                 onClose={(event, reason) => {
//                     if (reason === 'clickaway') {
//                         console.log("New Airport Added: "+new Date().getTime());
//                     }
//                 }}
//             >
//                 New Airport Added
//             </Snackbar>)
//         }
//     </div>
// );
