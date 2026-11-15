<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alertas - Fast Products</title>
    
    <!-- Ruta simple para CSS (Spring busca en static) -->
    <link rel="stylesheet" href="/css/styles.css">
    
    <link href="https://fonts.googleapis.com/css2?family=PT+Sans+Narrow:wght@400;700&display=swap" rel="stylesheet">  
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">  
</head>
<body>
    <header id="header" class="headerInterno">
        <div style="display: flex; align-items: center;">
            <a href="/inventario">
                <img class="logo" src="/images/FastProducts.jpeg" alt="Logo" width="60" height="60"> 
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
        <section id="barraBusqueda"></section>

        <section style="margin: 20px 150px;">
            <c:if test="${empty alertas}">
                <h2 style="text-align: center; margin-top: 50px; color: #555;">¡Todo en orden! No hay productos con stock bajo.</h2>
            </c:if>

            <c:forEach var="alerta" items="${alertas}">
                <div class="tarjetaEnlistada">
                    <div class="seccionDatosPrincipal">
                        <c:choose>
                            <c:when test="${not empty alerta.foto}">
                                <img src="/imagen?foto=${alerta.foto}" alt="${alerta.nombre}" width="110" height="110">
                            </c:when>
                            <c:otherwise>
                                <img src="/images/default.jpg" alt="Sin imagen" width="110" height="110">
                            </c:otherwise>
                        </c:choose>

                        <div class="seccionDatosSecundaria">
                            <span class="nombreTarjetas">Producto: ${alerta.nombre}</span>

                            <div class="seccionDatosTerciaria">
                                <div class="datosInternos">
                                    <span class="datosIndividuales">ID Producto: ${alerta.id}</span>
                                    <!-- AQUÍ EL CAMBIO: usar cantidadDisp -->
                                    <span class="datosIndividuales" style="color: #d32f2f; font-weight: bold;">Stock Actual: ${alerta.cantidadDisp}</span>
                                </div>
                                <div class="datosInternos">
                                    <!-- AQUÍ EL CAMBIO: usar cantidadMin -->
                                    <span class="datosIndividuales">Alerta Mínima: ${alerta.cantidadMin}</span>
                                    <span class="datosIndividuales">Estado: <b>Reabastecer</b></span>  
                                </div>
                            </div>
                        </div>
                    </div>
                    <a href="/editarProducto?id=${alerta.id}" class="btn btn-primary btn-md">Reabastecer</a>
                </div>
            </c:forEach>
        </section>
    </main>
     
    <footer>
        <hr>
        <h4><a href="mailto:carvamiguel5@gmail.com">Contáctanos</a></h4>
    </footer>
    
    <!-- Rutas simples para los archivos JS -->
    <script src="/js/header.js"></script>
    <script src="/js/barra-busqueda.js"></script>
</body>
</html>