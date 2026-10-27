<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Producto - Fast Products</title>
    
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
        <div id="bloqueInformacion">
            <div class="headerLogin">
                <h2>Modificar Producto</h2>
                <h4>ID del producto: ${producto.id}</h4>
            </div>

            <!-- FORMULARIO DE MODIFICACIÓN -->
            <form action="${pageContext.request.contextPath}/modificarProducto" method="POST" class="formulario" enctype="multipart/form-data">
                
                <!-- ID oculto para saber qué producto actualizar -->
                <input type="hidden" name="id" value="${producto.id}">

                <div class="grupoInput">
                    <label for="nombreProducto">Nombre del producto *</label>
                    <input type="text" id="nombreProducto" name="nombreProducto" value="${producto.nombre}" required>
                </div>

                <div class="grupoInput">
                    <label for="descripcion">Descripción</label>
                    <input type="text" id="descripcion" name="descripcion" value="${producto.descripcion}">
                </div>

                <div class="grupoPequeños">
                    <div class="grupoInput">
                        <label for="cantidadDisp">Unidades disponibles *</label>
                        <input type="number" id="cantidadDisp" name="cantidadDisp" value="${producto.cantidadDisp}" required>
                    </div>
                    <div class="grupoInput">
                        <label for="cantidadMin">Unidades de alerta</label>
                        <input type="number" id="cantidadMin" name="cantidadMin" value="${producto.cantidadMin}">
                    </div>
                    <div class="grupoInput">
                        <label for="talla">Talla</label>
                        <input type="text" id="talla" name="talla" value="${producto.talla}">
                    </div>
                </div>

                <div class="grupoInput">
                    <label for="precio">Precio *</label>
                    <input type="number" step="0.01" id="precio" name="precio" value="${producto.precio}" required>
                </div>

                <!-- FOTO ACTUAL Y OPCIÓN DE CAMBIARLA -->
                <div class="grupoInput">
                    <label>Foto actual</label>
                    <c:choose>
                        <c:when test="${not empty producto.foto}">
                            <img src="${pageContext.request.contextPath}/mostrarImagen?foto=${producto.foto}" width="100" style="margin-bottom: 10px; border-radius: 8px;">
                        </c:when>
                        <c:otherwise>
                            <p style="color: #666; margin-bottom: 10px;">Este producto no tiene foto registrada.</p>
                        </c:otherwise>
                    </c:choose>
                    
                    <label for="foto">Subir nueva foto (opcional)</label>
                    <div class="input-archivo-personalizado">
                        <input type="file" id="foto" name="foto" accept="image/*" onchange="mostrarNombreArchivo()">
                        <label for="foto" class="btn btn-primary btn-md">Seleccionar imagen</label>
                        <span id="nombre-archivo">Ningún archivo</span>
                    </div>
                </div>

                <div class="seccionBotones" style="margin-top: 20px;">
                    <button type="submit" class="btn btn-primary btn-lg" style="flex: 1;">Confirmar Modificación</button>
                </div>
            </form>

            <!-- FORMULARIO DE ELIMINACIÓN -->
            <form action="${pageContext.request.contextPath}/eliminarProducto" method="POST" style="margin-top: 15px;" onsubmit="return confirm('¿Estás seguro de que deseas eliminar este producto? Esta acción no se puede deshacer.');">
                <input type="hidden" name="id" value="${producto.id}">
                <input type="hidden" name="foto" value="${producto.foto}">
                <button type="submit" class="btn btn-danger btn-lg" style="width: 100%;">Eliminar Producto</button>
            </form>
        </div>
    </main>

    <footer>
        <hr style="color: #d98e8ecb;">
        <h4><a href="mailto:carvamiguel5@gmail.com">Contáctanos</a></h4>
    </footer>
    
    <script>
        // Función para mostrar el nombre del archivo seleccionado
        function mostrarNombreArchivo() {
            const input = document.getElementById('foto');
            const texto = document.getElementById('nombre-archivo');
            
            if (input.files && input.files.length > 0) {
                texto.textContent = input.files[0].name;
                texto.style.color = '#000';
            } else {
                texto.textContent = 'Ningún archivo';
                texto.style.color = '#555';
            }
        }
    </script>
</body>
</html>