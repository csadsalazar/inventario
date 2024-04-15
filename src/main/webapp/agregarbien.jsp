<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nava.jsp" %>
<%@ page import="models.Dependencia" %>
<%@ page import="controllers.DependenciaController" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main>
    <div class="container">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Agregar bien</h2>  
        <br>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
        <form action="AgregarBien" method="POST">
            <table id="agregarbien" class="table">
            <script>
    // Iterar sobre la lista de dependencias y mostrar cada una en la consola
    <c:forEach items="${dependencias}" var="dependencia">
        console.log("Dependencia: <c:out value="${dependencia.nombreDependencia}" />");
    </c:forEach>
</script>

                <thead>
                    <tr>
                        <th style="width: 15%;">Item</th>
                        <th style="width: 50%;">Información</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td data-label="Item">Funcionario:</td>
                        <td data-label="Información"><input type="text" name="usuario" class="informacion" placeholder="Funcionario" required></td>
                    </tr>
                    <tr>
                        <td data-label="Item">Código:</td>
                        <td data-label="Información"><input type="number" name="codigo" class="informacion" placeholder="Codigo" required></td>
                    </tr>
                    <tr>
                        <td data-label="Item">Placa:</td>
                        <td data-label="Información"><input type="number" name="placa" class="informacion" placeholder="Placa" required></td>
                    </tr>
                    <tr>
                        <td data-label="Item">Nombre:</td>
                        <td data-label="Información"><input type="text" name="nombre" class="informacion" placeholder="Nombre" required></td>
                    </tr>
                    <tr>
                    <td data-label="Item">Ubicación:</td>
                    <td data-label="Información">
                    <select class="informacion" name="dependencia" placeholder="Seleccione la ubicación del bien">
                        <c:forEach items="${dependencias}" var="dependencia">
                            <option value="${dependencia.PK_idDependencia}">${dependencia.nombreDependencia}</option>
                        </c:forEach>
                    </select>
                    </td>
                    </tr>
                    <tr>
                        <td data-label="Item">Descripción:</td>
                        <td data-label="Información"><input type="text" name="descripcion" class="informacion" placeholder="Descripción" required></td>
                    </tr>
                    <tr>
                        <td data-label="Item">Valor:</td>
                        <td data-label="Información"><input type="number" name="valor" class="informacion" placeholder="Valor" required></td>
                    </tr>
                    <tr>
                        <td data-label="Item">Estado:</td>
                        <td data-label="Información">
                            <select class="informacion" name="estado" placeholder="Seleccione el estado del bien">
                                <option value="No reportado">No reportado</option>
                                <option value="Reportado">Reportado</option>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>
            <!-- Aquí termina el formulario -->
            <div class="button-container">
                <button class="button">Enviar</button>
                <a href="gestionbienes.jsp" class="button-second">Cancelar</a>
            </div>
        </form>
    </div>
</main>
<%@ include file="footera.jsp" %>
