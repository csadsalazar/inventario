<%@ page contentType="text/html; charset=utf-8" %>
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
  <div class="container mt-3">
    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="homea.jsp">Inicio</a></li>
            <li class="breadcrumb-item"><a href="managementobjects.jsp">Almacen</a></li>
            <li class="breadcrumb-item active" aria-current="page">Editar bien</li>
        </ol>
    </nav>
    </div>
     <main class="container">
      <div class="text-center">
      <h1>Inventario personalizado - INVIMA</h1>
          <h2>Editar bien</h1>
      </div>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
    <form class="row g-3 py-3" method="POST" action="EditObject">
      <div class="col-md-4">
          <label for="codigo" class="form-label">Codigo</label>
          <input class="form-control" type="text" id="codigo" name="codigo" readonly value="<%= (bien != null) ? bien.getCode() : "" %>">
      </div>
      <div class="col-md-4">
          <label for="placa" class="form-label">Placa</label>
          <input type="number" class="form-control" name="placa" id="placa" readonly value="<%= (bien != null) ? bien.getPlate() : "" %>">
      </div>
      <div class="col-md-4">
          <label for="fecha" class="form-label">Fecha de creación</label>
          <input type="date" class="form-control" name="fecha" id="fecha" readonly value="<%= (bien != null) ? bien.getDate() : "" %>">
      </div>
      <div class="col-md-4">
          <label for="usuario" class="form-label">Responsable</label>
          <input type="text" name="usuario" class="form-control" placeholder="Responsable" required value="<%= (bien != null && bien.getUser() != null) ? bien.getUser().getUser() : "" %>">            
      </div>
      </div>
      <div class="col-md-4">
          <label for="nombre" class="form-label">Nombre</label>
          <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre" required value="<%= (bien != null) ? bien.getName() : "" %>">
      </div>
      <div class="col-md-4">
          <label for="dependencia" class="form-label">Ubicacion</label>
            <select class="form-control" id="dependencia" name="dependencia">
                <% if (dependencias != null && !dependencias.isEmpty()) { %>
                <% for (Dependency dep : dependencias) { %>
                <option value="<%= dep.getPK_idDependency() %>" <%= (bien != null && dep.getDependencyname().equals(bien.getPK_idDependency().getDependencyname())) ? "selected" : "" %> >
                <%= dep.getDependencyname() %>
                </option>
                <% } %>
                <% } %> 
            </select>
      </div>
      <div class="col-md-4">
          <label for="valor" class="form-label">Valor</label>
          <input class="form-control" type="text" name="valor" placeholder="Valor" required value="<%= (bien != null) ? bien.getValue() : "" %>">
      </div>      
      <div class="col-md-4">
          <label for="estado" class="form-label">Estado</label>
            <select class="form-control" id="estado" name="estado">
                <option value="No reportado" <%= (bien != null && bien.getState().equals("No reportado")) ? "selected" : "" %>>No reportado</option>
                <option value="En espera" <%= (bien != null && bien.getState().equals("En espera")) ? "selected" : "" %>>En espera</option>
                <option value="Reportado" <%= (bien != null && bien.getState().equals("Reportado")) ? "selected" : "" %>>Reportado</option>
            </select>
      </div>
       <div class="col-md-4">
          <label for="descripcion" class="form-label">Descripción</label>
          <textarea name="descripcion" class="form-control" rows="3" placeholder="Descripcion" required><%= (bien != null) ? bien.getDescription() : "" %></textarea>
      </div>
      <div>
      </div>
      <div class="col">
          <button class="btn btn-primary" value="Actualizar">Guardar</button>
      </div>
    </form>
  </main>
<%@ include file="footer.jsp" %>