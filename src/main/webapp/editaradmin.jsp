<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nava.jsp" %>
<%@ page import="models.Administrador" %>
<%@ page import="controllers.ListarAdministradorPorCodigo" %>
<main>
    <div class="container">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Editar administrador</h2>  
        <br>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p> 
        </c:if>
           <form action="EditarAdministrador" method="POST">
            <% 
                // Obtener el código del administrador de la solicitud
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                // Obtener el administrador por su código
                Administrador admin = ListarAdministradorPorCodigo.obtenerAdministradorPorCodigo(codigo);
            %>
            <table id="agregarbien" class="table">
                <thead>
                    <tr>
                        <th style="width: 15%;">Item</th>
                        <th style="width: 50%;">Información</th>
                    </tr>
                </thead>
                <tbody>
                    <input type="hidden" name="codigo" value="<%= admin.getPK_idAdministrador() %>">
                    <tr>
                        <td data-label="Item">Usuario:</td>
                        <td data-label="Información"><input type="text" id="usuario" name="usuario" readonly value="<%= admin.getUsuario() %>"></td>
                    </tr>
                    <tr>
                        <td data-label="Item">Estado:</td>
                        <td data-label="Información">
                            <select class="informacion" name="estado" id="estado" style="width: 100%">
                                <option value="Inactivo" <%= admin.getEstado().equals("Inactivo") ? "selected" : "" %>>Inactivo</option>
                                <option value="Activo" <%= admin.getEstado().equals("Activo") ? "selected" : "" %>>Activo</option>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table> 
            <div class="button-container">
                <button class="button" type="submit">Guardar</button>
                <a href="gestionadmin.jsp" class="button-second">Cancelar</a>
            </div>
        </form>
    </div>
</main>
<%@ include file="footera.jsp" %>
 