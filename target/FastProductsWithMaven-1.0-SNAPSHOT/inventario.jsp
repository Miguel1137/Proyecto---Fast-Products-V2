<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio - Fast Products</title>
    
    <!-- Ruta corregida para el CSS usando el Context Path -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=PT+Sans+Narrow:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">
</head>
<body>

    <header id="header" class="headerInterno">
        <div style="display: flex; align-items: center;">
            <a href="${pageContext.request.contextPath}/inventario">
                <img class="logo" src="${pageContext.request.contextPath}/images/FastProducts.jpeg" alt="Logo FastProducts" width="60" height="60">
            </a>
            <h1 class="italica"><a href="${pageContext.request.contextPath}/inventario"><i>Fast Products</i></a></h1>
        </div>
        
        <nav>
            <h3 id="navbar-inicio" class="nav-interfaz"><a href="${pageContext.request.contextPath}/inventario">Inicio</a></h3>
            <h3 id="navbar-alertas" class="nav-interfaz"><a href="${pageContext.request.contextPath}/alertas.html">Alertas</a></h3>
            <h3 id="navbar-reportes" class="nav-interfaz"><a href="${pageContext.request.contextPath}/reportes.html">Reportes</a></h3>
            <h3 id="navbar-cuenta" class="nav-interfaz"><a href="${pageContext.request.contextPath}/cuenta.html">Cuenta</a></h3>
            <h3 id="navbar-configuracion" class="nav-interfaz"><a href="${pageContext.request.contextPath}/configuracion.html">Configuración</a></h3>
        </nav>
    </header>        

    <main>
        <section id="barraBusqueda"></section>

        <section id="inventario">
            <c:forEach var="prod" items="${productos}">
                <!-- Se cambió a /verProducto para que pase por el Servlet -->
                <a href="${pageContext.request.contextPath}/verProducto?id=${prod.id}">
                    <div class="tarjetaProducto">

                        <div class="contenedorImagen">
                            <c:choose>
                                <c:when test="${not empty prod.foto}">
                                    <!-- Si hay foto, la carga desde la carpeta images -->
                                    <img src="${pageContext.request.contextPath}/mostrarImagen?foto=${prod.foto}" alt="${prod.nombre}">
                                </c:when>
                                <c:otherwise>
                                    <!-- Si está vacía en la BD, muestra una por defecto -->
                                    <img src="${pageContext.request.contextPath}/images/default.jpg" alt="Sin imagen">
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div class="datosIndividuales">
                            <span>Nombre: ${prod.nombre}</span>
                            <span>Talla: ${prod.talla != null && prod.talla != '' ? prod.talla : 'N/A'}</span>
                            <span>Precio: $${prod.precio}</span>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </section>
        
        <a href="${pageContext.request.contextPath}/crear-producto.html" class="btn btn-primary btn-float" title="Crear nuevo producto">+</a>
    </main>

    <footer>
        <hr style="color: #d98e8ecb;">
        <h4><a href="mailto:carvamiguel5@gmail.com">Contáctanos</a></h4>
    </footer>
    
    <!-- Rutas corregidas para los Scripts -->
    <script src="${pageContext.request.contextPath}/js/header.js"></script>
    <script src="${pageContext.request.contextPath}/js/barra-busqueda.js"></script>
    
</body>
</html>