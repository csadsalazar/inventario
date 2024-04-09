<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nava.jsp" %>
<%@ page import="models.Bien" %>

<main>
    <div class="container">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Editar bien</h2> 
        <br>
        <form action="ActualizarBien" method="post">
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
                        <td data-label="Información">
                            <input type="hidden" name="codigo" value="<%= (bien != null) ? bien.getCodigo() : "" %>">
                            <%= (bien != null) ? bien.getCodigo() : "" %>
                        </td>
                    </tr>
                    <tr>
                        <td data-label="Item">Placa:</td>
                        <td data-label="Información">
                            <input type="text" name="placa" class="informacion" placeholder="Placa" value="<%= (bien != null) ? bien.getPlaca() : "" %>">
                        </td>
                    </tr>
                    <tr>
                        <td data-label="Item">Nombre:</td>
                        <td data-label="Información">
                            <input type="text" name="nombre" class="informacion" placeholder="Nombre" value="<%= (bien != null) ? bien.getNombre() : "" %>">
                        </td>
                    </tr>
                    <tr>
                        <td data-label="Item">Descripción:</td>
                        <td data-label="Información">
                            <input type="text" name="descripcion" class="informacion" placeholder="Descripción" value="<%= (bien != null) ? bien.getDescripcion() : "" %>">
                        </td>
                    </tr>
                    <tr>
                        <td data-label="Item">Valor:</td>
                        <td data-label="Información">
                            <input type="number" name="valor" class="informacion" placeholder="Valor" value="<%= (bien != null) ? bien.getValor() : "" %>">
                        </td>
                    </tr>
                    <tr>
                        <td data-label="Item">Funcionario:</td>
                        <td data-label="Información">
                            <input type="text" name="funcionario" class="informacion" placeholder="Funcionario" value="<%= (bien != null) ? bien.getFuncionario() : "" %>">
                        </td>
                    </tr>
                    <tr>
                        <td data-label="Item">Ubicación:</td>
                        <td data-label="Información">
                            <input type="text" name="ubicacion" class="informacion" placeholder="Ubicación" value="<%= (bien != null) ? bien.getUbicacion() : "" %>">
                        </td>
                    </tr>
                    <tr>
                        <td data-label="Item">Estado:</td>
                        <td data-label="Información">
                            <input type="text" name="estado" class="informacion" placeholder="Estado" value="<%= (bien != null) ? bien.getEstado() : "" %>">
                        </td>
                    </tr>
                </tbody>
            </table>
            <div class="button-container">
                <button type="submit" class="button">Guardar</button>
                <a href="gestionbienes.jsp" class="button">Cancelar</a>
            </div>
        </form>
    </div>
</main>
<%@ include file="footera.jsp" %>
