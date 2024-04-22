<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="headera.jsp" %>
<%@ include file="nava.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Administrador" %> 
<%@ page import="controllers.ListarAdminPorCodigo" %>
<%
// Verificar si el parámetro "codigo" está presente y no es nulo
String codigoParameter = request.getParameter("codigo");
int codigoAdmin = 0;
if (codigoParameter != null && !codigoParameter.isEmpty()) {
    // Convertir el parámetro "codigo" a un entero
    codigoAdmin = Integer.parseInt(codigoParameter);
}
// Obtener la información del bien utilizando el controlador ListarBienPorCodigo
Administrador administrador = ListarAdminPorCodigo.obtenerAdminPorCodigo(codigoAdmin);
%>

<main>
    <div class="container">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Editar administrador</h2>  
        <br>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p> 
        </c:if>
            <table id="agregarbien" class="table">
                <thead>
                    <tr>
                        <th style="width: 15%;">Item</th>
                        <th style="width: 50%;">Información</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td data-label="Item">Usuario:</td>
                        <td data-label="Información"><input type="text" name="usuario" class="informacion" placeholder="Usuario" readonly value="<%= (administrador != null) ? administrador.getUsuario() : "" %>"></td>
                    </tr>
                     <form action="ActualizarAdmin" method="POST">
                    <tr>
                        <td data-label="Item">Estado:</td>
                        <td data-label="Información">
                            <select class="informacion" name="estado" placeholder="Seleccione el estado del bien" style="width: 100%">
                                <option value="Inactivo" <%= (administrador != null && administrador.getEstado().equals("Inactivo")) ? "selected" : "" %>>Inactivo</option>
                                <option value="Activo" <%= (administrador != null && administrador.getEstado().equals("Activo")) ? "selected" : "" %>>Activo</option>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table> 
            <div class="button-container">
                <button class="button" value="Actualizar">Guardar</button>
                <a href="gestionadministradores.jsp" class="button-second">Cancelar</a>
            </div>
        </form>
    </div>
</main>
<%@ include file="footera.jsp" %>
 