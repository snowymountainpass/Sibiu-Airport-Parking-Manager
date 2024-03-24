import React from 'react';


const Body = ({ children, className }) => {
    return (
        <div className={className}>
            {children}
        </div>
    );
};
export default Body;

//ORIGINAL

// <Box
//     component="main"
//     flexGrow={1} // Ensure the main content area fills available space
//     bgcolor="#CDCDCD" //"#cb5a6b"
//     paddingTop={15}
//     paddingBottom={15}
//     paddingLeft={1} // Add padding to the left and right to remove white space
//     paddingRight={1}
//     minHeight="100vh" // Ensure the body takes up the full height of the viewport
//     display="flex"
//     flexDirection="column"
//
// >
//     {children}
// </Box>

//ORIGINAL



// <Container >
//     <Box mt={8} mb={8} width="100%">
//         {children}
//     </Box>
// </Container>

// <Box mt={8} mb={8} width="100%">
//     {children}
// </Box> // looks good

// <Box mt={8} mb={8} bgcolor={'#4dabf5'} width="100%" paddingTop={15}>
//     {children}
// </Box>