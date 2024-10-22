import './components/LoginNew.css';
import './App.css';
import { ToastContainer } from 'react-toastify';
import FooterComponent from './components/FooterComponent';
import HeaderComponent from './components/HeaderComponent';
import Login from './components/LoginNew';
import Forgot from './components/ForgotPass';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import PatientInduction from './components/PatientInduction';
import PatientUpdate from './components/PatientUpdate';
import ProtectRoutes from './components/ProtectedRoutes';
import Menu from './components/Menu';
import ProcessPatientData from './components/ProcessPatientData';
import ProtectedRoutes from './components/ProtectedRoutes';
import { LoginContext } from './components/LoginContext';
import { useState } from 'react';

const App = () => {

const [loggedIn, setLoggedIn] = useState(false)

  return (
    <LoginContext.Provider value={{loggedIn, setLoggedIn}}>
    <div>
      <div>
        <HeaderComponent />
      </div>
      <div>
        <ToastContainer
          autoClose={5000}
          hideProgressBar={false}
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
        />
        {/* <BrowserRouter> 
         <Routes>
            <Route exact path="/" element={<Login />} />
              <Route path="/forgotpass" element={<Forgot />} />
              <Route path="/menu" element={<Menu />} />
              <Route path="/menu/patient-induction" element={<PatientInduction />} />
              <Route path="/menu/patient-update" element={<PatientUpdate />} />
              <Route path="/menu/process-patient-data" element={<ProcessPatientData />} />
          </Routes> 
         </BrowserRouter> */}


        <BrowserRouter>
          <Routes>
            <Route exact path="/" element={<Login />} />
            <Route path="/forgotpass" element={<Forgot />} />
            {/* below are protected routes */}
            <Route path="/" element={<ProtectRoutes />} >
              <Route path="/menu" element={<Menu />} />
              <Route path="/menu/patient-induction" element={<PatientInduction />} />
              <Route path="/menu/patient-update" element={<PatientUpdate />} />
              <Route path="/menu/process-patient-data" element={<ProcessPatientData />} />
            </Route>
          </Routes>
        </BrowserRouter>
      </div>
      <div>
        <FooterComponent />
      </div>
    </div>
  </LoginContext.Provider>
  );
};

export default App;
