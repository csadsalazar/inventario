<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="headera.jsp"%>
<%@include file="nava.jsp"%>
<%@ page import="models.Bien" %>
<%@ page import="controllers.ObtenerBienPorCodigo" %>

<%
    // Recuperar el parámetro "codigo" de la URL
    int codigoBien = Integer.parseInt(request.getParameter("codigo"));
    
    // Obtener la información del bien utilizando el controlador ObtenerBienPorCodigo
    Bien bien = ObtenerBienPorCodigo.obtenerBienPorCodigo(codigoBien);
%>

<main>
    <div class="container">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Información del bien</h2>
        <table class="usuario">
            <thead>
                <tr>
                    <th style="width: 15%;">Usuario</th>
                    <th style="width: 15%;">Dependencia</th>
                    <th style="width: 15%;">Cargo</th> 
                    <th style="width: 25%;">Sede</th> 
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td data-label="Usuario"><%= bien.getUsuario().getUsuario() %></td>
                    <td data-label="Dependencia"><%= bien.getUsuario().getDependencia() %></td>
                    <td data-label="Cargo"><%= bien.getUsuario().getCargo() %></td>
                    <td data-label="Sede"><%= bien.getUsuario().getSede() %></td>
                </tr>
            </tbody>
        </table>
        <br>
        <table id="verbiena" class="table">
            <thead>
                <tr>
                    <th style="width: 15%;">Item</th>
                    <th style="width: 50%;">Información</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td data-label="Item">Código:</td>
                    <td data-label="Información"><%= bien.getCodigo() %></td>
                </tr>
                <tr>
                    <td data-label="Item">Placa:</td>
                    <td data-label="Información"><%= bien.getPlaca() %></td>
                </tr>
                <tr>
                    <td data-label="Item">Nombre:</td>
                    <td data-label="Información"><%= bien.getNombre() %></td>
                </tr>
                <tr>
                    <td data-label="Item">Descripción:</td>
                    <td data-label="Información"><%= bien.getDescripcion() %></td>
                </tr>
                <tr>
                    <td data-label="Item">Ubicación:</td>
                    <td data-label="Información"><%= bien.getUbicacion() %></td>
                </tr>
                <tr>
                    <td data-label="Item">Observaciones:</td>
                    <td data-label="Información"><%= bien.getObservaciones() %></td>
                </tr>
                <tr>
                    <td data-label="Item">Imágenes:</td>
                    <td data-label="Información">
                        <!-- Agrega aquí el código para mostrar las imágenes del bien -->
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</main>

<%@include file="footera.jsp"%>
