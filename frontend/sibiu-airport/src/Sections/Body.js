import React from 'react';
import { Paper, Container } from '@mui/material';
import { useStyles } from '../Styling/styles';

const Body = ({ children }) => {
    const classes = useStyles();

    return (
        <Container className={classes.container}>
            <Paper elevation={3} className={`${classes.body} custom-body`}>
                {children}
            </Paper>
        </Container>
    );
};

export default Body;
