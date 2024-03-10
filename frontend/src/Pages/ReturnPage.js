import React, { useState, useEffect } from "react";
import {Navigate} from "react-router-dom";
import Header from "../Sections/Header";
import Footer from "../Sections/Footer";
import Body from "../Sections/Body";

const ReturnPage = () => {
    const [status, setStatus] = useState(null);
    const [customerEmail, setCustomerEmail] = useState('');

    useEffect(() => {
        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);
        const sessionId = urlParams.get('session_id');

        fetch(`http://localhost:8080/order/session-status?session_id=${sessionId}`)
            .then((res) => res.json())
            .then((data) => {
                setStatus(data.status);
                setCustomerEmail(data.customer_email);
            });
    }, []);

    if (status === 'open') {
        return (
            <Navigate to="'http://localhost:8080/order/checkout"/>
        )
    }

    if (status === 'complete') {
        return (
            <div>
                <Header/>
                    <Body>
                        <section id="success">
                            <p>
                            We appreciate your business! A confirmation email will be sent to {customerEmail}.

                            If you have any issues, please email <a href="mailto:sibiuairport@roairport.ro">sibiuairport@roairport.ro</a>.
                            </p>
                        </section>
                    </Body>
                <Footer />
            </div>
        )
    }

    return null;
}
export default ReturnPage;