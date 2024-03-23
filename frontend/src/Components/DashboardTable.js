import * as React from 'react';
import {useEffect, useState} from "react";
import Box from '@mui/material/Box';
import { DataGrid } from '@mui/x-data-grid';
// import {useEffect} from "react";
import axios from "axios";




    // {
    //     field: 'firstName',
    //     headerName: 'First name',
    //     width: 150,
    //     editable: true,
    // },
    // {
    //     field: 'lastName',
    //     headerName: 'Last name',
    //     width: 150,
    //     editable: true,
    // },
    // {
    //     field: 'age',
    //     headerName: 'Age',
    //     type: 'number',
    //     width: 110,
    //     editable: true,
    // },
    // {
    //     field: 'fullName',
    //     headerName: 'Full name',
    //     description: 'This column has a value getter and is not sortable.',
    //     sortable: false,
    //     width: 160,
    //     valueGetter: (value, row) => `${row.firstName || ''} ${row.lastName || ''}`,
    // },
// ];






const DashboardTable = () => {

    const columns = [
        // { field: 'id', headerName: 'ID', width: 120 },
        { field: 'licensePlate', headerName: 'License Plate', width: 120 },
        { field: 'airportName', headerName: 'Airport Name', width: 120 },
        { field: 'parkingSpaceName', headerName: 'Parking Space', width: 120 },
        { field: 'startTime', headerName: 'Start Time', width: 120 },
        { field: 'timeSpent', headerName: 'Time Spent (minutes)', width: 200 }];

    const [parkedCars,setParkedCars] = useState([]);


    const updateTable = () => {

        console.log('Called updateTable function!')

        axios.get('http://localhost:8080/parkingActivities/getParkedCars')
            .then(response => {
                setParkedCars(response.data.result);

            }).catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
    }
    // setInterval(updateTable, 480000); //1 minute - 60000 //TODO: Create a Update Table button => update table when clicked


    return (
        <Box sx={{ height: 400, width: '100%' }}>
            <DataGrid
                // rows={rows}
                rows={parkedCars}
                columns={columns}
                initialState={{
                    pagination: {
                        paginationModel: {
                            pageSize: 5,
                        },
                    },
                }}
                pageSizeOptions={[5]}
                checkboxSelection
                disableRowSelectionOnClick
            />
        </Box>
    );
}
export default DashboardTable;