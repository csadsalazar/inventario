<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nava.jsp" %>
<%@ page import="models.Object" %>
<%@ page import="controllers.ListObjectById" %>

<%
    // Recuperar el parámetro "codigo" de la URL
    long codigoBien = Long.parseLong(request.getParameter("codigo"));
    
    // Obtener la información del bien utilizando el controlador ListarBienPorCodigo
    Object bien = ListObjectById.getObjectById(codigoBien);
%>
<main>
<div class="container">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Detalles del bien <%= (bien != null) ? bien.getPlaca() : "" %></h2>  
    <br> 

<table class="table">
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
                <td data-label="Item">Responsable:</td>
                <td data-label="Información"><%= (bien != null && bien.getUsuario() != null) ? bien.getUsuario().getUsuario() : "" %></td>
                </tr>
                <tr>
                    <td data-label="Item">Nombre:</td>
                    <td data-label="Información"><%= (bien != null) ? bien.getNombre() : "" %></td>
                </tr>
                <tr>
                    <td data-label="Item">Descripción:</td>
                    <td data-label="Información"><%= (bien != null) ? bien.getDescripcion() : "" %></td>
                </tr>
                    <td data-label="Item">Ubicación:</td>
                    <td data-label="Información"><%= (bien != null && bien.getDependencia() != null) ? bien.getDependencia().getnombreDependencia() : "" %></td>
                </tr>
                <tr>
                    <td data-label="Item">Valor:</td>
                    <td data-label="Información"><%= (bien != null) ? bien.getValor() : "" %>&nbsp;COP</td>
                </tr>
                <tr>
                    <td data-label="Item">Estado:</td>
                    <td data-label="Información"><%= (bien != null) ? bien.getEstado() : "" %></td>
                </tr>
            </tbody>
        </table>
    </div>
</main>
<%@ include file="footera.jsp" %>

