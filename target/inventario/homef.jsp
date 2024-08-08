<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="headerf.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Object" %>
<div class="container mt-3">
    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item active" aria-current="page">Inicio</li>
        </ol>
    </nav>
</div>
<main class="container">
        <div class="text-center">
            <h1>Inventario personalizado - INVIMA</h1>
            <h2>Bienvenido, <%= username %><br></h2>
        </div>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end py-3">
       <a class="btn btn-primary" type="button" href="addobservation.jsp">Agregar Observacion</a>
        <form action="GenerateReportUser" method="get" id="generateReportForm">
            <input type="hidden" name="action" value="generateReport">
            <button class="btn btn-primary" type="submit">Reporte de bienes</button>
        </form>
    </div>
    <br>
    <div class="table-responsive">
        <table class="w-100 table table-head">
            <thead>
                <tr>
                    <th class="campo" scope="col">Nombre</th>
                    <th scope="col">Placa</th>
                    <th class="campo" scope="col">Codigo</th>
                    <th scope="col">Estado</th>
                    <th scope="col">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%  
                List<Object> bienesUsuario = (List<Object>) request.getSession().getAttribute("bienesUsuario");
                // Iterar sobre los bienes del usuario y mostrar solo los no reportados, en espera y activos en una fila de la tabla
                if (bienesUsuario != null) {
                    for (Object unBien : bienesUsuario) {
                        // Verificar si el estado del bien es no reportado
                        if (unBien.getState().equals("No reportado") && unBien.getCondition().equals("Activo") || unBien.getState().equals("En espera") && unBien.getCondition().equals("Activo")) {
                %>
                            <tr data-codigo="<%= unBien.getCode() %>">
                            <td class="campo"><%= unBien.getName() %></td>
                            <td><%= unBien.getPlate() %></td>
                            <td class="campo"><%= unBien.getCode() %></td>
                            <td><%= unBien.getState() %></td>
                            <td>  
                                <div class="acciones">
                                    <a data-bs-toggle="modal" data-bs-target="#staticBackdrop" data-codigo="<%= unBien.getCode() %>">
                                        <img src="resources/img/icons/camera.svg" alt="camera">
                                    </a>&nbsp;
                                    <!-- Modal -->
                                    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="staticBackdropLabel">Cargar evidencia</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <form id="uploadForm" class="row g-3 needs-validation py-3" action="UploadImages" method="POST" enctype="multipart/form-data">
                                                        <input type="hidden" id="idBien" name="idBien">
                                                        <div class="input-group mb-3">
                                                            <input type="file" class="form-control" id="imagenuno" name="imagenuno">
                                                            <label class="input-group-text" for="imagenuno">Placa</label>
                                                        </div>
                                                        <div class="input-group mb-3">
                                                            <input type="file" class="form-control" id="imagendos" name="imagendos">
                                                            <label class="input-group-text" for="imagendos">Objeto</label>
                                                        </div>
                                                        <div class="input-group mb-3">
                                                            <input type="file" class="form-control" id="imagentres" name="imagentres">
                                                            <label class="input-group-text" for="imagentres">Serial</label>
                                                        </div>
                                                        <br>
                                                        <div class="col-md-4">
                                                            <button class="btn btn-primary" type="submit">Enviar</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="seeobjectf.jsp?codigo=<%= unBien.getCode() %>" data-codigo="<%= unBien.getCode() %>">
                                        <img src="resources/img/icons/airplay.svg" alt="airplay">
                                    </a>&nbsp;
                                    <a onclick="reportar('<%= unBien.getCode() %>')" data-codigo="<%= unBien.getCode() %>">
                                        <img src="resources/img/icons/check-circle.svg" alt="check-circle">
                                    </a>
                                </div>   
                            </td>
                        </tr>
                <% 
                } // Cierre de la condición if
                } // Cierre del bucle for
                } 
                %>
            </tbody>
        </table> 
    </div>
</main>
<script>
    // Esperar a que el DOM esté completamente cargado
    document.addEventListener("DOMContentLoaded", function() {
        var cameraIcons = document.querySelectorAll('a[data-bs-toggle="modal"][data-codigo]');
        
        cameraIcons.forEach(function(icon) {
            icon.addEventListener('click', function() {
                var codigo = this.getAttribute('data-codigo');
                document.getElementById('idBien').value = codigo;
            });
        });
    });
</script>
<%@ include file="footer.jsp" %>