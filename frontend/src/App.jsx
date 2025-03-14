import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import PatientDashboard from './components/PatientDashboard';
import MedecinDashboard from './components/MedecinDashboard';
import AdminDashboard from './components/AdminDashboard';
import AppointmentForm from './components/AppointmentForm';
import Navbar from './components/Navbar';

function App() {
  return (
    <>Bonjour
      <Navbar />
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/patient" element={<PatientDashboard />} />
        <Route path="/medecin" element={<MedecinDashboard />} />
        <Route path="/admin" element={<AdminDashboard />} />
        <Route path="/appointment" element={<AppointmentForm />} />
      </Routes>
    </>
  );
}

export default App;