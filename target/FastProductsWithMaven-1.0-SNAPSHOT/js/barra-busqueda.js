if (window.location.href.includes("inventario")){

    document.getElementById("barraBusqueda").innerHTML=`
        <section id="opcionesBusqueda">
            <!--SELECCION DE VISTA Y TEXTO-->
            <div id="SeleccionVista">

                <!--texto-->
                <div class="vista-txt" style="margin-right: 10px;">
                    <span>Vista: </span>
                </div>

                <!--botones-->
                <div class="distribucion-botones-vista">
                    <button class="BotonesNavegacion">Cuadricula</button>
                    <button class="BotonesNavegacion">Lista</button>
                </div>

            </div>

                <!--BARRA BUSQUEDA-->
            <div class="contenedor-busqueda">

                <input class="casilla-bus-txt"; type="text" placeholder="Buscar artículo">
                <button class="BotonesNavegacion">Buscar</button>

            </div>   
        </section>
`
} else {
   document.getElementById("barraBusqueda").innerHTML=`

        <!--BARRA BUSQUEDA-->
        <section id="opcionesBusqueda">
            <div class="contenedor-busqueda">

                <input class="casilla-bus-txt"; type="text" placeholder="Buscar artículo">
                <button class="BotonesNavegacion">Buscar</button>

            </div>    
        </section> 
    `   
}
