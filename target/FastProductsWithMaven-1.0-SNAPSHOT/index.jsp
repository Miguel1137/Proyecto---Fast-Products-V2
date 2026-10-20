<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesion - Fast Products</title>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=PT+Sans+Narrow:wght@400;700&display=swap" rel="stylesheet">    
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400..900;1,400..900&display=swap" rel="stylesheet">
</head>
<body>

    <!-- HEADER CORREGIDO -->
    <header class="headerLogin">
        <div style="display: flex; flex-direction: column; align-items: center; justify-content: center; margin-bottom: 20px;">
            <img src="images/FastProducts.jpeg" alt="Logo de FastProducts" width="100" height="100">
            <h1 style="margin-left: 0; margin-top: 10px;">FastProducts</h1>
        </div>
    </header>

    <main>
        <div id="bloqueInformacion">
            <div class="headerLogin">
                <h2>Inicio de Sesion</h2>
                <h4>Ingrese los datos de usuario correspondientes</h4>
            </div>
            
            <form action="LoginServlet" method="POST" class="formulario">
                <div class="grupoInput">
                    <label for="usuario">Usuario</label>
                    <input type="text" id="usuario" name="user" placeholder="Ingrese su usuario">
                </div>

                <div class="grupoInput">
                    <label for="password">Contrasena</label>
                    <input type="password" id="password" name="password" placeholder="••••••••">
                </div>

                <!-- Cambié "botonIngresar" por "botonConfirmar" para que tome tu estilo del CSS -->
                <button type="submit" class="btn btn-primary btn-lg">Ingresar</button>
            </form>
            
            <% if (request.getAttribute("error") != null) { %>
                <p class="error-Inicio-Sesion">
                    <%= request.getAttribute("error") %>
                </p>
            <% } %>
        </div>
    </main>

    <footer>
        <!-- Aquí puedes poner tu footer si lo necesitas -->
    </footer>

</body>
</html>