// Table.js
import React from 'react';
import Paper from '@mui/material/Paper';
import TableContainer from '@mui/material/TableContainer';
import Table from '@mui/material/Table';
import TableHead from '@mui/material/TableHead';
import TableBody from '@mui/material/TableBody';
import TableRow from '@mui/material/TableRow';
import TableCell from '@mui/material/TableCell';

const TableComponent = () => {
    const priceData = [
        { duration: '1 hour', cost: 5 },
        { duration: '2 hours', cost: 10 },
        { duration: '3 hour', cost: 15 },
        { duration: '4 hours', cost: 20 },
        { duration: '5 hours', cost: 25 },

    ];

    return (
        <TableContainer component={Paper}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>Duration</TableCell>
                        <TableCell align="right">Cost (Euro/Hour)</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {priceData.map((row) => (
                        <TableRow key={row.duration}>
                            <TableCell>{row.duration}</TableCell>
                            <TableCell align="right">{row.cost}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
};

export default TableComponent;
