// import React, {useState} from 'react';
// import { CardElement, useStripe, useElements } from '@stripe/react-stripe-js';
// import {useNavigate} from "react-router-dom";
//
// const PaymentForm = () => {
//     const stripe = useStripe();
//     const elements = useElements();
//     const history = useNavigate();
//
//
//     const [amount, setAmount] = useState(0);
//
//     const handleAmountChange = (event) => {
//         setAmount(parseFloat(event.target.value));
//     };
//
//     const handleSubmit = async (event) => {
//         event.preventDefault();
//
//         if (!stripe || !elements) {
//             return;
//         }
//
//         const { token, error } = await stripe.createToken(elements.getElement(CardElement), { amount });
//
//         if (error) {
//             console.error(error);
//         } else {
//             console.log('Token:', token);
//             // Assuming your payment processing logic returns a success status
//             const paymentSuccess = true; // Replace with your actual success check
//
//             if (paymentSuccess) {
//                 // Redirect to the Confirmation Page
//                 history('/confirmation');
//             } else {
//                 console.log('Payment failed');
//                 // Handle payment failure if needed
//             }
//         }
//     };
//
//     return (
//         <form onSubmit={handleSubmit}>
//             <label>
//                 Amount (in cents):
//                 <input type="number" value={amount} onChange={handleAmountChange} />
//             </label>
//             <CardElement />
//             <button type="submit" disabled={!stripe}>
//                 Pay
//             </button>
//         </form>
//     );
// };
//
// export default PaymentForm;