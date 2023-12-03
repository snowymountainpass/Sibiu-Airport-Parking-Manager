import React from 'react';
import { BrowserRouter as Router,Routes,Route } from 'react-router-dom';

// import * as React from "react";
// import { createRoot } from "react-dom/client";
// import {
//     createBrowserRouter,
//     RouterProvider,
//     Route,
//     Link,
// } from "react-router-dom";


import LandingPage from './Pages/LandingPage';
import PaymentPage from './Pages/PaymentPage';
import ConfirmationPage from './Pages/ConfirmationPage';

// const router = createBrowserRouter([
//
//
//
// ]);

function App() {
  return (
      <Router>
        <div>
          <Routes>
            <Route path="/" exact component={LandingPage} />
            <Route path="/payment" component={PaymentPage} />
            <Route path="/confirmation" component={ConfirmationPage} />
          </Routes>
        </div>
      </Router>
  );
}

export default App;