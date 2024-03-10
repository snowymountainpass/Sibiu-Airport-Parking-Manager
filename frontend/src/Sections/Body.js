import React from 'react';
import { Paper, Container } from '@mui/material';

const Body = ({ children }) => {
    return (
        <Container >
            <Paper elevation={3} >
                {children}
            </Paper>
        </Container>
    );
};
export default Body;
