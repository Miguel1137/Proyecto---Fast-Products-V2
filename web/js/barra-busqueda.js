if (window.location.href.includes("index")){

    document.getElementById("barraBusqueda").innerHTML=`
        <section id="BarraNavegacion">
            <!--SELECCION DE VISTA Y TEXTO-->
            <div id="SeleccionVista">

                <!--texto-->
                <div class="vista-txt" style="margin-right: 10px;">
                    <span>Vista: </span>
                </div>

                <!--botones-->
                <div>
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
        <section id="BarraNavegacion">
            <div class="contenedor-busqueda">

                <input class="casilla-bus-txt"; type="text" placeholder="Buscar artículo">
                <button class="BotonesNavegacion">Buscar</button>

            </div>    
        </section> 
    `   
}
