<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nava.jsp" %>
<main>
    <div class="container">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Reportes y Alertas</h2> 
        <div class="form-container">
            <form id="reportForm">
                <table id="agregarbien" class="table">
                    <thead>
                        <tr>
                            <th style="width: 15%;">Item</th>
                            <th style="width: 50%;">Seleccione</th>
                        </tr>
                    </thead> 
                    <tbody>
                        <tr>
                            <td for="tipo" data-label="Item">Tipo:</td>
                            <td data-label="Información">
                                <select id="tipo" name="tipo" class="informacion" style="width: 100%" onchange="cargarDependencias()">
                                    <option value="reportes">Reportes</option>
                                    <option value="alertas">Alertas</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td for="dependencia" data-label="Item">Dependencia:</td>
                            <td data-label="Información">
                                <select id="dependencia" name="dependencia" class="informacion" style="width: 100%; height: 45px;">
                                    <!-- Las opciones de dependencias se cargarán dinámicamente mediante JavaScript -->
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td for="usuario" data-label="Item">Usuario:</td>
                            <td data-label="Información">
                                <select id="usuario" name="usuario" class="informacion" style="width: 100%; height: 45px;">
                                    <option value="no_aplica">No aplica</option>
                                    <!-- Las opciones de usuarios se cargarán dinámicamente mediante JavaScript -->
                                </select>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div class="button-container">
                    <a class="button" type="button" onclick="generarReporte()">Acci&oacute;n</a>
                </div>    
            </form>
        </div>
    </div>
</main>
<%@ include file="footera.jsp" %>

<script>
    function cargarDependencias() {
        var tipo = document.getElementById("tipo").value;
        var dependenciaSelect = document.getElementById("dependencia");

        // Limpiar opciones solo si el tipo es "reportes"
        if (tipo === "reportes") {
            dependenciaSelect.innerHTML = ''; // Limpiar opciones

            // Llamar al servlet para obtener las dependencias
            fetch('ObtenerDependenciasServlet')
                .then(response => response.json())
                .then(data => {
                    data.forEach(dependencia => {
                        var option = document.createElement("option");
                        option.text = dependencia.nombreDependencia;
                        option.value = dependencia.PK_idDependencia;
                        dependenciaSelect.add(option);
                    });
                });
        }
    }

    function cargarUsuarios() {
        var tipo = document.getElementById("tipo").value;
        var usuarioSelect = document.getElementById("usuario");

        // Limpiar opciones solo si el tipo es "reportes"
        if (tipo === "reportes") {
            usuarioSelect.innerHTML = ''; // Limpiar opciones

            // Agregar opción "No aplica"
            var optionNoAplica = document.createElement("option");
            optionNoAplica.text = "No aplica";
            optionNoAplica.value = "no_aplica";
            usuarioSelect.add(optionNoAplica);

            // Llamar al servlet para obtener los usuarios
            fetch('ObtenerUsuariosServlet')
                .then(response => response.json())
                .then(data => {
                    data.forEach(usuario => {
                        var option = document.createElement("option");
                        option.text = usuario.usuario;
                        option.value = usuario.PK_idUsuario;
                        usuarioSelect.add(option);
                    });
                });
        }
    }

    function generarReporte() {
        var tipo = document.getElementById("tipo").value;
        var dependencia = document.getElementById("dependencia").value;
        var usuario = document.getElementById("usuario").value;

        // Verificar si el tipo es "reportes", la dependencia está seleccionada y el usuario es "No aplica"
        if (tipo !== "reportes" || dependencia === "" || usuario !== "no_aplica") {
            alert("Por favor seleccione un tipo 'Reportes', una dependencia y el usuario como 'No aplica' para generar el reporte.");
            return;
        }

        // Llamar al servlet para generar el reporte Excel
        fetch('GenerarReporteExcelServlet' + '?tipo=' + tipo + '&dependencia=' + dependencia + '&usuario=' + usuario)
            .then(response => {
                if (response.ok) {
                    // Iniciar la descarga del archivo Excel
                    response.blob().then(blob => {
                        var url = window.URL.createObjectURL(blob);
                        var a = document.createElement('a');
                        a.href = url;
                        a.download = 'reporte.xls';
                        document.body.appendChild(a);
                        a.click();
                        window.URL.revokeObjectURL(url);

                        // Una vez descargado el Excel, iniciar la descarga del PDF
                        fetch('GenerarReportePDFServlet' + '?tipo=' + tipo + '&dependencia=' + dependencia + '&usuario=' + usuario)
                            .then(response => {
                                if (response.ok) {
                                    response.blob().then(blob => {
                                        var url = window.URL.createObjectURL(blob);
                                        var a = document.createElement('a');
                                        a.href = url;
                                        a.download = 'reporte.pdf';
                                        document.body.appendChild(a);
                                        a.click();
                                        window.URL.revokeObjectURL(url);
                                    });
                                } else {
                                    console.error('Error al generar el reporte PDF');
                                }
                            })
                            .catch(error => console.error('Error al generar el reporte PDF:', error));
                    });
                } else {
                    console.error('Error al generar el reporte Excel');
                }
            })
            .catch(error => console.error('Error al generar el reporte Excel:', error));
    }

    // Llamar a cargarDependencias y usuarios al cargar la página
    window.onload = function() {
        cargarDependencias();
        cargarUsuarios();
    };
</script>
<main>
<%@ include file="footera.jsp" %>
