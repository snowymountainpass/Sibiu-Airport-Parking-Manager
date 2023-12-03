// PaymentPageBody.js
import React, {useEffect} from 'react';
import { Divider, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography } from '@mui/material';
// import PaymentForm from './PaymentForm';

const PaymentPageBody = ({ paymentDetails }) => {
    const carLicenseNumber = localStorage.getItem('carLicenseNumber');
    const startDate = '2023-11-20T08:00:00'; // TODO: replace with server stored start date & time

    // TODO: replace with current server date & time
    const endDate = new Date().toISOString();

    const DisplayAmount = () => {
      console.log(paymentDetails.amount);
    }

    useEffect(()=>{
        DisplayAmount();
    },[])

    return (
        <div>
            {/*<PaymentForm />*/}

            <Divider style={{ margin: '20px 0' }} />

            <Typography variant="h6" gutterBottom>
                Payment Details Table
            </Typography>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Car License Number</TableCell>
                            <TableCell>Start Date & Time</TableCell>
                            <TableCell>End Date & Time</TableCell>
                            <TableCell>Payment Amount</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        <TableRow>
                            <TableCell>{carLicenseNumber}</TableCell>
                            <TableCell>{startDate}</TableCell>
                            <TableCell>{endDate}</TableCell>
                            <TableCell>{paymentDetails.amount/100} EUR</TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
};

export default PaymentPageBody;
