import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { FaUsers, FaFilter } from 'react-icons/fa';
import { useSpring, useTrail} from '@react-spring/web';

function AdminDashboard() {
  const [utilisateurs, setUtilisateurs] = useState([]);
  const [filterRole, setFilterRole] = useState('');

  useEffect(() => {
    const fetchUtilisateurs = async () => {
      try {
        const token = localStorage.getItem('token');
        const response = await axios.get('http://localhost:8080/api/utilisateurs/tous', {
          headers: { Authorization: `Bearer ${token}` },
        });
        setUtilisateurs(response.data);
      } catch (err) {
        console.error('Erreur:', err);
      }
    };
    fetchUtilisateurs();
  }, []);

  const filteredUsers = filterRole
    ? utilisateurs.filter((user) => user.role === filterRole)
    : utilisateurs;

  const handleFilter = (e) => setFilterRole(e.target.value);

  const containerProps = useSpring({
    from: { opacity: 0, transform: 'translateY(20px)' },
    to: { opacity: 1, transform: 'translateY(0)' },
    config: { tension: 220, friction: 20 },
  });

  const trail = useTrail(filteredUsers.length, {
    from: { opacity: 0, transform: 'translateY(10px)' },
    to: { opacity: 1, transform: 'translateY(0)' },
    config: { tension: 220, friction: 20 },
  });

  return (
    <animated.div style={containerProps} className="container mt-4 spring-container">
      <h2><FaUsers className="icon" /> Dashboard Admin</h2>
      <div className="mb-3">
        <label className="form-label"><FaFilter className="icon" /> Filtrer par rôle</label>
        <select
          className="form-select w-100 w-md-25"
          value={filterRole}
          onChange={handleFilter}
        >
          <option value="">Tous les rôles</option>
          <option value="PATIENT">Patient</option>
          <option value="MEDECIN">Médecin</option>
          <option value="ADMIN">Admin</option>
        </select>
      </div>
      <div className="table-responsive">
        <table className="table table-striped table-hover">
          <thead className="table-dark">
            <tr>
              <th>Nom</th>
              <th>Email</th>
              <th>Rôle</th>
            </tr>
          </thead>
          <tbody>
            {trail.map((props, index) => (
              <animated.tr key={filteredUsers[index].id} style={props}>
                <td>{filteredUsers[index].nom}</td>
                <td>{filteredUsers[index].email}</td>
                <td>
                  <span className={`badge ${filteredUsers[index].role === 'ADMIN' ? 'bg-info' : filteredUsers[index].role === 'MEDECIN' ? 'bg-primary' : 'bg-secondary'}`}>
                    {filteredUsers[index].role}
                  </span>
                </td>
              </animated.tr>
            ))}
          </tbody>
        </table>
      </div>
    </animated.div>
  );
}

export default AdminDashboard;