document.getElementById("bloqueInformacion").innerHTML=`
    <div class="casillasInformacion">
        <div>
            <span class="etiquetaInformacion">Nombre completo</span>
            <div class="casillaDatos">
                <span>Miguel Angel Carvajal</span>
            </div>
        </div>

        <div>
            <span class="etiquetaInformacion">Nombre de usuario</span>
            <div class="casillaDatos">
                <span>Miguel Angel Carvajal</span>
            </div>
        </div>
        <div>
            <span class="etiquetaInformacion">Contraseña</span>
            <div class="casillaDatos">
                <span>*********</span>
            </div>
        </div>
        <div>
            <span class="etiquetaInformacion">Permisos</span>
            <div class="casillaPermisos">
                <div class="permiso">
                    <span>Registrar Productos</span>
                    <input type="checkbox" checked>
                </div>
                <div class="permiso">
                    <span>Modificar Productos</span>
                    <input type="checkbox" checked>
                </div>
                <div class="permiso">
                    <span>Retirar productos</span>
                    <input type="checkbox" checked>
                </div>
                <div class="permiso">
                    <span>Visualizar Reportes</span>
                    <input type="checkbox" checked>
                </div>
                <div class="permiso">
                    <span>Visualizar Alertas</span>
                    <input type="checkbox" checked>
                </div>
            </div>
        </div>
    </div>
    <div class="seccionBotones">
        <a href="<%= request.getContextPath() %>/LogOutServlet" class="Botones-Principales" style="background-color: #F45D5D;">Cerrar sesión</a>
        <a href="" class="Botones-Principales" style="background-color: #D9C6C6;">Modificar</a>
    </div>
            `