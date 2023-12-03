// InputField.js
import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { redirect } from 'react-router-dom';

const InputField = ({ onPayment }) => {
    const [licensePlate, setLicensePlate] = useState('');


    const handleInputChange = (event) => {
        setLicensePlate(event.target.value);
    };

    const HandlePayment = () =>{
        onPayment(licensePlate);

        // Redirect to the "/payment" route
        redirect('/payment');
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
                <Button variant="contained" onClick={() => HandlePayment}>
                    Pay
                </Button>
            )}
        </div>
    );
};

export default InputField;
