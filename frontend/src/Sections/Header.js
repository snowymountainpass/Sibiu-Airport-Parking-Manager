import React from 'react';
import { AppBar, Toolbar, Typography } from '@mui/material';

import {Link, redirect} from "react-router-dom";

const Header = () => {
    const HandleLogoClick = () => {
        redirect('/');
    }
    return (
        <AppBar position="static" >
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

