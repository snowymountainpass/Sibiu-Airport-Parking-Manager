// // styles.js
// import { makeStyles } from '@emotion/react';
// import {blueGrey} from "@mui/material/colors";
//
// export const useStyles = makeStyles((theme) => ({
//     container: {
//         display: 'flex',
//         flexDirection: 'column',
//         minHeight: '100vh',
//     },
//     header: {
//         flex: 1,
//         backgroundColor: theme.palette.primary.main,
//         color: theme.palette.common.white,
//         display: 'flex',
//         alignItems: 'center',
//         justifyContent: 'center',
//         [theme.breakpoints.down('sm')]: {
//             fontSize: '1.5rem',
//         },
//     },
//     body: {
//         flex: 3,
//         backgroundColor: blueGrey, //theme.palette.background.default
//         padding: theme.spacing(2),
//     },
//     footer: {
//         flex: 1,
//         backgroundColor: theme.palette.primary.main,
//         color: theme.palette.common.white,
//         display: 'flex',
//         alignItems: 'center',
//         justifyContent: 'center',
//         [theme.breakpoints.down('sm')]: {
//             fontSize: '1.5rem',
//         },
//         textAlign: 'left',
//     },
//     toolbar: {
//         display: 'flex',
//         justifyContent: 'center',
//     },
//
//     footerLink: {
//         marginLeft: theme.spacing(2),
//         textDecoration: 'none',
//     },
//
//     landingPageContent: {
//         display: 'flex',
//         flexDirection: 'column',
//         alignItems: 'center',
//         justifyContent: 'center',
//         minHeight: '70vh', // TODO: Adjust if needed
//         padding: theme.spacing(2),
//     },
//     paymentPageContent: {
//         display: 'flex',
//         flexDirection: 'column',
//         alignItems: 'center',
//     },
//     confirmationPageContent: {
//         display: 'flex',
//         flexDirection: 'column',
//         alignItems: 'center',
//     },
//
//
// }));