// InputField.js
import React, {useState} from 'react';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';


const InputField = () => {

    const [licensePlate, setLicensePlate] = useState('');
    const navigate = useNavigate();

    const handleInputChange = (event) => {
        setLicensePlate(event.target.value);
    };


    const HandlePayment = async () =>{
        try {
            // const response = await axios.post('http://localhost:8080/licenseplate', { licensePlate });
            // console.log('API Response:', response.data);

            console.log('License plate data sent successfully!');
            localStorage.setItem("carLicenseNumber", `${licensePlate}`);
            navigate("/payment");
            console.log('Redirecting to Payment Page');

        } catch (error) {
            console.error('Error sending request:', error);
        }
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
