import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { FaUser, FaPlus, FaTimes } from 'react-icons/fa';
import { useSpring, useTrail} from '@react-spring/web';

function PatientDashboard() {
  const [rendezVous, setRendezVous] = useState([]);
  const [filterStatus, setFilterStatus] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const fetchRendezVous = async () => {
      try {
        const token = localStorage.getItem('token');
        const response = await axios.get('http://localhost:8080/api/rendezvous/tous', {
          headers: { Authorization: `Bearer ${token}` },
        });
        setRendezVous(response.data);
      } catch (err) {
        console.error('Erreur:', err);
      }
    };
    fetchRendezVous();
  }, []);

  const filteredRendezVous = filterStatus
    ? rendezVous.filter((rdv) => rdv.statut === filterStatus)
    : rendezVous;

  const handleFilter = (e) => setFilterStatus(e.target.value);

  const handleCancel = async (id) => {
    try {
      const token = localStorage.getItem('token');
      await axios.put(`http://localhost:8080/api/rendezvous/${id}/annuler`, {}, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setRendezVous(rendezVous.map(rdv => rdv.id === id ? { ...rdv, statut: 'ANNULE' } : rdv));
    } catch (err) {
      alert(err.response?.data || 'Erreur lors de l’annulation.');
    }
  };

  const containerProps = useSpring({
    from: { opacity: 0, transform: 'translateY(20px)' },
    to: { opacity: 1, transform: 'translateY(0)' },
    config: { tension: 220, friction: 20 },
  });

  const trail = useTrail(filteredRendezVous.length, {
    from: { opacity: 0, transform: 'translateY(10px)' },
    to: { opacity: 1, transform: 'translateY(0)' },
    config: { tension: 220, friction: 20 },
  });

  return (
    <animated.div style={containerProps} className="container mt-4 spring-container">
      <h2><FaUser className="icon" /> Dashboard Patient</h2>
      <div className="d-flex flex-column flex-md-row justify-content-between mb-3">
        <button
          className="btn btn-success mb-2 mb-md-0"
          onClick={() => navigate('/appointment')}
        >
          <FaPlus className="icon" /> Prendre un rendez-vous
        </button>
        <select
          className="form-select w-100 w-md-25"
          value={filterStatus}
          onChange={handleFilter}
        >
          <option value="">Tous les statuts</option>
          <option value="EN_ATTENTE">En attente</option>
          <option value="CONFIRME">Confirmé</option>
          <option value="ANNULE">Annulé</option>
        </select>
      </div>
      <div className="table-responsive">
        <table className="table table-striped table-hover">
          <thead className="table-dark">
            <tr>
              <th>Date</th>
              <th>Médecin</th>
              <th>Créneau</th>
              <th>Statut</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {trail.map((props, index) => (
              <animated.tr key={filteredRendezVous[index].id} style={props}>
                <td>{filteredRendezVous[index].date}</td>
                <td>{filteredRendezVous[index].medecin.utilisateur.nom}</td>
                <td>{`${filteredRendezVous[index].creneau.heureDebut} - ${filteredRendezVous[index].creneau.heureFin}`}</td>
                <td>
                  <span className={`badge ${filteredRendezVous[index].statut === 'CONFIRME' ? 'bg-success' : filteredRendezVous[index].statut === 'ANNULE' ? 'bg-danger' : 'bg-warning'}`}>
                    {filteredRendezVous[index].statut}
                  </span>
                </td>
                <td>
                  {filteredRendezVous[index].statut !== 'ANNULE' && (
                    <button
                      className="btn btn-danger btn-sm"
                      onClick={() => handleCancel(filteredRendezVous[index].id)}
                    >
                      <FaTimes className="icon" /> Annuler
                    </button>
                  )}
                </td>
              </animated.tr>
            ))}
          </tbody>
        </table>
      </div>
    </animated.div>
  );
}

export default PatientDashboard;