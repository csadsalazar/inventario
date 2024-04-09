<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nava.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Observacion" %>
<%@ page import="controllers.ListarObservaciones" %>


<main>
    <div class="container">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Observaciones</h2>
        <table class="table">
            <thead>
                <tr>
                    <th style="width: 10%;">Funcionario</th>
                    <th style="width: 15%;">Dependencia</th>
                    <th style="width: 20%;">Asunto</th>
                    <th style="width: 25%;">Observación</th>
                </tr>
            </thead>
            <tbody>
                <%
                ArrayList<Observacion> observaciones = ListarObservaciones.obtenerObservaciones();
                for (Observacion observacion : observaciones) {
                %>
                            <tr>
                                <td data-label="Funcionario"><%= observacion.getUsuario().getUsuario() %></td>
                                <td data-label="Dependencia"><%= observacion.getUsuario().getDependencia() %></td>
                                <td data-label="Asunto"><%= observacion.getAsunto() %></td>
                                <td data-label="Observación"><%= observacion.getInformacion() %></td>
                            </tr>
                <%
                }
                %>
            </tbody>
        </table>
    </div>
</main>
<%@ include file="footera.jsp" %>
