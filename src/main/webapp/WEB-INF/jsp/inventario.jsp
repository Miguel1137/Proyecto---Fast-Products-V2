<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio - Fast Products</title>
    
    <!-- Ruta simple para CSS -->
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
        <section id="barraBusqueda"></section>

        <section id="inventario">
            <c:forEach var="prod" items="${productos}">
                <!-- Ruta simple para ver el producto -->
                <a href="/verProducto?id=${prod.id}">
                    <div class="tarjetaProducto">

                        <div class="contenedorImagen">
                            <c:choose>
                                <c:when test="${not empty prod.foto}">
                                    <!-- Ruta apuntando al Controlador de Imágenes -->
                                    <img src="/imagen?foto=${prod.foto}" alt="${prod.nombre}">
                                </c:when>
                                <c:otherwise>
                                    <img src="/images/default.jpg" alt="Sin imagen">
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
        
        <!-- Botón flotante hacia el Controlador de creación -->
        <a href="/crear-producto" class="btn btn-primary btn-float" title="Crear nuevo producto">+</a>
    </main>

    <footer>
        <hr style="color: #d98e8ecb;">
        <h4><a href="mailto:carvamiguel5@gmail.com">Contáctanos</a></h4>
    </footer>
    
    <!-- Rutas simples para los scripts -->
    <script src="/js/header.js"></script>
    <script src="/js/barra-busqueda.js"></script>
    
</body>
</html>