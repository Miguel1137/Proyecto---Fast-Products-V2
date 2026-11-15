<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Producto - Fast Products</title>
    
    <!-- Ruta simple para CSS (Spring busca en static) -->
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
        <div id="bloqueInformacion">
            <div class="headerLogin">
                <h2>Crear nuevo Producto</h2>
                <h4>Ingrese la información correspondiente a su producto</h4>
                <h4>Los campos con " * " son obligatorios</h4>
            </div>
            
            <c:if test="${not empty error}">
                <p class="error-Inicio-Sesion" style="color: red; font-weight: bold; text-align: center;">${error}</p>
            </c:if>
                
            <!-- Ruta apuntando al futuro Controlador de Spring -->
            <form action="/registrarProducto" method="POST" class="formulario" enctype="multipart/form-data">
                
                <div class="grupoInput">
                    <label for="foto">Foto del producto</label>
                    <div class="input-archivo-personalizado">
                        <input type="file" id="foto" name="foto" accept="image/*" onchange="mostrarNombreArchivo()">
                        <label for="foto" class="btn btn-primary btn-md">Seleccionar imagen</label>
                        <span id="nombre-archivo">Ningún archivo seleccionado</span>
                    </div>
                </div>
                
                <div class="grupoInput">
                    <label for="nombreProducto">Nombre del producto *</label>
                    <!-- Corregido el id para que coincida con el label -->
                    <input type="text" id="nombreProducto" name="nombreProducto" placeholder="Ingresar nombre" maxlength="40">
                </div>

                <div class="grupoInput">
                    <label for="descripcion">Descripción</label>
                    <input type="text" id="descripcion" name="descripcion" placeholder="Ingresar descripción" maxlength="150">
                </div>

                <div class="grupoPequeños">
                    <div class="grupoInput">
                        <label for="cantidadDisp">Unidades disponibles *</label>
                        <input type="number" id="cantidadDisp" name="cantidadDisp" placeholder="Disp." >
                    </div>

                    <div class="grupoInput">
                        <label for="cantidadMin">Unidades de alerta</label>
                        <input type="number" id="cantidadMin" name="cantidadMin" placeholder="Min.">
                    </div>

                    <div class="grupoInput">
                        <label for="talla">Talla</label>
                        <input type="text" id="talla" name="talla" placeholder="Talla">
                    </div>
                    
                    <div class="grupoInput">
                        <label for="precio">Precio *</label>
                        <input type="number" step="0.01" id="precio" name="precio" placeholder="Precio">
                    </div>
                </div>

                <button type="submit" class="btn btn-primary btn-md">Registrar producto</button>
            </form>
        </div>
    </main>
    
    <script>
        function mostrarNombreArchivo() {
            const input = document.getElementById('foto');
            const texto = document.getElementById('nombre-archivo');

            if (input.files && input.files.length > 0) {
                texto.textContent = input.files[0].name; 
                texto.style.color = '#000'; 
            } else {
                texto.textContent = 'Ningún archivo seleccionado';
                texto.style.color = '#555';
            }
        }
    </script>
</body>
</html>