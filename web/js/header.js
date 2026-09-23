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