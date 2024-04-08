<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="headerf.jsp"%>
<%@include file="navf.jsp"%>
<%@include file="usuario.jsp"%>
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
                <td data-label="Información">Código del bien</td>
            </tr>
            <tr>
                <td data-label="Item">Placa:</td>
                <td data-label="Información">Número de placa</td>
            </tr>
            <tr>
                <td data-label="Item">Nombre:</td>
                <td data-label="Información">Nombre del bien</td>
            </tr>
            <tr>
                <td data-label="Item">Descripción:</td>
                <td data-label="Información">Descripción del bien</td>
            </tr>
            <tr>
                <td data-label="Item">Ubicación:</td>
                <td data-label="Información">Ubicación que pertenece el bien</td>
            </tr>
            <tr>
                <td data-label="Item">Imágenes:</td>
                <td data-label="Información">Imagenes que pertenecen al bien</td>
            </tr>
        </tbody>
    </table>
    <div class="form-group">
        <label for="information">Observación:</label>
        <textarea id="information" name="information" rows="5" cols="30" required></textarea>
    </div>
    <div class="button-container">
        <button class="button">Enviar observación</button>
    </div>
</div>
<%@include file="footerf.jsp"%>