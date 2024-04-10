<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nava.jsp" %>
<%@ page import="models.Bien" %>
<%@ page import="controllers.ListarBienPorCodigo" %>

<%
    // Recuperar el parámetro "codigo" de la URL
    int codigoBien = Integer.parseInt(request.getParameter("codigo"));
    
    // Obtener la información del bien utilizando el controlador ListarBienPorCodigo
    Bien bien = ListarBienPorCodigo.obtenerBienPorCodigo(codigoBien);
%>
<main>
<div class="container">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Detalles del bien: <%= (bien != null) ? bien.getPlaca() : "" %></h2>  
    <br>

<table id="agregarbien" class="table">
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
                    <td data-label="Item">Ubicación:</td>
                    <td data-label="Información"><%= (bien != null) ? bien.getUbicacion() : "" %></td>
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

