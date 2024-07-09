<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nav.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.User" %>
<%@ page import="models.Profile" %>
<%@ page import="controllers.ListAdministrators" %>
<div class="container mt-3">
      <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="homea.jsp">Inicio</a></li>
            <li class="breadcrumb-item active" aria-current="page">Administradores</li>
        </ol>
    </nav>
</div>  
<main class="container">
    <div class="text-center">
      <h1>Inventario personalizado - INVIMA</h1>
        <h2>Administradores</h1>
    </div>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
       <a class="btn btn-primary" type="button" href="addadmin.jsp">Agregar</a>
    </div>
    <br>
    <div class="table-responsive">
    <table class="w-100 table table-head">
        <thead>
        <tr>
            <th scope="col">Usuario</th>
            <th scope="col">Estado</th> 
            <th scope="col">Acciones</th>
        </tr>
        </thead> 
        <tbody>
            <% ArrayList<User> administradores = ListAdministrators.getAdministrators();
                   for (User admin : administradores) { %>
        <tr>
            <td><%= admin.getUser() %></td>
            <td><%= admin.getPK_idProfile().getProfileName() %></td>
            <td>  
              <div class="acciones"> 
                <a href="editadmin.jsp?codigo=<%= admin.getPK_idUser() %>">
                <img src="resources/img/icons/edit.svg" alt="edit">
                </a>
                <a onclick="eliminar('<%= admin.getPK_idUser() %>')">
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
      </div>
</main>
<%@ include file="footer.jsp" %>