import React from 'react';
import { Button, Typography } from '@mui/material';
import { PDFViewer, Document, Page, Text, View, StyleSheet } from '@react-pdf/renderer';


const styles = StyleSheet.create({
    page: {
        flexDirection: 'row',
        backgroundColor: '#fff',
        padding: 10,
    },
    section: {
        margin: 10,
        padding: 10,
        flexGrow: 1,
    },
});

const ConfirmationPageBody = ({ paymentDetails }) => {
    const generatePDF = () => {
        // Generate PDF with payment details

        const carLicenseNumber = localStorage.getItem('carLicenseNumber');
        // Create a blob with PDF content
        const pdfBlob = (
            <Document>
                <Page size="A4" style={styles.page}>
                    <View style={styles.section}>
                        <Text>Payment Details</Text>
                        <Text>{`Car License Plate: ${carLicenseNumber}`}</Text>
                        <Text>{`Amount: ${paymentDetails.amount}`}</Text>
                        <Text>{`Transaction ID: ${paymentDetails.transactionId}`}</Text>

                        {/* Add more payment details as needed */}
                    </View>
                </Page>
            </Document>
        ).toBlob();

        // Create a download link and trigger the download
        const downloadLink = document.createElement('a');
        downloadLink.href = URL.createObjectURL(pdfBlob);
        downloadLink.download = 'PaymentReceipt.pdf';
        downloadLink.click();

        // Example: generate PDF with payment details
        alert('Generating PDF with payment details');
    };

    return (
        <div style={{ textAlign: 'center' }}>
            <Typography variant="h4" gutterBottom>
                Your payment has been successfully processed!
            </Typography>
            <Typography variant="body1" gutterBottom>
                We thank you for using Sibiu International Airport and we hope to see you soon!
            </Typography>
            <Typography variant="body1" gutterBottom>
                In order to obtain the receipt, click on the button marked "Receipt".
            </Typography>
            <Button variant="contained" color="primary" onClick={generatePDF} style={{ marginTop: '20px' }}>
                Receipt
            </Button>
        </div>
    );
};

export default ConfirmationPageBody;