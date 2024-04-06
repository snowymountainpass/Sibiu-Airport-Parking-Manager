import {Divider, Typography} from "@mui/material";
import React from 'react';

const SectionHeader = ({ children, className }) => {
    return (
        <>
            <Typography variant="h5" className={className}>
                {children}
            </Typography>
        </>
    );
};

export default SectionHeader;
