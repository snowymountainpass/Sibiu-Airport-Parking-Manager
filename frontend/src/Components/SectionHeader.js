import {Divider, Typography} from "@mui/material";
import React from 'react';

const SectionHeader = ({ children }) => {
    return (
        <>
            <Typography variant="h6" align="left" gutterBottom style={{ paddingLeft: 4 }}>
                {children}
            </Typography>
            <Divider />
        </>
    );
};

export default SectionHeader;