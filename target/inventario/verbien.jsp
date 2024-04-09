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
        <h2>Información del bien</h2>
        <table class="table">
            <tbody>
                <tr>
                    <td>Código:</td>
                    <td><%= (bien != null) ? bien.getCodigo() : "" %></td>
                </tr>
                <tr>
                    <td>Placa:</td>
                    <td><%= (bien != null) ? bien.getPlaca() : "" %></td>
                </tr>
                <tr>
                    <td>Nombre:</td>
                    <td><%= (bien != null) ? bien.getNombre() : "" %></td>
                </tr>
                <tr>
                    <td>Descripción:</td>
                    <td><%= (bien != null) ? bien.getDescripcion() : "" %></td>
                </tr>
                <tr>
                    <td>Ubicación:</td>
                    <td><%= (bien != null) ? bien.getUbicacion() : "" %></td>
                </tr>
                <tr>
                    <td>Valor:</td>
                    <td><%= (bien != null) ? bien.getValor() : "" %></td>
                </tr>
                <tr>
                    <td>Estado:</td>
                    <td><%= (bien != null) ? bien.getEstado() : "" %></td>
                </tr>
            </tbody>
        </table>
    </div>
</main>

<%@ include file="footera.jsp" %>
