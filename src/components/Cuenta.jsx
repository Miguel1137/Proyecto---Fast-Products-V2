import { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import './Inventario.css';

function Cuenta() {
  const [usuario, setUsuario] = useState(null);
  const [permisos, setPermisos] = useState([]);
  const [error, setError] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    // 1. Consultamos quién es el usuario logueado en la sesión de Spring Boot
    axios.get('http://localhost:8080/api/usuarios/actual', { withCredentials: true })
      .then(response => {
        setUsuario(response.data);
        // 2. Cargamos el catálogo de permisos
        return axios.get('http://localhost:8080/api/usuarios/permisos');
      })
      .then(response => setPermisos(response.data))
      .catch(error => {
        console.error("Error al cargar cuenta:", error);
        setError(true);
      });
  }, []);

  const cerrarSesion = () => {
    // Redirigimos al endpoint de logout de Spring Boot que destruye la sesión
    window.location.href = 'http://localhost:8080/logout';
  };

  if (error) return <div className="react-app-wrapper"><h2>Debes iniciar sesión para ver esta página.</h2></div>;
  if (!usuario) return <div className="react-app-wrapper"><h2>Cargando cuenta...</h2></div>;

  const tienePermiso = (idPermiso) => {
    return usuario.permisosIds && usuario.permisosIds.includes(idPermiso);
  };

  return (
    <div className="react-app-wrapper">
      <header id="header" className="headerInterno">
        <div style={{ display: 'flex', alignItems: 'center' }}>
          <Link to="/inventario">
            <img className="logo" src="/images/FastProducts.jpeg" alt="Logo" width="60" height="60" />
          </Link>
          <h1 className="italica"><Link to="/inventario"><i>Fast Products</i></Link></h1>
        </div>
        
        <nav>
          <h3 className="nav-interfaz"><Link to="/inventario">Inicio</Link></h3>
          <h3 className="nav-interfaz"><Link to="/alertas">Alertas</Link></h3>
          <h3 className="nav-interfaz"><Link to="/reportes">Reportes</Link></h3>
          <h3 className="nav-interfaz"><Link to="/cuenta">Cuenta</Link></h3>
          <h3 className="nav-interfaz"><Link to="/configuracion">Configuración</Link></h3>
        </nav>
      </header>

      <main>
        <div id="bloqueInformacion" style={{ maxWidth: '500px' }}>
          <div className="headerLogin">
            <h2>Mi Cuenta</h2>
            <h4>Información de tu perfil</h4>
          </div>

          <div className="casillasInformacion">
            <div>
              <span className="etiquetaInformacion">Nombre completo</span>
              <div className="casillaDatos"><span>{usuario.nombreCompleto}</span></div>
            </div>
            <div>
              <span className="etiquetaInformacion">Nombre de usuario</span>
              <div className="casillaDatos"><span>{usuario.nombreUsuario}</span></div>
            </div>
            <div>
              <span className="etiquetaInformacion">Contraseña</span>
              <div className="casillaDatos"><span>*********</span></div>
            </div>
            <div>
              <span className="etiquetaInformacion">Permisos activos</span>
              <div className="casillaPermisos">
                {permisos.map(p => (
                  <div key={p.id} className="permiso">
                    <span>{p.nombrePermiso}</span>
                    <input type="checkbox" checked={tienePermiso(p.id)} readOnly />
                  </div>
                ))}
              </div>
            </div>
          </div>

          <div className="seccionBotones" style={{ marginTop: '30px' }}>
            <button onClick={cerrarSesion} className="btn btn-danger btn-md" style={{ width: '100%' }}>
              Cerrar Sesión
            </button>
          </div>
        </div>
      </main>

      <footer>
        <hr style={{ color: '#d98e8ecb' }} />
        <h4><a href="mailto:carvamiguel5@gmail.com">Contáctanos</a></h4>
      </footer>
    </div>
  );
}

export default Cuenta;