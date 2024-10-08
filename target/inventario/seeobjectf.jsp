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
        <h2>Detalles del bien <%= (bien != null) ? bien.getPlate() : "" %></h2>
      </div>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p> 
        </c:if>
    <form class="row g-3 py-3" action="AddObservation" method="POST">
      <div class="col-md-4">
          <label for="codigo" class="form-label">Codigo</label>
          <input class="form-control" id="codigo" name="codigo" readonly value="<%= (bien != null) ? bien.getCode() : "" %>">
      </div>
      <div class="col-md-4">
          <label for="placa" class="form-label">Placa</label>
          <input class="form-control" name="placa" id="placa" readonly value="<%= (bien != null) ? bien.getPlate() : "" %>">
      </div>
      <div class="col-md-4">
          <label for="nombre" class="form-label">Nombre</label>
          <input class="form-control" name="nombre" id="nombre" readonly value="<%= (bien != null) ? bien.getName() : "" %>">
      </div>  
      <div class="col-md-4">
          <label for="estado" class="form-label">Estado</label>
          <input class="form-control" id="estado" name="estado" readonly value="<%= (bien != null) ? bien.getState() : "" %>">
      </div>  
      <div class="col-md-4">
          <label for="descripcion" class="form-label">Descripción</label>
          <textarea name="descripcion" id="descripcion" class="form-control" readonly rows="1"><%= (bien != null) ? bien.getDescription() : "" %></textarea>
      </div>
      <div class="col-md-4"> 
          <label for="dependencia" class="form-label">Dependencia</label>
          <textarea name="dependencia" id="dependencia" class="form-control" readonly rows="1"><%= (bien != null && bien.getPK_idDependency() != null) ? bien.getPK_idDependency().getDependencyname() : "" %></textarea>
      </div>
      <div class="col-md-4">
          <label for="observacion" class="form-label">Observación de almacen</label>
          <textarea name="observacion" id="observacion" class="form-control" readonly rows="1"><% 
            if (bien != null && bien.getObservation() != null && !bien.getObservation().isEmpty()) {
                out.print(bien.getObservation());
            } else {
                out.print("Sin observación");
            }
        %></textarea>
      </div>
      <div>
            <div>
                <label for="imagenuno" class="form-label">Imagen 1</label>
                <c:if test="${not empty bien.imageOne}">
                    <img src="<%= (bien != null) ? bien.getImageOne() : "" %>" alt="No se adjunta evidencia" class="img-fluid rounded mx-auto d-block">
                </c:if>
            </div>
            <div>
                <label for="imagendos" class="form-label">Imagen 2</label>
                <c:if test="${not empty bien.imageTwo}">
                    <img src="<%= (bien != null) ? bien.getImageTwo() : "" %>" alt="No se adjunta evidencia" class="img-fluid rounded mx-auto d-block">
                </c:if>
            </div>
            <div>
                <label for="imagentres" class="form-label">Imagen 3</label>
                <c:if test="${not empty bien.imageThree}">
                    <img src="<%= (bien != null) ? bien.getImageThree() : "" %>" alt="No se adjunta evidencia" class="img-fluid rounded mx-auto d-block">
                    <img  class="">
                </c:if>
            </div>    
        </div>      
        <form action="Observacion" method="POST"> <!-- Utiliza el servlet Observacion para procesar la observación -->
            <input type="hidden" name="placaBien" value="<%= (bien != null) ? bien.getPlate() : "" %>">
            <input type="hidden" name="codigoBien" value="<%= bien.getCode() %>"> <!-- Enviar el código del bien como parámetro oculto -->
            <input type="hidden" name="idUsuario" value="<%= (bien != null && bien.getUser() != null) ? bien.getUser().getPK_idUser() : "" %>"> <!-- Enviar el ID del usuario como parámetro oculto -->
            <div class="col-md-4">
                <label  class="form-label" for="informacion">Observación:</label> <!-- Cambiar "information" a "informacion" -->
                <textarea class="form-control" id="informacion" name="informacion" rows="5" cols="30"></textarea>
            </div>
             <div>
            </div>
            <div class="col-md-4">
            <button class="btn btn-primary">Enviar observación</button>
            <button class="btn btn-primary">Volver</button>
            </div>
        </form>
    </form>
  </main>
<%@ include file="footer.jsp" %>