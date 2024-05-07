<%@ page contentType="text/html; charset=utf-8" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Administrador-Invima</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/x-icon" href="resources/img/checkinvima.png">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="resources/css/estilos.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
  </head>
  <body>
    <header>
    <nav class="navbar navbar-expand-lg navbar-light bg-primary">
      <div class="container">
        <img src="resources/img/logo blanco gov.co.png" alt="img gov.co" class="py-2" height="45px">
      </div>
    </nav>
    <nav class="w-100 d-flex justify-content-around align-items-center py-2">
        <img src="resources/img/Logo-potencia-de-la-vida 1.png" alt="Logo-potencia-de-la-vida" height="35">
        <div class="input-wrapper d-flex align-items-center">        
        </div>            
        <img src="resources/img/Logo_Invima-Te-Acompana_0 1.png" alt="Logo_Invima-Te-Acompana" height="35">
    </nav>
    <nav class="navbar navbar-expand-lg navbar-light bg" id="options">
      <div class="container">
        <img src="resources/img/invima_blanco.png" alt="Logo_Invima-Te-Acompana" height="45" >
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav ms-auto">
  <li class="nav-item">
              <a class="nav-link" href="#">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Gestion Bienes</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Reportes y Alertas</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Observaciones</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Administradores</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    </header>
    <main class="container">
      <div class="text-center py-5">
      <h1>Inventario personalizado - INVIMA</h1>
        <div class="text-center py-5">
        <h2>Reportes y Alertas</h2>  
        </div>
        <form class="row g-3 needs-validation" id="reportForm">
          <div class="col-md-4">
            <label for="tipo" class="form-label">Tipo</label>
            <select class="form-select" id="tipo" onchange="cargarDependencias()">
              <option value="reportes">Reportes</option>
              <option value="alertas">Alertas</option>
            </select>
          </div>
          <div class="col-md-4">
            <label for="dependencia" class="form-label">Dependencia</label>
            <select class="form-select" id="dependencia">
            </select>
          </div>
          <div class="col-md-4">
            <label for="usuario" class="form-label">Usuario</label>
            <select class="form-select" id="usuario">
            </select>
          </div>
          <div>
          </div>
          <div class="col-md-3">
            <button class="btn btn-primary" type="button" onclick="generarReporte()">Acciones</button>
          </div>
        </form>

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
 <footer>
    <div class="mt-5 pt-5 pb-5 footer">
      <div class="container">
        <div class="row">
          <div class="col-lg-1"></div>
          <div class="col-lg-5 col-xs-12 about-company">
            <img src="resources/img/Logo_Invima-Te-Acompana_Blanco 1.png" alt="" style="margin-block-end: 20px;">
            <p class="text-white">Sede principal: Carrera 10 #64 - 28 Bogotá, Colombia</p>
            <p class="text-white">Teléfono conmutador: (+57)(601) 7422121 </p>
            <p class="text-white">Contáctenos PQRSD</p>
            <p class="text-white">Horario de atención: Lunes a Viernes 7:30 a.m. a 3:30 p.m.</p>
            <p class="text-white">Línea anticorrupción: (+57)(601) 7458593</p>
            <p class="text-white">Notificaciones Judiciales: notificaciones_judiciales@invima.gov.co</p>
            <p class="text-white">Transparencia Invima: soytransparente@invima.gov.co</p>
            <p><a href="#"><i class="fa fa-facebook-square mr-1"></i></a><a href="#"><i class="fa fa-linkedin-square"></i></a></p>
          </div>
          <div class="col-lg-3 col-xs-12 links">
            <h4 class="mt-lg-0 mt-sm-3">Enlaces de interes</h4>
              <ul class="m-0 p-0">
                <li>- <a href="#">Enlaces instituto</a></li>
                <li>- <a href="#">Qué hacemos</a></li>
                <li>- <a href="#">Atención al ciudadano</a></li>
                <li>- <a href="#">Tramites y servicios</a></li>
              </ul>
          </div>
          <div class="col-lg-3 col-xs-12 links">
              <ul class="m-0 p-0">
                <li>> <a href="#">Normatividad</a></li>
                <li>> <a href="#">Paticipa</a></li>
                <li>><a href="#">Sala de prensa</a></li>
                <li>> <a href="#">Sed faucibus</a></li>
                <li>> <a href="#">Transparencia</a></li>
              </ul>
          </div>
        </div>
        <div class="row mt-5">
          <div class="col copyright">
            <p class=""></p>
            <nav class="d-flex justify-content-around align-items-center">
              <img src="resources/img/invima_blanco.png" alt="Logo-potencia-de-la-vida" height="35">
              <div class="input-wrapper d-flex align-items-center">        
              </div>            
              <img src="resources/img/logo-gov-co-blanco.png" alt="Logo_Invima-Te-Acompana" height="35">
          </nav>
          </div>
        </div>
      </div>
      </div>
      </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    <script src="resources/js/administrador.js"></script>
    <script src="resources/js/librerias.js"></script> 
  </body>
</html>