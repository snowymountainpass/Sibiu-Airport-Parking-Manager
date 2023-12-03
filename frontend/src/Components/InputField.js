// InputField.js
import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router-dom';
import {atom, useAtom} from "jotai";

const InputField = () => {

    // const licensePlateAtom = atom('');

    // const [licensePlate,setLicensePlate] = useAtom(licensePlateAtom);
    const [licensePlate,setLicensePlate] = useState('');
    const navigate = useNavigate();

    const handleInputChange = (event) => {
        setLicensePlate(event.target.value);
    };

    const HandlePayment =() => {
        localStorage.setItem("licensePlateValue", `${licensePlate}`);
        navigate("/payment");
        console.log('Redirecting to Payment Page');
    }

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
