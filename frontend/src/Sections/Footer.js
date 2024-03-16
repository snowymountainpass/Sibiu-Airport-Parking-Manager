
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
                <Link href="#" color="inherit" >
                    Terms and Conditions
                </Link>
                <Link href="#" color="inherit" >
                    Privacy Policy
                </Link>
            </Toolbar>
        </AppBar>
    );
};
export default Footer;

// <AppBar position="static" >
//     <Toolbar >
//         <Typography variant="body2" color="inherit">
//             © {currentYear} Sibiu International Airport
//         </Typography>
//         <Link href="#" color="inherit" >
//             Terms and Conditions
//         </Link>
//         <Link href="#" color="inherit" >
//             Privacy Policy
//         </Link>
//     </Toolbar>
// </AppBar>
