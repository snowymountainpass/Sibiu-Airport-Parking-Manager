import React, {useState} from 'react';
import {useNavigate} from "react-router-dom";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";


const DashboardInputField = () => {

    const [licensePlate, setLicensePlate] = useState('');

    const navigate = useNavigate();

    const handleInputChange = (event) => {
        setLicensePlate(event.target.value);
    };


    //v2
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
        }).then(response => {
            console.log('Response:', response);
        }).then(
            navigate('/')
        )
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });

    }

    return (
        <div>
            <TextField
                label="Car License Plate"
                helperText="Enter your car license plate"
                value={licensePlate}
                onChange={handleInputChange}
            />
            {licensePlate && (
                <Button variant="contained" onClick={addCar}>
                    Add
                </Button>
            )}
        </div>
    );

};
export default DashboardInputField;