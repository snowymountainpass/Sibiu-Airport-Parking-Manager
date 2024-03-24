import {Divider, Typography} from "@mui/material";
import React from 'react';

const SectionHeader = ({ children, className }) => { //Typography -- style={{ paddingLeft: 4 }}
    return (
        <>
            <Typography variant="h5"   className={className}>
                {children}
            </Typography>
            {/*<Divider />*/}
        </>
    );
};

export default SectionHeader;

//ORIGINAL
// <>
//     <Typography variant="h6" align="left" gutterBottom style={{ paddingLeft: 4 }}>
//         {children}
//     </Typography>
//     {/*<Divider />*/}
// </>

//ORIGINAL