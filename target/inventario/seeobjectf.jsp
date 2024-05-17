<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headerf.jsp" %>
<%@ page import="models.Object" %>
<%@ page import="controllers.ListObjectById" %>
<%
    // Recuperar el parámetro "codigo" de la URL
    long codigoBien = Long.parseLong(request.getParameter("codigo"));
    // Obtener la información del bien utilizando el controlador ListarBienPorCodigo
    Object bien = ListObjectById.getObjectById(codigoBien);
%>
  <div class="container mt-3">
    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="homef.jsp">Inicio</a></li>
            <li class="breadcrumb-item active" aria-current="page">Detalles del bien</li>
        </ol>
    </nav>
    </div>
      <main class="container">
      <div class="text-center">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Detalles del bien <%= (bien != null) ? bien.getPlaca() : "" %></h2>
      </div>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
    <form class="row g-3 py-3" action="AddObservation" method="POST">
      <div class="col-md-4">
          <label for="codigo" class="form-label">Codigo</label>
          <input class="form-control" id="codigo" name="codigo" readonly value="<%= (bien != null) ? bien.getCodigo() : "" %>">
      </div>
      <div class="col-md-4">
          <label for="placa" class="form-label">Placa</label>
          <input class="form-control" name="placa" id="placa" readonly value="<%= (bien != null) ? bien.getPlaca() : "" %>">
      </div>
      <div class="col-md-4">
          <label for="nombre" class="form-label">Nombre</label>
          <input class="form-control" name="nombre" id="nombre" readonly value="<%= (bien != null) ? bien.getNombre() : "" %>">
      </div>    
      <div class="col-md-4">
          <label for="descripcion" class="form-label">Descripción</label>
          <textarea name="descripcion" id="descripcion" class="form-control" readonly><%= (bien != null) ? bien.getDescripcion() : "" %></textarea>
      </div>
      <div class="col-md-4">
          <label for="dependencia" class="form-label">Dependencia</label>
          <textarea name="dependencia" id="dependencia" class="form-control" readonly><%= (bien != null && bien.getDependencia() != null) ? bien.getDependencia().getnombreDependencia() : "" %></textarea>
      </div>
        <form action="Observacion" method="POST"> <!-- Utiliza el servlet Observacion para procesar la observación -->
            <input type="hidden" name="codigoBien" value="<%= bien.getCodigo() %>"> <!-- Enviar el código del bien como parámetro oculto -->
            <input type="hidden" name="idUsuario" value="<%= (bien != null && bien.getUsuario() != null) ? bien.getUsuario().getPK_idUsuario() : "" %>"> <!-- Enviar el ID del usuario como parámetro oculto -->
            <div class="col-md-4">
                <label  class="form-label" for="informacion">Observación:</label> <!-- Cambiar "information" a "informacion" -->
                <textarea class="form-control" id="informacion" name="informacion" rows="5" cols="30" required></textarea>
            </div>
             <div>
            </div>
            <div class="col-md-4">
            <button class="btn btn-primary">Enviar observación</button>
            </div>
        </form>
    </form>
  </main>
<%@ include file="footer.jsp" %>