import React, {useState} from 'react';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router-dom';


const InputField = ({ className }) => {
    const [licensePlate, setLicensePlate] = useState('');
    const navigate = useNavigate();

    const handleInputChange = (event) => {
        setLicensePlate(event.target.value);
    };

    const getCarDetails = async (e) =>{
        e.preventDefault();
        localStorage.setItem('licensePlate',JSON.stringify(licensePlate));
        navigate('/order/checkout');
    }

    return (
        <div className={`${className} input-field`}>
            <div className="button-container">
                <TextField
                    label="Car License Plate"
                    helperText="Enter your car license plate"
                    value={licensePlate}
                    onChange={handleInputChange}
                    className={className}
                />
                {licensePlate && (
                    <Button variant="contained" onClick={getCarDetails} className="button">
                        Pay
                    </Button>
                )}
            </div>
        </div>
    );
};
export default InputField;



