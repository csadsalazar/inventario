<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nava.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Admin" %>
<%@ page import="controllers.ListAdministrators" %>
<div class="container">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="homea.jsp">Inicio</a></li>
        <li class="breadcrumb-item active" aria-current="page">Administradores</li>
      </ol>
    </nav>
</div>
<main class="container">
    <div class="text-center">
      <h1>Inventario personalizado - INVIMA</h1>
        <h2>Almacen</h1>
    </div>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
       <a class="btn btn-primary" type="button" href="addadmin.jsp">Agregar</a>
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
            <% ArrayList<Admin> administradores = ListAdministrators.getAdministrators();
                   for (Admin admin : administradores) { %>
        <tr>
            <td><%= admin.getUsuario() %></td>
            <td><%= admin.getEstado() %></td>
            <td>  
              <div class="acciones">
                <a href="editadmin.jsp?codigo=<%= admin.getPK_idAdministrador() %>">
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