<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nav.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Object" %>
<%@ page import="controllers.ListObjects" %>
<div class="container mt-3">
    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="homea.jsp">Inicio</a></li>
            <li class="breadcrumb-item active" aria-current="page">Almacen</li>
        </ol>
    </nav>
</div>
<main class="container">
    <div class="text-center">
      <h1>Inventario personalizado - INVIMA</h1>
      <h2>Almacen</h1>
    </div>
    <div class="d-grid gap-2 d-md-flex justify-content-md-between">
        <div>
            <a class="btn btn-primary" type="button" onclick="deleteAllObjects()">Eliminar almacen</a>
        </div>
        <div>
            <a class="btn btn-primary" type="button" href="addobject.jsp">Agregar</a>
            <a class="btn btn-primary" type="button" href="uploadfile.jsp">Subir almacen</a>
        </div>
    </div>
    <br>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Codigo</th>
            <th scope="col">Placa</th>
            <th scope="col">Nombre</th>
            <th scope="col">Funcionario</th>
            <th scope="col">Dependencia</th>
            <th scope="col">Fecha Creacion</th>
            <th scope="col">Acciones</th>
        </tr>
        </thead>
        <tbody>
            <%
            ArrayList<Object> bienes = ListObjects.getObjects();
            for (Object bien : bienes) {
            %>
        <tr>
            <td><%= bien.getCodigo() %></td>
            <td><%= bien.getPlaca() %></td>
            <td><%= bien.getNombre() %></td>
            <td><%= bien.getUsuario().getUsuario() %></td>
            <td><%= bien.getUsuario().getDependencia() %></td>
            <td><%= bien.getFecha() %></td>
            
            <td>  
                <div class="acciones">
                    <a href="seeobjecta.jsp?codigo=<%= bien.getCodigo() %>">
                    <img src="resources/img/icons/airplay.svg" alt="airplay">
                    </a>&nbsp;&nbsp;&nbsp;
                    <a href="editobject.jsp?codigo=<%= bien.getCodigo() %>">
                    <img src="resources/img/icons/edit.svg" alt="edit">
                    </a>&nbsp;&nbsp;&nbsp; 
                    <a onclick="action('<%= bien.getCodigo() %>')">
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
