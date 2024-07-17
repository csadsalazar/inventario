<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="headera.jsp" %>
<%@ include file="nav.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Object" %>
<%@ page import="models.Dependency" %>
<%@ page import="controllers.ListDependencies" %>
<%@ page import="controllers.ListObjects" %>

<div class="container mt-3">
    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="homea.jsp">Inicio</a></li>
            <li class="breadcrumb-item"><a href="managementobjects.jsp">Almacén</a></li>
            <li class="breadcrumb-item active" aria-current="page">Editar masivamente</li>
        </ol>
    </nav>
</div>

<main class="container">
    <div class="text-center">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Editar bienes seleccionados</h2>
    </div>

    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <form class="row g-3 py-3" method="POST" action="EditMasive">
        <div class="col-md-4">
            <label for="descripcion" class="form-label">Descripción</label>
            <textarea name="descripcion" class="form-control" rows="1" required></textarea>
        </div>
        <div class="col-md-4">
            <label for="observacion" class="form-label">Observación</label>
            <textarea name="observacion" class="form-control" rows="1" required></textarea>
        </div>
        <div class="col-md-4">
            <label for="usuario" class="form-label">Responsable</label>
            <input type="text" name="usuario" class="form-control" placeholder="Responsable" required>
        </div>
        <div class="col-md-4">
            <label for="dependencia" class="form-label">Dependencia</label>
            <select class="form-control" id="dependencia" name="dependencia">
                <% ArrayList<Dependency> dependencias = ListDependencies.getDependencies();
                   for (Dependency dep : dependencias) { %>
                <option value="<%= dep.getPK_idDependency() %>"><%= dep.getDependencyname() %></option>
                <% } %>
            </select>
        </div>
        <div class="col">
            <button class="btn btn-primary" type="submit">Guardar cambios</button>
        </div>
        <input type="hidden" id="selectedIds" name="selectedIds" />
    </form>
</main>

<%@ include file="footer.jsp" %>