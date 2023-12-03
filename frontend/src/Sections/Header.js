// Header.js
import React from 'react';
import { AppBar, Toolbar, Typography } from '@mui/material';
// import { useStyles } from '../Styling/styles';
import {Link, redirect} from "react-router-dom";

const Header = () => {
    // const classes = useStyles();

    const HandleLogoClick = () => {
        // Redirect to the Landing page
        redirect('/');
    }

    return (
        <AppBar position="static" >
            {/*className={classes.header}*/}
            <Toolbar>
                <Link to="/" style={{ textDecoration: 'none', color: 'inherit' }}>
                    <Typography variant="h6" onClick={HandleLogoClick}>
                        Sibiu International Airport
                    </Typography>
                </Link>
            </Toolbar>
        </AppBar>
    );
};

export default Header;

