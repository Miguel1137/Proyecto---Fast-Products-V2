<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${producto.nombre} - Fast Products</title>

    <!-- RUTA CORREGIDA CON CONTEXT PATH -->
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
            <h3 id="navbar-alertas" class="nav-interfaz"><a href="${pageContext.request.contextPath}/alertas">Alertas</a></h3>
            <h3 id="navbar-reportes" class="nav-interfaz"><a href="${pageContext.request.contextPath}/reportes.html">Reportes</a></h3>
            <h3 id="navbar-cuenta" class="nav-interfaz"><a href="${pageContext.request.contextPath}/cuenta.html">Cuenta</a></h3>
            <h3 id="navbar-configuracion" class="nav-interfaz"><a href="${pageContext.request.contextPath}/configuracion.html">Configuración</a></h3>
        </nav>
    </header> 

    <main>
        <div class="contenedor-tarjeta-productos">
            <div class="contenedor-datos-producto">
                
                <div class="imagen-producto">
                    <c:choose>
                        <c:when test="${not empty producto.foto}">
                            <img src="${pageContext.request.contextPath}/mostrarImagen?foto=${producto.foto}" alt="${producto.nombre}">
                        </c:when>
                        <c:otherwise>
                            <img src="${pageContext.request.contextPath}/images/default.jpg" alt="Sin imagen">
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="datos-textuales">
                    <h2>${producto.nombre}</h2> 
                    <span><strong>Descripción:</strong> ${producto.descripcion}</span>
                    <span><strong>Precio:</strong> $${producto.precio}</span>
                    <span><strong>Talla:</strong> ${producto.talla}</span>
                    <span><strong>Cantidad disponible:</strong> ${producto.cantidad}</span> 
                    
                    <!-- BOTÓN PARA IR A MODIFICAR -->
                    <div style="margin-top: 20px;">
                        <a href="${pageContext.request.contextPath}/editarProducto?id=${producto.id}" class="btn btn-primary btn-lg">Modificar Producto</a>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <footer>
        <hr style="color: #d98e8ecb ;">
        <h4><a href="mailto:carvamiguel5@gmail.com">Contáctanos</a></h4>
    </footer>
    
    <script src="${pageContext.request.contextPath}/js/header.js"></script>
</body>
</html>