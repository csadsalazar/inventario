<%@ page contentType="text/html; charset=utf-8" %>
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
        <h2>Editar bien</h2>
    </div>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <form class="row g-3 py-3" method="POST" action="EditObject" enctype="multipart/form-data">
        <div class="col-md-4">
            <label for="codigo" class="form-label">Codigo</label>
            <input class="form-control" type="text" id="codigo" name="codigo" readonly value="<%= (bien != null) ? bien.getCode() : "" %>">
        </div>
        <div class="col-md-4">
            <label for="placa" class="form-label">Placa</label>
            <input type="number" class="form-control" name="placa" id="placa" readonly value="<%= (bien != null) ? bien.getPlate() : "" %>">
        </div>
        <div class="col-md-4">
            <label for="usuario" class="form-label">Responsable</label>
            <input type="text" name="usuario" class="form-control" placeholder="Responsable" required value="<%= (bien != null && bien.getUser() != null) ? bien.getUser().getUser() : "" %>">
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
                <option value="<%= dep.getPK_idDependency() %>" <%= (bien != null && dep.getPK_idDependency() == bien.getPK_idDependency().getPK_idDependency()) ? "selected" : "" %> >
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
                <option value="No reportado" <%= (bien != null && "No reportado".equals(bien.getState())) ? "selected" : "" %>>No reportado</option>
                <option value="En espera" <%= (bien != null && "En espera".equals(bien.getState())) ? "selected" : "" %>>En espera</option>
                <option value="Reportado" <%= (bien != null && "Reportado".equals(bien.getState())) ? "selected" : "" %>>Reportado</option>
            </select>
        </div>
        <div class="col-md-4">
            <label for="descripcion" class="form-label">Descripción</label>
            <textarea name="descripcion" class="form-control" rows="1" required><%= (bien != null) ? bien.getDescription() : "" %></textarea>
        </div>
        <div class="col-md-4">
            <label for="observacion" class="form-label">Observación administrador</label>
            <textarea name="observacion" class="form-control" rows="1"><%= (bien != null && bien.getObservation() != null) ? bien.getObservation() : "" %></textarea>
        </div>
        <div class="col-md-4">
            <label for="imagenuno" class="form-label">Imagen 1</label>
            <input type="file" class="form-control" name="imagenuno">
            <% if (bien != null && bien.getImageOne() != null && !bien.getImageOne().isEmpty()) { %>
                <img src="<%= bien.getImageOne() %>" alt="Imagen 1" class="img-fluid rounded mx-auto d-block mt-2">
                <input type="hidden" name="imagenuno_existing" value="<%= bien.getImageOne() %>">
            <% } %>
        </div>
        <div class="col-md-4">
            <label for="imagendos" class="form-label">Imagen 2</label>
            <input type="file" class="form-control" name="imagendos">
            <% if (bien != null && bien.getImageTwo() != null && !bien.getImageTwo().isEmpty()) { %>
                <img src="<%= bien.getImageTwo() %>" alt="Imagen 2" class="img-fluid rounded mx-auto d-block mt-2">
                <input type="hidden" name="imagendos_existing" value="<%= bien.getImageTwo() %>">
            <% } %>
        </div>
        <div class="col-md-4">
            <label for="imagentres" class="form-label">Imagen 3</label>
            <input type="file" class="form-control" name="imagentres">
            <% if (bien != null && bien.getImageThree() != null && !bien.getImageThree().isEmpty()) { %>
                <img src="<%= bien.getImageThree() %>" alt="Imagen 3" class="img-fluid rounded mx-auto d-block mt-2">
                <input type="hidden" name="imagentres_existing" value="<%= bien.getImageThree() %>">
            <% } %>
        </div>
        <div class="col">
            <button class="btn btn-primary" type="submit">Guardar</button>
        </div>
    </form>
</main>
<%@ include file="footer.jsp" %>