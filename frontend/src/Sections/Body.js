import React from 'react';
import {Paper, Container, Box} from '@mui/material';

const Body = ({ children }) => {
    return (
            <Box mt={8} mb={8} width="100%" display="flex" justifyContent="center" alignItems="center">
                {children}
            </Box>
    );
};
export default Body;

// <Container >
//     <Box mt={8} mb={8} width="100%">
//         {children}
//     </Box>
// </Container>

// <Box mt={8} mb={8} width="100%">
//     {children}
// </Box> // looks good