if (window.location.href.includes("index")){

  

} else if (window.location.href.includes("alertas")){

    const alertas=document.querySelectorAll(".campoEnlistado")

    alertas.forEach(function(campoEnlistado){
        campoEnlistado.innerHTML=`

            <div class="tarjetaEnlistada">

                <div class="seccionDatosPrincipal">
                        <img src="images/louis.jpg" alt="foto de producto" width="110px" height="110px">
                
                    <div class="seccionDatosSecundaria">
                        <span class="nombreTarjetas">Nombre del producto: Camiseta blanca</span>
                        
                        <div class="seccionDatosTerciaria">
                            
                            <div class="datosInternos">
                                <span class="datosIndividuales">ID Alerta: </span>
                                <span class="datosIndividuales">Cantidad Disponible: </span>
                            </div>
                            <div class="datosInternos">
                                <span class="datosIndividuales">ID Producto: </span>
                                <span class="datosIndividuales">Cantidad Minima: </span>  
                            </div>
                        </div>
                    </div>
                </div>
                <button class="boton">Reabastecer</button>
            </div>
        `
    });

} else if (window.location.href.includes("reportes")){
    const reportes=document.querySelectorAll(".campoEnlistado")

    reportes.forEach(function(campoEnlistado){
        campoEnlistado.innerHTML=`

            <div class="tarjetaEnlistada">

                <div class="seccionDatosPrincipal">
                        <img src="images/louis.jpg" alt="foto de producto" width="110px" height="110px">
                
                    <div class="seccionDatosSecundaria">
                        <span class="nombreTarjetas">Nombre del producto: Camiseta blanca</span>
                        
                        <div class="seccionDatosTerciaria">
                            
                            <div class="datosInternos">
                                <span class="datosIndividuales">ID Reporte:</span>
                                <span class="datosIndividuales">Tipo Cambio:</span>
                            </div>
                            <div class="datosInternos">
                                <span class="datosIndividuales">ID Producto:</span>
                                <span class="datosIndividuales">Hora/Fecha</span>  
                            </div>
                        </div>
                    </div>
                </div>
                <button class="boton">Deshacer Reporte</button>
            </div>
        `
    });
}
