import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import LandingPage from '././Pages/LandingPage';
import PaymentPage from '././Pages/PaymentPage';
import ConfirmationPage from '././Pages/ConfirmationPage';

function App() {
  return (
      <Router>
        <div>
          <Switch>
            <Route path="/" exact component={LandingPage} />
            <Route path="/payment" component={PaymentPage} />
            <Route path="/confirmation" component={ConfirmationPage} />
          </Switch>
        </div>
      </Router>
  );
}

export default App;