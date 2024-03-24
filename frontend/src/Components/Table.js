import React from 'react';
import Paper from '@mui/material/Paper';
import TableContainer from '@mui/material/TableContainer';
import Table from '@mui/material/Table';
import TableHead from '@mui/material/TableHead';
import TableBody from '@mui/material/TableBody';
import TableRow from '@mui/material/TableRow';
import TableCell from '@mui/material/TableCell';

const TableComponent = ({ className }) => {
    const priceData = [
        { duration: '1 minute', cost: 5 },
    ];

    return (
        <TableContainer component={Paper} className={className}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>Duration</TableCell>
                        <TableCell align="right">Cost (Euro/Minute)</TableCell>
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


//ORIGINAL

// <TableContainer component={Paper}>
//     <Table>
//         <TableHead>
//             <TableRow>
//                 <TableCell>Duration</TableCell>
//                 <TableCell align="right">Cost (Euro/Minute)</TableCell>
//             </TableRow>
//         </TableHead>
//         <TableBody>
//             {priceData.map((row) => (
//                 <TableRow key={row.duration}>
//                     <TableCell>{row.duration}</TableCell>
//                     <TableCell align="right">{row.cost}</TableCell>
//                 </TableRow>
//             ))}
//         </TableBody>
//     </Table>
// </TableContainer>

//ORIGINAL