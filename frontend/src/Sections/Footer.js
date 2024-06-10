
import React from 'react';
import {AppBar, Link, Toolbar, Typography} from '@mui/material';

const Footer = () => {
    const currentYear = new Date().getFullYear();
    return (
        <AppBar position="fixed" style={{ top: 'auto', bottom: 0 }}>
            <Toolbar >
                <Typography variant="body2" color="inherit">
                    © {currentYear} Sibiu International Airport
                </Typography>
            </Toolbar>
        </AppBar>
    );
};
export default Footer;
