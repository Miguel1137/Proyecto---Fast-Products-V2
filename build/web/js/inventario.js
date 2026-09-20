/*document.getElementsByClassName("producto").innerHTML=`
// el div es un contenedor Generico
    <div>
        <img src="louis.jpg" alt="imagen-Producto" width="200" height="150" style="padding-bottom: 10px;margin-left: 0px;margin-right: 0px;margin-top: 0px; border-radius: 8px;">
        <h4 style="margin-bottom:0px;margin-top: 0px; margin-left: 10px; padding-bottom: 10px;">Nombre:</h4>
        <h4 style="margin-bottom:0px;margin-top: 0px; margin-left: 10px; padding-bottom: 10px;";>Talla:</h4>
        <h4 style="margin-bottom:0px;margin-top: 0px; margin-left: 10px; padding-bottom: 10px;";>Costo:</h4>
    </div>
`*/

const productos=document.querySelectorAll(".productoCuadricula");


productos.forEach(function(productoCuadricula){
    productoCuadricula.innerHTML=`
    <div>
        <img src="images/louis.jpg" alt="imagen-Producto" width="200" height="150" style="padding-bottom: 10px;margin-left: 0px;margin-right: 0px;margin-top: 0px; border-radius: 8px;">
        <h4 style="margin-bottom:0px;margin-top: 0px; margin-left: 10px; padding-bottom: 10px;">Nombre:</h4>
        <h4 style="margin-bottom:0px;margin-top: 0px; margin-left: 10px; padding-bottom: 10px;";>Talla:</h4>
        <h4 style="margin-bottom:0px;margin-top: 0px; margin-left: 10px; padding-bottom: 10px;";>Costo:</h4>
    </div>
    `
});