document.getElementById("header").innerHTML=`
        <div style="display: flex; align-items: center; ">
            <a style=" color: rgb(255, 255, 255);" href="inventario.html">
                <img class="logo" src="images/FastProducts.jpeg" alt="Logo FastProducts" width="60" height="60"> 
            </a>
            <h1><a href="inventario.html" >Fast Products</a></h1>
        </div>
           
        <nav>
            <h3 id="navbar-inicio" class="nav-interfaz"><a href="inventario.html" >Inicio</a></h3>
            <h3 id="navbar-alertas" class="nav-interfaz"><a href="alertas.html">Alertas</a></h3>
            <h3 id="navbar-reportes" class="nav-interfaz"><a href="reportes.html" >Reportes</a></h3>
            <h3 id="navbar-cuenta" class="nav-interfaz"><a href="cuenta.html" >Cuenta</a></h3>
            <h3 id="navbar-configuracion" class="nav-interfaz"><a href="configuracion.html" >Configuracion</a></h3>

        </nav>
`;


//el window location... dice que si el link incluye la palabbra puesta
if (window.location.href.includes("inventario")){

    //esta linea llama el elemento con el nombre ... y le agrega la clase especificada en CSS
    document.getElementById("navbar-inicio").classList.add("activo")

} else if (window.location.href.includes("alertas")){
    document.getElementById("navbar-alertas").classList.add("activo")

} else if (window.location.href.includes("reportes")){
    document.getElementById("navbar-reportes").classList.add("activo")

} else if (window.location.href.includes("cuenta")){
    document.getElementById("navbar-cuenta").classList.add("activo")

} else if (window.location.href.includes("configuracion")){
    document.getElementById("navbar-configuracion").classList.add("activo")

}