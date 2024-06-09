import * as React from 'react';
import Box from '@mui/material/Box';
import {DataGrid} from "@mui/x-data-grid";
import {useState} from "react";
import Button from "@mui/material/Button";
import axios from "axios";

const DashboardEmptyParkingSpacesTable = () => {

    const columns = [
        { field: 'airportName', headerName: 'Airport Name', width: 250  },
        { field: 'parkingSpaceName', headerName: 'Parking Space', width: 200 }
    ];


    const [emptyParkingSpaces,setEmptyParkingSpaces] = useState([]);

    const updateTable = () => {
        axios.get('http://localhost:8080/parkingSpaces/getEmptyParkingSpaces')
            .then(response => {
                setEmptyParkingSpaces(response.data.result);
            }).catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
    }

    return (
        <Box sx={{ height: 400, width: '100%' }}>
            <Button variant="contained" onClick={updateTable}>
                Update
            </Button>
            <DataGrid
                rows={emptyParkingSpaces}
                columns={columns}
                initialState={{
                    pagination: {
                        paginationModel: {
                            pageSize: 5,
                        },
                    },
                }}
                autosizeOptions={{
                    columns: ['airportName', 'parkingSpaceName'],
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
export default DashboardEmptyParkingSpacesTable;
