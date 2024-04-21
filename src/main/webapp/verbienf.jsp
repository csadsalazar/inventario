<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="headerf.jsp" %>
<%@ include file="navf.jsp" %>
<%@ include file="usuario.jsp" %>
<%@ page import="models.Bien" %>
<%@ page import="controllers.ListarBienPorCodigo" %>
<%
    // Recuperar el parámetro "codigo" de la URL
    long codigoBien = Long.parseLong(request.getParameter("codigo"));
    // Obtener la información del bien utilizando el controlador ListarBienPorCodigo
    Bien bien = ListarBienPorCodigo.obtenerBienPorCodigo(codigoBien);
%>
<div class="container">
    <h2>Información del bien <%= (bien != null) ? bien.getPlaca() : "" %></h2>
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
                <td data-label="Información"><%= (bien != null) ? bien.getCodigo() : "" %></td>
            </tr>
            <tr>
                <td data-label="Item">Placa:</td>
                <td data-label="Información"><%= (bien != null) ? bien.getPlaca() : "" %></td>
            </tr>
            <tr>
                <td data-label="Item">Nombre:</td>
                <td data-label="Información"><%= (bien != null) ? bien.getNombre() : "" %></td>
            </tr>
            <tr>
                <td data-label="Item">Descripción:</td>
                <td data-label="Información"><%= (bien != null) ? bien.getDescripcion() : "" %></td>
            </tr>
            <tr>
                <td data-label="Item">Ubicación:</td>
                <td data-label="Información"><%= (bien != null && bien.getDependencia() != null) ? bien.getDependencia().getnombreDependencia() : "" %></td>
            </tr>
            <tr>
                <td data-label="Item">Imágenes:</td>
                <td data-label="Información">Imágenes que pertenecen al bien</td>
            </tr>
        </tbody>
    </table>
    <form action="Observacion" method="POST"> <!-- Utiliza el servlet Observacion para procesar la observación -->
        <input type="hidden" name="codigoBien" value="<%= bien.getCodigo() %>"> <!-- Enviar el código del bien como parámetro oculto -->
        <input type="hidden" name="idUsuario" value="<%= (bien != null && bien.getUsuario() != null) ? bien.getUsuario().getPK_idUsuario() : "" %>"> <!-- Enviar el ID del usuario como parámetro oculto -->
        <div class="form-group">
            <label for="informacion">Observación:</label> <!-- Cambiar "information" a "informacion" -->
            <textarea id="informacion" name="informacion" rows="5" cols="30" required></textarea>
        </div>
        <div class="button-container">
            <button class="button">Enviar observación</button>
        </div>
    </form>
</div>
<%@ include file="footerf.jsp" %>
