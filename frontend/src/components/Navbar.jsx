import React from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { FaHome, FaCalendarPlus, FaUserCog, FaSignOutAlt } from 'react-icons/fa';
import { useSpring} from '@react-spring/web';

function Navbar() {
  const navigate = useNavigate();
  const token = localStorage.getItem('token');
  const role = localStorage.getItem('role');

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    navigate('/');
  };

  const animationProps = useSpring({
    from: { opacity: 0, transform: 'translateY(-20px)' },
    to: { opacity: 1, transform: 'translateY(0)' },
    config: { tension: 200, friction: 15 },
  });

  return (
    <animated.nav style={animationProps} className="navbar navbar-expand-lg navbar-dark bg-primary spring-container">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/"><FaHome className="icon" /> RDV Médical</Link>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            {token && role === 'PATIENT' && (
              <li className="nav-item">
                <Link className="nav-link" to="/appointment"><FaCalendarPlus className="icon" /> Prendre RDV</Link>
              </li>
            )}
            {token && role === 'ADMIN' && (
              <li className="nav-item">
                <Link className="nav-link" to="/admin"><FaUserCog className="icon" /> Gestion Utilisateurs</Link>
              </li>
            )}
          </ul>
          {token && (
            <button className="btn btn-outline-light" onClick={handleLogout}>
              <FaSignOutAlt className="icon" /> Déconnexion
            </button>
          )}
        </div>
      </div>
    </animated.nav>
  );
}

export default Navbar;