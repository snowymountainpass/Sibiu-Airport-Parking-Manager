// Footer.js
import React from 'react';
import { AppBar, Toolbar, Typography } from '@mui/material';
import { useStyles } from '../Styling/styles';

const Footer = () => {
    const classes = useStyles();

    return (
        <AppBar position="static" className={classes.footer}>
            <Toolbar>
                <Typography variant="h6">
                    Sibiu International Airport
                </Typography>
            </Toolbar>
        </AppBar>
    );
};

export default Footer;
