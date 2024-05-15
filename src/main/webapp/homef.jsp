<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headerf.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Object" %>
<div class="container">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item active" aria-current="page">Inicio</li>
      </ol>
    </nav> 
</div>
<main class="container">
    <div class="text-center">
      <h1>Inventario personalizado - INVIMA</h1>
        <h2>Bines Totales</h1>
    </div>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end py-3">
       <a class="btn btn-primary" type="button" href="addobservation.jsp">Agregar Observacion</a>
       <a onclick="reportefinal()" class="btn btn-primary" type="button">Finalizar Reporte</a>
    </div>
    <br>
    <div class="table-responsive">
        <table class="w-100 table table-head">
            <thead>
                <tr>
                    <th scope="col">Nombre</th>
                    <th scope="col">Placa</th>
                    <th scope="col">Codigo</th>
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
                        if (unBien.getEstado().equals("No reportado")) {
                %>
                            <tr data-codigo="<%= unBien.getCodigo() %>">
                            <td><%= unBien.getNombre() %></td>
                            <td><%= unBien.getPlaca() %></td>
                            <td><%= unBien.getCodigo() %></td>
                            <td>  
                                <div class="acciones">
                                    <a id="cargarImagenes" data-codigo="<%= unBien.getCodigo() %>">
                                        <img src="resources/img/icons/camera.svg" alt="camera">
                                    </a>&nbsp;
                                    <a href="seeobjectf.jsp?codigo=<%= unBien.getCodigo() %>" data-codigo="<%= unBien.getCodigo() %>">
                                        <img src="resources/img/icons/airplay.svg" alt="airplay">
                                    </a>&nbsp;
                                    <a onclick="reportar('<%= unBien.getCodigo() %>')" data-codigo="<%= unBien.getCodigo() %>">
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
<%@ include file="footerf.jsp" %>