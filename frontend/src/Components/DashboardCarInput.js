import React, {useState} from 'react';
import {useNavigate} from "react-router-dom";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import {Snackbar} from "@mui/material";

const DashboardCarInput = () => {
    const [licensePlate, setLicensePlate] = useState('');
    //TODO: add frontend regex to check valididty of License Plate
    const [validLicensePlate, setValidLicensePlate] = useState(true);
    const [isSnackBarVisible,setIsSnackBarVisible] = useState(false);

    const navigate = useNavigate();

    const handleInputChange = (event) => {
        setLicensePlate(event.target.value);
    };

    const addCar = async (e) => {
        e.preventDefault();

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
        // if(validLicensePlate===true){
        //     navigate('/');
        // }

    }

    return (
        <div>
            <TextField
                label="Car License Plate"
                helperText="Enter your car license plate"
                value={licensePlate}
                onChange={handleInputChange}
                error={!validLicensePlate}
            />
            {licensePlate && (
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