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
        <tbody>
        <td for="tipo" data-label="Item">Tipo:</td>
        <td data-label="Información">
        <select id="tipo" name="tipo" class="informacion"style="width: 100%" onchange="cargarDependencias()">
            <option value="reportes">Reportes</option>
            <option value="alertas">Alertas</option>
        </select>
        </td>
        </tr>
        <td for="dependencia" data-label="Item">Dependencia:</td>
        <td data-label="Información">
        <select id="dependencia" name="dependencia" class="informacion" style="width: 100%; height: 45px;">
            <!-- Las opciones de dependencias se cargarán dinámicamente mediante JavaScript -->
        </select>
        </td>
        </tr>
        <td for="usuario" data-label="Item">Usuario:</td>
        <td data-label="Información">
        <label for="usuario">Usuario:</label>
        <select id="usuario" name="usuario" class="informacion" style="width: 100%; height: 45px;">
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

    function generarReporte() {
    var tipo = document.getElementById("tipo").value;
    var dependencia = document.getElementById("dependencia").value;

    // Verificar URL del servlet y parámetros
    console.log("Tipo:", tipo);
    console.log("Dependencia:", dependencia);

    // Llamar al servlet para generar el reporte
    var url = tipo === "reportes" ? 'GenerarReporteExcelServlet' : 'GenerarReportePDFServlet';
    fetch(url + '?tipo=' + tipo + '&dependencia=' + dependencia)
        .then(response => {
            if (response.ok) {
                // Iniciar la descarga del archivo
                response.blob().then(blob => {
                    var url = window.URL.createObjectURL(blob);
                    var a = document.createElement('a');
                    a.href = url;
                    a.download = 'reporte.' + (tipo === "reportes" ? 'xls' : 'pdf');
                    document.body.appendChild(a);
                    a.click();
                    window.URL.revokeObjectURL(url);
                });
            } else {
                console.error('Error al generar el reporte');
            }
        })
        .catch(error => console.error('Error al generar el reporte:', error));
}
    // Llamar a cargarDependencias al cargar la página
    window.onload = cargarDependencias;
    </script>
<main>
<%@ include file="footera.jsp" %>
