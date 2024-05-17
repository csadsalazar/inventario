<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nav.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Dependency" %>
<%@ page import="controllers.ListDependencies" %>
    <div class="container mt-3">
    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="homea.jsp">Inicio</a></li>
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
    <form class="row g-3 needs-validation py-3" method="POST" action="AddObject">
      <div class="col-md-4">
          <label for="usuario" class="form-label">Funcionario</label>
          <input type="text" class="form-control" name="usuario" id="usuario" required>
      </div>
      <div class="col-md-4">
          <label for="codigo" class="form-label">Codigo</label>
          <input type="number" class="form-control" name="codigo" id="codigo" required>
      </div>
      <div class="col-md-4">
          <label for="placa" class="form-label">Placa</label>
      <div class="input-group has-validation">
          <input type="number" class="form-control" name="placa" id="placa" aria-describedby="inputGroupPrepend" required>
      </div>
      </div>
      <div class="col-md-4">
          <label for="nombre" class="form-label">Nombre</label>
          <input type="text" class="form-control" name="nombre" id="nombre" required>
      </div>
      <div class="col-md-4">
          <label for="dependencia" class="form-label">Ubicacion</label>
          <select class="form-select" name="dependencia" id="dependencia" required>
            <%
              ArrayList<Dependency> dependencias = ListDependencies.getDependencies();
              for (Dependency dependencia : dependencias) {
              %>
              <option value="<%= dependencia.getPK_idDependencia() %>"><%= dependencia.getnombreDependencia() %></option>
              <%  
              }
            %>
          </select>
      </div>
      <div class="col-md-4">
          <label for="valor" class="form-label">Valor</label>
          <input type="number" class="form-control" name="valor" id="valor" required>
      </div>
      <div class="col-md-4">
          <label for="descripcion" class="form-label">Descripción</label>
          <textarea class="form-control" name="descripcion" id="descripcion" rows="1"></textarea>
      </div>
      <div class="col-md-4">
          <label for="estado" class="form-label">Estado</label>
          <select class="form-select" id="estado" name="estado">
            <option value="No reportado">No reportado</option>
            <option value="Reportado">Reportado</option>
          </select>
      </div>
      <div class="col-md-agre">
      </div>
      <div class="col">
          <button class="btn btn-primary">Agregar</button>
          <a class="btn btn-secondary" href="managementobjects.jsp">Cancelar</a>
      </div>
    </form>
  </main>
 <%@ include file="footer.jsp" %>