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
        <section>
            <h2 class="text-center" id="general-percentage-header">Porcentaje general: <span style="color: black;" id="general-percentage-value"></span>%</h2>
            <canvas id="myChart" width="400" height="50"></canvas>
        </section>
        </div>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end py-3">
       <a class="btn btn-primary" type="button" href="addobservation.jsp">Agregar Observacion</a>
       <a onclick="reportfinish()" class="btn btn-primary" type="button">Finalizar Reporte</a>
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
                // Iterar sobre los bienes del usuario y mostrar solo los no reportados en una fila de la tabla
                if (bienesUsuario != null) {
                    for (Object unBien : bienesUsuario) {
                        // Verificar si el estado del bien es no reportado
                        if (unBien.getState().equals("No reportado") || unBien.getState().equals("En espera")) {
                %>
                            <tr data-codigo="<%= unBien.getCode() %>">
                            <td class="campo"><%= unBien.getName() %></td>
                            <td><%= unBien.getPlate() %></td>
                            <td class="campo"><%= unBien.getCode() %></td>
                            <td><%= unBien.getState() %></td>
                            <td>  
                                <div class="acciones">
                                    <a id="cargarImagenes" data-codigo="<%= unBien.getCode() %>">
                                        <img src="resources/img/icons/camera.svg" alt="camera">
                                    </a>&nbsp;
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
<%@ include file="footer.jsp" %>