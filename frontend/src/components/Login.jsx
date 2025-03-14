import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { FaEnvelope, FaLock, FaSignInAlt } from 'react-icons/fa';
import { useSpring} from '@react-spring/web';

function Login() {
  const [email, setEmail] = useState('');
  const [motDePasse, setMotDePasse] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/login', { email, motDePasse });
      const { token, role } = response.data;
      localStorage.setItem('token', token);
      localStorage.setItem('role', role);
      if (role === 'PATIENT') navigate('/patient');
      else if (role === 'MEDECIN') navigate('/medecin');
      else if (role === 'ADMIN') navigate('/admin');
    } catch (err) {
      setError(err.response?.data || 'Erreur de connexion. Vérifiez vos identifiants.');
    }
  };

  const animationProps = useSpring({
    from: { opacity: 0, transform: 'scale(0.95)' },
    to: { opacity: 1, transform: 'scale(1)' },
    config: { tension: 280, friction: 60 },
  });

  return (
    <animated.div style={animationProps} className="container mt-5 spring-container">
      <h2><FaSignInAlt className="icon" /> Connexion</h2>
      <form onSubmit={handleSubmit} className="w-50 mx-auto">
        <div className="mb-3">
          <label className="form-label"><FaEnvelope className="icon" /> Email</label>
          <input
            type="email"
            className="form-control"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label"><FaLock className="icon" /> Mot de passe</label>
          <input
            type="password"
            className="form-control"
            value={motDePasse}
            onChange={(e) => setMotDePasse(e.target.value)}
            required
          />
        </div>
        {error && <div className="alert alert-danger">{error}</div>}
        <button type="submit" className="btn btn-primary w-100">
          <FaSignInAlt className="icon" /> Se connecter
        </button>
      </form>
    </animated.div>
  );
}

export default Login;