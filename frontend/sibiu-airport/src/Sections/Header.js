// Header.js
import React from 'react';
import { AppBar, Toolbar, Typography } from '@mui/material';
import { useStyles } from '../Styling/styles';

const Header = () => {
    const classes = useStyles();

    return (
        <AppBar position="static" className={classes.header}>
            <Toolbar>
                <Typography variant="h6">
                    Sibiu International Airport
                </Typography>
            </Toolbar>
        </AppBar>
    );
};

export default Header;

