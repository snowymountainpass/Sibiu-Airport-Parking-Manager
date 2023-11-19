// Body.js
import React from 'react';
import { Paper, Container } from '@mui/material';
import './styles.css';

const Body = ({ children }) => (
    <Container>
        <Paper elevation={3} className="body">
            {children}
        </Paper>
    </Container>
);

export default Body;
