import * as React from 'react';
import {useState} from "react";
import Box from '@mui/material/Box';
import { DataGrid } from '@mui/x-data-grid';
import axios from "axios";
import Button from "@mui/material/Button";


const DashboardTable = () => {

    const columns = [
        { field: 'licensePlate', headerName: 'License Plate', width: 120 },
        { field: 'airportName', headerName: 'Airport Name', width: 250 },
        { field: 'parkingSpaceName', headerName: 'Parking Space', width: 200 },
        { field: 'startTime', headerName: 'Start Time', width: 250 },
        { field: 'timeSpent', headerName: 'Time Spent (minutes)', width: 200 }];

    const [parkedCars,setParkedCars] = useState([]);


    const updateTable = () => {
        axios.get('http://localhost:8080/parkingActivities/getParkedCars')
            .then(response => {
                setParkedCars(response.data.result);
            }).catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
    }
    // setInterval(updateTable, 480000); //1 minute - 60000


    return (
        <Box sx={{ height: 400, width: '100%', paddingBottom:10 }}>
            <Button variant="contained" onClick={updateTable}>
                Update
            </Button>
            <DataGrid
                rows={parkedCars}
                columns={columns}
                initialState={{
                    pagination: {
                        paginationModel: {
                            pageSize: 5,
                        },
                    },
                }}
                autosizeOptions={{
                    columns: ['licensePlate','airportName', 'parkingSpaceName','startTime','timeSpent'],
                    includeOutliers: true,
                    includeHeaders: false,
                }}
                pageSizeOptions={[5]}
                checkboxSelection
                disableRowSelectionOnClick
            />
        </Box>
    );
}
export default DashboardTable;