<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reportes - Fast Products</title>
    
    <link rel="stylesheet" href="/css/styles.css">
    
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=PT+Sans+Narrow:wght@400;700&display=swap" rel="stylesheet">   
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet"> 
</head>
<body>
    <header id="header" class="headerInterno">
        <div style="display: flex; align-items: center;">
            <a href="/inventario">
                <img class="logo" src="/images/FastProducts.jpeg" alt="Logo FastProducts" width="60" height="60"> 
            </a>
            <h1 class="italica"><a href="/inventario"><i>Fast Products</i></a></h1>
        </div>
        
        <nav>
            <h3 id="navbar-inicio" class="nav-interfaz"><a href="/inventario">Inicio</a></h3>
            <h3 id="navbar-alertas" class="nav-interfaz"><a href="/alertas">Alertas</a></h3>
            <h3 id="navbar-reportes" class="nav-interfaz"><a href="/reportes">Reportes</a></h3>
            <h3 id="navbar-cuenta" class="nav-interfaz"><a href="/cuenta">Cuenta</a></h3>
            <h3 id="navbar-configuracion" class="nav-interfaz"><a href="/configuracion">Configuración</a></h3>
        </nav>
    </header>
    
    <main>
        <section style="margin: 20px auto; max-width: 900px;">
            <h2 style="text-align: center; margin-bottom: 30px;">Historial de Movimientos</h2>

            <c:if test="${empty reportes}">
                <h3 style="text-align: center; color: #555;">No hay movimientos registrados.</h3>
            </c:if>

            <c:forEach var="rep" items="${reportes}">
                <a href="/verReporte?id=${rep.id}" style="text-decoration: none; color: inherit;">
                    <div class="tarjetaEnlistada" style="height: auto; padding: 20px; display: flex; justify-content: space-between; align-items: center; background-color: var(--primary-color);">
                        <div>
                            <span class="nombreTarjetas">Cambio: ${rep.tipoCambio}</span>
                            <div style="margin-top: 5px; font-size: 0.9rem; color: #333;">
                                <strong>Fecha:</strong> ${rep.fechaHora} | <strong>ID Producto:</strong> ${rep.idProducto}
                            </div>
                        </div>
                        <span class="btn btn-primary btn-sm">Ver detalles ></span>
                    </div>
                </a>
            </c:forEach>
        </section>
    </main>

    <footer>
        <hr style="color: #d98e8ecb;">
        <h4><a href="mailto:carvamiguel5@gmail.com">Contáctanos</a></h4>
    </footer>
    
    <script src="/js/header.js"></script>
</body>
</html>