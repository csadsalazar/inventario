<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nava.jsp" %>
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
    <main>
        <div class="container">
            <h1>Inventario personalizado - INVIMA</h1>
            <h2>Editar bien</h2>  
        <br>
            <c:if test="${not empty error}">
                <p style="color: red;">${error}</p>
            </c:if>
    <form action="ActualizarBien" method="POST">
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
                        <td data-label="Información"><input type="number" name="codigo" class="informacion" placeholder="Codigo" readonly desabled value="<%= (bien != null) ? bien.getCodigo() : "" %>"></td>
                    </tr>
                    <tr>
                        <td data-label="Item">Placa:</td>
                        <td data-label="Información"><input type="number" name="placa" class="informacion" placeholder="Placa" desabled readonly value="<%= (bien != null) ? bien.getPlaca() : "" %>"></td>
                    </tr>
                    <tr>
                    <td data-label="Item">Responsable:</td>
                    <td data-label="Información"><input type="text" name="usuario" class="informacion" placeholder="Responsable" required value="<%= (bien != null && bien.getUsuario() != null) ? bien.getUsuario().getUsuario() : "" %>"></td>
                    </tr>
                    <tr>
                        <td data-label="Item">Nombre:</td>
                        <td data-label="Información"><input type="text" name="nombre" class="informacion" placeholder="Nombre" required value="<%= (bien != null) ? bien.getNombre() : "" %>"></td>
                    </tr>
                    <tr>
                        <td data-label="Item">Ubicación:</td>
                        <td data-label="Información">
<select class="informacion" name="dependencia" placeholder="Seleccione la ubicación del bien" style="width: 100%">
    <% for (Dependencia dependencia : dependencias) { %>
        <option value="<%= dependencia.getPK_idDependencia() %>" <%= (bien.getDependencia() != null && dependencia.getPK_idDependencia() == bien.getDependencia().getPK_idDependencia()) ? "selected" : "" %>>
            <%= dependencia.getnombreDependencia() %>
        </option>
    <% } %>
</select>



                        </td>
                        </tr>
                    <tr>
                        <td data-label="Item">Descripción:</td>
                        <td data-label="Información"><textarea name="descripcion" class="informacion" rows="5" cols="30" required value="<%= (bien != null) ? bien.getDescripcion() : "" %>"></textarea></td>
                    </tr>
                    <tr>
                        <td data-label="Item">Valor:</td>
                        <td data-label="Información"><input type="number" name="valor" class="informacion" placeholder="Valor" required value="<%= (bien != null) ? bien.getValor() : "" %>"></td>
                    </tr>
                    <tr>
                    <td data-label="Item">Estado:</td>
                    <td data-label="Información">
                    <select class="informacion" name="estado" placeholder="Seleccione el estado del bien" style="width: 100%">
                        <option value="No reportado" <%= (bien != null && bien.getEstado().equals("No reportado")) ? "selected" : "" %>>
                            No reportado
                        </option>
                        <option value="Reportado" <%= (bien != null && bien.getEstado().equals("Reportado")) ? "selected" : "" %>>
                            Reportado
                        </option>
                    </select>
                    </td>
                    </tr>
                </tbody>
            </table>
            <div class="button-container">
                <button class="button" value="Actualizar">Guardar</button>
                <a href="gestionbienes.jsp" class="button-second">Cancelar</a>
            </div>
        </form>
    </main>
    <%@ include file="footera.jsp" %>

