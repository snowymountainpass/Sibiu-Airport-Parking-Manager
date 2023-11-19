// styles.js
import { makeStyles } from '@emotion/react';

export const useStyles = makeStyles((theme) => ({
    container: {
        display: 'flex',
        flexDirection: 'column',
        minHeight: '100vh',
    },
    header: {
        flex: 1,
        backgroundColor: theme.palette.primary.main,
        color: theme.palette.common.white,
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        [theme.breakpoints.down('sm')]: {
            fontSize: '1.5rem',
        },
    },
    body: {
        flex: 3,
        backgroundColor: theme.palette.background.default,
        padding: theme.spacing(2),
    },
    footer: {
        flex: 1,
        backgroundColor: theme.palette.primary.main,
        color: theme.palette.common.white,
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        [theme.breakpoints.down('sm')]: {
            fontSize: '1.5rem',
        },
    },
}));
