<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nav.jsp" %>
<div class="container mt-3">
    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="homea.jsp">Inicio</a></li>
            <li class="breadcrumb-item active" aria-current="page">Reportes y alertas</li>
        </ol>
    </nav>
</div>
<main class="container">
    <div class="text-center">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Reportes y alertas</h1>
    </div>
    <form class="row g-3 needs-validation py-4" id="reportForm">
        <div class="col-md-4">
            <label for="tipo" class="form-label">Tipo</label>
            <select class="form-select" id="tipo" onchange="loadDependencies()">
                <option value="reportes">Reportes</option> 
                <option value="alertas">Alertas</option>
            </select>
        </div>
        <div class="col-md-4">
            <label for="dependencia" class="form-label">Dependencia</label>
            <select class="form-select" id="dependencia">
            </select>
        </div>
        <br>
        <div class="col-md-3">
            <button class="btn btn-primary" type="button" onclick="generateReport()">Acciones</button>
        </div>
    </form>
</main>
<script>
    function loadDependencies() {
        var tipo = document.getElementById("tipo").value;
        var dependenciaSelect = document.getElementById("dependencia");

        // Limpiar opciones solo si el tipo es "reportes"
        if (tipo === "reportes") {
            dependenciaSelect.innerHTML = ''; // Limpiar opciones

            // Llamar al servlet para obtener las dependencias
            fetch('GetDependenciesServlet')
                .then(response => response.json())
                .then(data => {
                    data.forEach(dependencia => { 
                        var option = document.createElement("option");
                        option.text = dependencia.dependencyname;
                        option.value = dependencia.PK_idDependency;
                        dependenciaSelect.add(option);
                    });
                });
        }
    }

    function generateReport() {
        var tipo = document.getElementById("tipo").value;
        var dependencia = document.getElementById("dependencia").value;

        // Verificar si el tipo es "reportes" y la dependencia está seleccionada
        if (tipo !== "reportes" || dependencia === "") {
            alert("Por favor seleccione un tipo 'Reportes' y una dependencia para generar el reporte.");
            return;
        }

        // Llamar al servlet para generar el reporte Excel
        fetch('GenerateReportExcelServlet' + '?tipo=' + tipo + '&dependencia=' + dependencia)
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
                    });
                } else {
                    console.error('Error al generar el reporte Excel');
                }
            })
            .catch(error => console.error('Error al generar el reporte Excel:', error));
    }

    // Llamar a cargarDependencias al cargar la página
    window.onload = function() {
        loadDependencies();
    };
</script>
<%@ include file="footer.jsp" %>
