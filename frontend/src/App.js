import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import LandingPage from './Pages/LandingPage';
import PaymentPage from './Pages/PaymentPage';
import ConfirmationPage from './Pages/ConfirmationPage';
import ReturnPage from "./Pages/ReturnPage";
import DashboardPage from "./Pages/DashboardPage";

function App() {
  return (

      <Router>
          <Routes>
            <Route path="/dashboard" element={<DashboardPage/>} />
            <Route path="/" element={<LandingPage/>}/>
            <Route path="/order/checkout" element={<PaymentPage/>} />
            <Route path="/confirmation" element={<ConfirmationPage/>} />
            <Route path="/return" element={<ReturnPage/>} />
          </Routes>
      </Router>
  );
}

export default App;