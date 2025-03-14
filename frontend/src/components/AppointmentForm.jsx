import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { parseJwt } from '../utils/jwt';
import { FaUserMd, FaClock, FaCalendarAlt, FaCheck, FaArrowLeft } from 'react-icons/fa';
import { useSpring} from '@react-spring/web';

function AppointmentForm() {
  const [medecins, setMedecins] = useState([]);
  const [creneaux, setCreneaux] = useState([]);
  const [selectedMedecin, setSelectedMedecin] = useState('');
  const [selectedCreneau, setSelectedCreneau] = useState('');
  const [date, setDate] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const fetchMedecins = async () => {
      const token = localStorage.getItem('token');
      const response = await axios.get('http://localhost:8080/api/medecins/tous', {
        headers: { Authorization: `Bearer ${token}` },
      });
      setMedecins(response.data);
    };
    fetchMedecins();
  }, []);

  useEffect(() => {
    if (selectedMedecin) {
      const fetchCreneaux = async () => {
        const token = localStorage.getItem('token');
        const response = await axios.get(`http://localhost:8080/api/creneaux/medecin/${selectedMedecin}`, {
          headers: { Authorization: `Bearer ${token}` },
        });
        setCreneaux(response.data);
      };
      fetchCreneaux();
    }
  }, [selectedMedecin]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const token = localStorage.getItem('token');
      const decodedToken = parseJwt(token);
      const patientId = decodedToken.userId;
      const rendezVous = {
        date,
        statut: 'EN_ATTENTE',
        patient: { id: patientId },
        medecin: { id: selectedMedecin },
        creneau: { id: selectedCreneau },
      };
      await axios.post('http://localhost:8080/api/rendezvous/ajouter', rendezVous, {
        headers: { Authorization: `Bearer ${token}` },
      });
      navigate('/patient');
    } catch (err) {
      setError(err.response?.data || 'Erreur lors de la prise de rendez-vous.');
    }
  };

  const animationProps = useSpring({
    from: { opacity: 0, transform: 'translateX(-20px)' },
    to: { opacity: 1, transform: 'translateX(0)' },
    config: { tension: 200, friction: 15 },
  });

  return (
    <animated.div style={animationProps} className="container mt-4 spring-container">
      <h2><FaCalendarAlt className="icon" /> Prendre un rendez-vous</h2>
      <form onSubmit={handleSubmit} className="w-100 w-md-50 mx-auto">
        <div className="mb-3">
          <label className="form-label"><FaUserMd className="icon" /> Médecin</label>
          <select
            className="form-select"
            value={selectedMedecin}
            onChange={(e) => setSelectedMedecin(e.target.value)}
            required
          >
            <option value="">Sélectionnez un médecin</option>
            {medecins.map((medecin) => (
              <option key={medecin.id} value={medecin.id}>
                {medecin.utilisateur.nom} - {medecin.specialite}
              </option>
            ))}
          </select>
        </div>
        <div className="mb-3">
          <label className="form-label"><FaClock className="icon" /> Créneau</label>
          <select
            className="form-select"
            value={selectedCreneau}
            onChange={(e) => setSelectedCreneau(e.target.value)}
            required
          >
            <option value="">Sélectionnez un créneau</option>
            {creneaux.map((creneau) => (
              <option key={creneau.id} value={creneau.id}>
                {creneau.jour} {creneau.heureDebut} - {creneau.heureFin}
              </option>
            ))}
          </select>
        </div>
        <div className="mb-3">
          <label className="form-label"><FaCalendarAlt className="icon" /> Date</label>
          <input
            type="date"
            className="form-control"
            value={date}
            onChange={(e) => setDate(e.target.value)}
            required
          />
        </div>
        {error && <div className="alert alert-danger">{error}</div>}
        <div className="d-flex flex-column flex-md-row justify-content-between">
          <button type="submit" className="btn btn-primary mb-2 mb-md-0">
            <FaCheck className="icon" /> Confirmer
          </button>
          <button
            type="button"
            className="btn btn-secondary"
            onClick={() => navigate('/patient')}
          >
            <FaArrowLeft className="icon" /> Retour
          </button>
        </div>
      </form>
    </animated.div>
  );
}

export default AppointmentForm;