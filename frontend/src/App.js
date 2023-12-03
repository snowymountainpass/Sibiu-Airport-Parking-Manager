import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import LandingPage from './Pages/LandingPage';
import PaymentPage from './Pages/PaymentPage';
import ConfirmationPage from './Pages/ConfirmationPage';

function App() {
  return (

      <Router>
          <Routes>
            <Route path="/" exact element={<LandingPage/>} />
            <Route path="/payment" element={<PaymentPage/>} />
            <Route path="/confirmation" element={<ConfirmationPage/>} />
          </Routes>
      </Router>
  );
}

export default App;