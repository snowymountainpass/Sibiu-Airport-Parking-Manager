// Footer.js
import React from 'react';
import {AppBar, Link, Toolbar, Typography} from '@mui/material';
// import { useStyles } from '../Styling/styles';

const Footer = () => {
    // const classes = useStyles();

    const currentYear = new Date().getFullYear();

    return (
        <AppBar position="static" >
            {/*className={classes.footer}*/}
            <Toolbar >
                {/*className={classes.toolbar}*/}
                <Typography variant="body2" color="inherit">
                    © {currentYear} Sibiu International Airport
                </Typography>
                <Link href="#" color="inherit" >
                    {/*className={classes.footerLink}*/}
                    Terms and Conditions
                </Link>
                <Link href="#" color="inherit" >
                    {/*className={classes.footerLink}*/}
                    Privacy Policy
                </Link>
            </Toolbar>
        </AppBar>
    );
};

export default Footer;
