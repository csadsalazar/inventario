<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="headerf.jsp" %>
<%@ include file="navf.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Object" %>
<table class="table">
    <h2>Bienes del usuario</h2>
    <br>
    <thead>
        <tr>
            <th style="width: 15%;">Nombre</th> 
            <th style="width: 15%;">Placa</th>
            <th style="width: 15%;">Codigo</th> 
            <th style="width: 25%;">Acciones</th> 
        </tr>
    </thead>
    <tbody>
        <% 
        List<Object> bienesUsuario = (List<Object>) request.getSession().getAttribute("bienesUsuario");
        // Iterar sobre los bienes del usuario y mostrar cada uno en una fila de la tabla
        if (bienesUsuario != null) {
            for (Object unBien : bienesUsuario) {
                %>
                <tr>
                    <td data-label="Nombre"><%= unBien.getNombre() %></td>
                    <td data-label="Placa"><%= unBien.getPlaca() %></td>
                    <td data-label="Codigo"><%= unBien.getCodigo() %></td>
                    <td data-label="Acciones">
                        <div class="acciones">
                        <a id="cargarImagenes" data-codigo="<%= unBien.getCodigo() %>" <%= unBien.getEstado().equals("Reportado") ? "disabled" : "" %>>
                            <img src="resources/img/camera.svg" alt="camera">
                        </a>&nbsp;
                        <a href="verbienf.jsp?codigo=<%= unBien.getCodigo() %>" data-codigo="<%= unBien.getCodigo() %>" <%= unBien.getEstado().equals("Reportado") ? "disabled" : "" %>>
                            <img src="resources/img/airplay.svg" alt="airplay">
                        </a>&nbsp;
                        <a onclick="reportar('<%= unBien.getCodigo() %>')" data-codigo="<%= unBien.getCodigo() %>" <%= unBien.getEstado().equals("Reportado") ? "disabled" : "" %>>
                            <img src="resources/img/check-circle.svg" alt="check-circle">
                        </a>
                        </div>   
                    </td>
                </tr>
                <% 
            }
        } 
        %>
    </tbody>
</table>
<div class="button-container">
    <a href="agregarobservacion.jsp" class="button">Observaciones</a>
    <a onclick="reportefinal()" class="button">Finalizar reporte</a>
</div>
</div>
</main>
<%@ include file="footerf.jsp" %>