<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<%@taglib uri="jakarta.tags.functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle del Reporte - Fast Products</title>
    
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
        <div style="max-width: 1000px; margin: 40px auto; background: white; padding: 30px; border-radius: 16px; box-shadow: var(--shadow-hard);">
            
            <!-- CABECERA DEL REPORTE -->
            <div style="text-align: center; margin-bottom: 30px; border-bottom: 2px solid var(--primary-color); padding-bottom: 20px;">
                <h2>Detalle de Cambio: ${reporte.tipoCambio}</h2>
                <p><strong>ID Reporte:</strong> ${reporte.id} | <strong>Fecha:</strong> ${reporte.fechaHora}</p>
                <p><strong>ID Producto:</strong> ${reporte.idProducto} | <strong>ID Usuario:</strong> ${reporte.idUsuario}</p>
            </div>

            <!-- SEPARAMOS LOS DATOS POR COMAS -->
            <c:set var="datosViejos" value="${fn:split(reporte.valorAnterior, ',')}" />
            <c:set var="datosNuevos" value="${fn:split(reporte.valorNuevo, ',')}" />

            <!-- FORMULARIOS COMPARATIVOS -->
            <div style="display: flex; gap: 30px; justify-content: space-between; flex-wrap: wrap;">
                
                <!-- FORMULARIO ANTES -->
                <div style="flex: 1; min-width: 300px; background-color: #f9f9f9; padding: 20px; border-radius: 11px; border: 1px solid var(--border-color);">
                    <h3 style="text-align: center; color: #d32f2f; margin-bottom: 15px;">Antes</h3>
                    
                    <div class="grupoInput">
                        <label>Nombre del producto</label>
                        <input type="text" value="${datosViejos[0]}" readonly style="background-color: #e0e0e0;">
                    </div>
                    <div class="grupoInput">
                        <label>Precio</label>
                        <input type="text" value="${datosViejos[1]}" readonly style="background-color: #e0e0e0;">
                    </div>
                    <!-- Puedes agregar más campos si guardas más datos en la BD -->
                </div>

                <!-- FORMULARIO DESPUÉS -->
                <div style="flex: 1; min-width: 300px; background-color: #f0f8ff; padding: 20px; border-radius: 11px; border: 1px solid #cce5ff;">
                    <h3 style="text-align: center; color: #2e7d32; margin-bottom: 15px;">Después</h3>
                    
                    <div class="grupoInput">
                        <label>Nombre del producto</label>
                        <input type="text" value="${datosNuevos[0]}" readonly style="background-color: #e0e0e0;">
                    </div>
                    <div class="grupoInput">
                        <label>Precio</label>
                        <input type="text" value="${datosNuevos[1]}" readonly style="background-color: #e0e0e0;">
                    </div>
                </div>

            </div>

            <div style="text-align: center; margin-top: 30px;">
                <a href="/reportes" class="btn btn-primary btn-md">Volver a Reportes</a>
            </div>
        </div>
    </main>

    <footer>
        <hr style="color: #d98e8ecb;">
        <h4><a href="mailto:carvamiguel5@gmail.com">Contáctanos</a></h4>
    </footer>
</body>
</html>