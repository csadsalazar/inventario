<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nava.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Administrador" %>
<%@ page import="controllers.ListarAdministradores" %>
      <h2>Gestión de administradores</h1>
      </div>
      <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <button class="btn btn-primary" type="button" href="agregaradmin.jsp">Agregar</button>
      </div>
      <br>
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">Usuario</th>
                  <th scope="col">Estado</th>
                  <th scope="col">Acciones</th>
                </tr>
              </thead>
              <tbody>
               <% ArrayList<Administrador> administradores = ListarAdministradores.obtenerAdministradores();
                   for (Administrador admin : administradores) { %>
                <tr>
                <td><%= admin.getUsuario() %></td>
                <td><%= admin.getEstado() %></td>
                <td>  
                    <div class="acciones">
                    <a href="editaradmin.jsp?codigo=<%= admin.getPK_idAdministrador() %>">
                    <img src="resources/img/icons/edit.svg" alt="edit">
                    </a>
                    <a onclick="eliminar('<%= admin.getPK_idAdministrador() %>')">
                    <img src="resources/img/icons/trash.svg" alt="trash" >
                    </a>
                    </div>
                </td>
                </tr>
                <%   
                }
                %>
              </tbody>
            </table>
</main>
<%@ include file="footera.jsp" %>