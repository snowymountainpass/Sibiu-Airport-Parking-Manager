// InputField.js
import React, {useState} from 'react';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';


const InputField = () => {

    const [licensePlate, setLicensePlate] = useState('');
    const [paymentDetails, setPaymentDetails] = useState();

    const navigate = useNavigate();

    const handleInputChange = (event) => {
        setLicensePlate(event.target.value);
    };

    //v1
    //'http://localhost:8080/payment/payment_details'
    // const getCarDetails = async (e) =>{
    //     e.preventDefault();
    //     const headers = {
    //         "Access-Control-Allow-Origin": "*",
    //         "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept",
    //     };
    //     await axios.get(
    //         'http://localhost:8080/payment/payment_details',
    //         {
    //             licensePlate: licensePlate,
    //         },
    //     ).then(response => {
    //         console.log("Success ========>", response);
    //     })
    //         .catch(error => {
    //                 console.log("Error ========>", error);
    //             }
    //         )
    // }
    //v1

    //v2
    const getCarDetails = async (e) =>{
        e.preventDefault();
        fetch('http://localhost:8080/payment/payment_details', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Headers": "Origin, X-Requested-With, Content-Type, Accept"
            },
            // Send the string in the request body as JSON
            body: JSON.stringify({ licensePlate: licensePlate })
        })
            // Chain the .then() to handle the response
            .then(response => {
                // Check if the response status is OK
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                // Parse the JSON response
                return response.json();
            })
            // Chain another .then() to log the parsed response
            .then(data => {
                console.log('Response:', data);
            })
            // Chain a .catch() to handle any errors during the fetch
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });
    }

    //v2


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
                <Button variant="contained" onClick={getCarDetails}>
                    Pay
                </Button>
            )}
        </div>
    );
};

export default InputField;
