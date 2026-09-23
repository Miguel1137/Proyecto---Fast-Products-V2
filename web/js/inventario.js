
const productos=document.querySelectorAll(".tarjetaProducto");


productos.forEach(function(tarjetaProducto){
    tarjetaProducto.innerHTML=`
    <div>
        <img src="images/louis.jpg" alt="imagen-Producto" width="200" height="150" style="padding-bottom: 10px;margin-left: 0px;margin-right: 0px;margin-top: 0px; border-radius: 8px;">
    </div>
    <div>
        <span class="datosIndividuales">Nombre producto</span>
    </div>
    <div>
        <span class="datosIndividuales">Talla</span>
    </div>
    <div>
        <span class="datosIndividuales">Costo</span>
    </div>
    `
});