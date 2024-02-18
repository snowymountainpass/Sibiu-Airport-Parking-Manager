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


    const getCarDetails = async () =>{

        try {

            const response = await axios.post('/api/sendString', { licensePlate });

        }catch (error){
            console.error(error);
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
