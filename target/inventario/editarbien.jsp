<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="headera.jsp"%>
<%@include file="nava.jsp"%>
<main>
    <div class="container">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Editar bien</h2> 
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
                <td data-label="Item">Funcionario:</td>
                <td data-label="Información"><input type="text" name="Funcionario" class="informacion" placeholder="Funcionario" required></td>
            </tr>
            <tr>
                <td data-label="Item">Código:</td>
                <td data-label="Información"><input type="number" name="codigo" class="informacion" placeholder="Codigo"></td>
            </tr>
            <tr>
                <td data-label="Item">Placa:</td>
                <td data-label="Información"><input type="number" name="placa" class="informacion" placeholder="Placa"></td>
            </tr>
            <tr>
                <td data-label="Item">Nombre:</td>
                <td data-label="Información"><input type="text" name="nombre" class="informacion" placeholder="Nombre"></td>
            </tr>
            <tr>
                <td data-label="Item">Ubicación:</td>
                <td data-label="Información"><input type="text" name="descripcion" class="informacion" placeholder="Ubicación"></td>
            </tr>
            <tr>
                <td data-label="Item">Descripción:</td>
                <td data-label="Información"><input type="text" name="grupo" class="informacion" placeholder="Descripción"></td>
            </tr>
            <tr>
                <td data-label="Item">Valor:</td>
                <td data-label="Información"><input type="number" name="imagenes" class="informacion" placeholder="Valor"></td>
            </tr>
        </tbody>
    </table>
    <div class="button-container">
        <button class="button">Guardar</button>
        <a href="gestionbienes.jsp" class="button">Cancelar</a>
    </div>
</div>
</main>
<%@include file="footera.jsp"%>

