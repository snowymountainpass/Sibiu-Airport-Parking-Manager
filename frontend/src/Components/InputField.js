// InputField.js
import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router-dom';
// import {atom, useAtom} from "jotai";

const InputField = async () => {

    // const licensePlateAtom = atom('');

    // const [licensePlate,setLicensePlate] = useAtom(licensePlateAtom);
    const [licensePlate, setLicensePlate] = useState('');
    const navigate = useNavigate();

    const handleInputChange = (event) => {
        setLicensePlate(event.target.value);
    };

    const HandlePayment = () => {
        localStorage.setItem("carLicenseNumber", `${licensePlate}`);
        navigate("/payment");
        console.log('Redirecting to Payment Page');
    }

    //sending license plate to backend - start
    try {
        // send request to backend and wait for the response
        const response = await fetch("http://localhost:8080/licenseplate", {
            method: "POST",
            // Data will be serialized and sent as json
            data: JSON.stringify({
                licenseplate: this.licenseplate,
            }),
            // tell the server we're sending JSON
            headers: {
                "Content-Type": "application/json"
            }
        })
        if (!response.ok) {
            // server returned a status code other than 200-299 --> something went wrong
        }
    } catch (error) {
        // an error occured
    }

    //sending license plate to backend - end

    return (
        <div>
            <TextField
                label="Car Registration Plate"
                helperText="Enter your car registration plate"
                value={licensePlate}
                onChange={handleInputChange}
            />
            {licensePlate && (
                <Button variant="contained" onClick={HandlePayment}>
                    Pay
                </Button>
            )}
        </div>
    );
};

export default InputField;
