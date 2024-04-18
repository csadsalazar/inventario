<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="headerf.jsp"%>
<%@include file="navf.jsp"%>
<%@include file="usuario.jsp"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Bien" %>
<%@ page import="models.Dependencia" %>
<%@ page import="controllers.ListarDependencias" %>
<%@ page import="controllers.ListarBienPorCodigo" %>
<%
// Obtener la lista de dependencias utilizando el controlador
ArrayList<Dependencia> dependencias = ListarDependencias.obtenerDependencias();
%>
    <%
        // Verificar si el parámetro "codigo" está presente y no es nulo
        String codigoParameter = request.getParameter("codigo");
        long codigoBien = 0; // Valor predeterminado en caso de que el parámetro "codigo" esté ausente o sea nulo
        if (codigoParameter != null && !codigoParameter.isEmpty()) {
            // Convertir el parámetro "codigo" a un entero
            codigoBien = Long.parseLong(codigoParameter);
        }
        // Obtener la información del bien utilizando el controlador ListarBienPorCodigo
        Bien bien = ListarBienPorCodigo.obtenerBienPorCodigo(codigoBien);
    %>
<div class="container">
    <h2>Información del bien</h2>
    <br>
    <table id="verbien" class="table">
        <thead>
            <tr>
                <th style="width: 15%;">Item</th>
                <th style="width: 50%;">Información</th>
            </tr>   
        </thead>
        <tbody>
            <tr>
                <td data-label="Item">Código:</td>
                <td data-label="Información"><%= (bien != null) ? bien.getCodigo() : "" %>"</td>
            </tr>
            <tr>
                <td data-label="Item">Placa:</td>
                <td data-label="Información"><%= (bien != null) ? bien.getPlaca() : "" %>"</td>
            </tr>
            <tr>
                <td data-label="Item">Nombre:</td>
                <td data-label="Información"><%= (bien != null) ? bien.getNombre() : "" %>"</td>
            </tr>
            <tr>
                <td data-label="Item">Descripción:</td>
                <td data-label="Información"><%= (bien != null) ? bien.getDescripcion() : "" %>"</td>
            </tr>
            <tr>
                <td data-label="Item">Ubicación:</td>
                <td data-label="Información"><%= (bien != null && bien.getDependencia() != null) ? bien.getDependencia().getnombreDependencia() : "" %></td>
            </tr>
            <tr>
                <td data-label="Item">Imágenes:</td>
                <td data-label="Información">Imagenes que pertenecen al bien</td>
            </tr>
        </tbody>
    </table>
    <form action="ActualizarBienUsuario" method="POST">
    <div class="form-group">
        <label for="information">Observación:</label>
        <textarea id="information" name="information" rows="5" cols="30" required></textarea>
    </div>
    <form>
    <div class="button-container">
        <button class="button">Enviar observación</button>
    </div>
</div>
<%@include file="footerf.jsp"%>