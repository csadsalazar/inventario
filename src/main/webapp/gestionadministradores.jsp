<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nava.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Administrador" %>
<%@ page import="controllers.ListarAdministradores" %>
<main>
    <div class="container">
        <h1>Inventario personalizado - INVIMA</h1> 
        <h2>Gestion administradores</h2> 
        <table class="table">
            <thead>
                <tr>
                    <th style="width: 20%;">Usuario</th>
                    <!-- <th style="width: 10%;">Permiso</th> -->
                    <th style="width: 20%;">Estado</th>
                    <th style="width: 25%;">Acciones</th>
                </tr>
            </thead> 
            <tbody>
                <% 
                    ArrayList<Administrador> administradores = ListarAdministradores.obtenerAdministradores();
                    for (Administrador administrador : administradores) {
                %>
                <tr> 
                    <td data-label="Usuario"><%= administrador.getUsuario() %></td>
                    <td data-label="Estado"><%= administrador.getEstado() %></td>  
                    <td data-label="Acciones">
                        <div class="acciones">
                            <a href="editaradmin.jsp?codigo=<%= administrador.getCodigo() %>">
                            <img src="resources/img/edit.svg" alt="edit">
                            </a>
                            <a onclick="eliminar('<%= administrador.getCodigo() %>')">
                            <img src="resources/img/trash.svg" alt="trash" >
                            </a>
                        </div>
                    </td>
                </tr>
                <%   
                    }
                %>
            </tbody>
        </table>
           <div class="button-container">
            <a href="agregaradmin.jsp" class="button">Agregar administrador</a>
        </div>
    </div>
</main>
<%@ include file="footera.jsp" %>
