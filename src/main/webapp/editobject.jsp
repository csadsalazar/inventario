<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="headera.jsp" %>
<%@ include file="nav.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Object" %> 
<%@ page import="models.Dependency" %>
<%@ page import="controllers.ListDependencies" %>
<%@ page import="controllers.ListObjectById" %>
<%
// Obtener la lista de dependencias utilizando el controlador
ArrayList<Dependency> dependencias = ListDependencies.getDependencies();
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
Object bien = ListObjectById.getObjectById(codigoBien);
%>
  <div class="container">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="homea.jsp">Home</a></li>
        <li class="breadcrumb-item"><a href="managementobjects.jsp">Almacen</a></li>
        <li class="breadcrumb-item active" aria-current="page">Agregar bien</li>
      </ol>
    </nav>
    </div>
     <main class="container">
      <div class="text-center">
      <h1>Inventario personalizado - INVIMA</h1>
          <h2>Agregar bien</h1>
      </div>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
    <form class="row g-3 py-3" method="POST" action="EditObject">
      <div class="col-md-4">
          <label for="codigo" class="form-label">Codigo</label>
          <input class="form-control" type="text" id="codigo" name="codigo" readonly value="<%= (bien != null) ? bien.getCodigo() : "" %>">
      </div>
      <div class="col-md-4">
          <label for="placa" class="form-label">Placa</label>
          <input type="number" class="form-control" name="placa" id="placa" readonly value="<%= (bien != null) ? bien.getPlaca() : "" %>">
      </div>
      <div class="col-md-4">
          <label for="usuario" class="form-label">Responsable</label>
          <input type="text" name="usuario" class="form-control" placeholder="Responsable" required value="<%= (bien != null && bien.getUsuario() != null) ? bien.getUsuario().getUsuario() : "" %>">            
      </div>
      </div>
      <div class="col-md-4">
          <label for="nombre" class="form-label">Nombre</label>
          <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre" required value="<%= (bien != null) ? bien.getNombre() : "" %>">
      </div>
      <div class="col-md-4">
          <label for="dependencia" class="form-label">Ubicacion</label>
            <select class="form-control" id="dependencia" name="dependencia">
                <% if (dependencias != null && !dependencias.isEmpty()) { %>
                <% for (Dependency dep : dependencias) { %>
                <option value="<%= dep.getPK_idDependencia() %>" <%= (bien != null && dep.getnombreDependencia().equals(bien.getDependencia().getnombreDependencia())) ? "selected" : "" %> >
                <%= dep.getnombreDependencia() %>
                </option>
                <% } %>
                <% } %> 
            </select>
      </div>
      <div class="col-md-4">
          <label for="valor" class="form-label">Valor</label>
          <input class="form-control" type="text" name="valor" placeholder="Valor" required value="<%= (bien != null) ? bien.getValor() : "" %>">
      </div>      
      <div class="col-md-4">
          <label for="estado" class="form-label">Estado</label>
            <select class="form-control" id="estado" name="estado">
                <option value="No reportado" <%= (bien != null && bien.getEstado().equals("No reportado")) ? "selected" : "" %>>No reportado</option>
                <option value="Reportado" <%= (bien != null && bien.getEstado().equals("Reportado")) ? "selected" : "" %>>Reportado</option>
            </select>
      </div>
       <div class="col-md-4">
          <label for="descripcion" class="form-label">Descripción</label>
          <textarea name="descripcion" class="form-control" rows="3" placeholder="Descripcion" required><%= (bien != null) ? bien.getDescripcion() : "" %></textarea>
      </div>
      <div>
      </div>
      <div class="col">
          <button class="btn btn-primary" value="Actualizar">Guardar</button>
          <a class="btn btn-secondary" href="managementobjects.jsp">Cancelar</a>
      </div>
    </form>
  </main>
<%@ include file="footera.jsp" %>