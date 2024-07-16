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
String[] selectedIds = request.getParameter("selectedIds").split(",");
List<Object> objetosSeleccionados = new ArrayList<>();

if (selectedIds.length > 0) {
    for (String id : selectedIds) {
        try {
            long codigoBien = Long.parseLong(id.trim());
            Object bien = ListObjectById.getObjectById(codigoBien);
            objetosSeleccionados.add(bien);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
%>

    <div class="container mt-3">
        <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="homea.jsp">Inicio</a></li>
                <li class="breadcrumb-item"><a href="managementobjects.jsp">Almacén</a></li>
                <li class="breadcrumb-item active" aria-current="page">Editar seleccionados</li>
            </ol>
        </nav>
    </div>

    <main class="container">
        <div class="text-center">  
            <h1>Inventario personalizado - INVIMA</h1>
            <h2>Editar objetos seleccionados</h2>
        </div>

        <div class="row">
            <div class="col">
                <form id="editarObjetosForm" method="POST" action="EditMasive">
        <% for (Object bien : objetosSeleccionados) { %>
        <input type="hidden" name="selectedIds" value="<%= bien.getCode() %>">
        <div class="mb-3">
            <label for="usuario_<%= bien.getCode() %>" class="form-label">Funcionario</label>
            <input type="text" class="form-control" id="usuario_<%= bien.getCode() %>" name="usuario_<%= bien.getCode() %>" value="<%= bien.getUser().getName() %>">
        </div>
        <div class="mb-3">
            <label for="descripcion_<%= bien.getCode() %>" class="form-label">Descripción</label>
            <textarea class="form-control" id="descripcion_<%= bien.getCode() %>" name="descripcion_<%= bien.getCode() %>"><%= bien.getDescription() %></textarea>
        </div>
        <div class="mb-3">
            <label for="observacion_<%= bien.getCode() %>" class="form-label">Observación</label>
            <textarea class="form-control" id="observacion_<%= bien.getCode() %>" name="observacion_<%= bien.getCode() %>"><%= bien.getObservation() %></textarea>
        </div>
        <div class="col-md-4">
            <label for="dependencia_<%= bien.getCode() %>" class="form-label">Ubicación</label>
            <select class="form-select" name="dependencia_<%= bien.getCode() %>" id="dependencia_<%= bien.getCode() %>">
                <% ArrayList<Dependency> dependencias = ListDependencies.getDependencies();
                for (Dependency dependencia : dependencias) { %>
                    <option value="<%= dependencia.getPK_idDependency() %>" <%= (dependencia.getPK_idDependency() == bien.getDependencyId()) ? "selected" : "" %>><%= dependencia.getDependencyname() %></option>
                <% } %>
            </select>
        </div>
        <div class="col-md-4">
            <label for="estado_<%= bien.getCode() %>" class="form-label">Estado</label>
            <select class="form-select" id="estado_<%= bien.getCode() %>" name="estado_<%= bien.getCode() %>">
                <option value="Reportado" <%= ("Reportado".equals(bien.getState())) ? "selected" : "" %>>Reportado</option>
                <option value="En espera" <%= ("En espera".equals(bien.getState())) ? "selected" : "" %>>En espera</option>
                <option value="No reportado" <%= ("No reportado".equals(bien.getState())) ? "selected" : "" %>>No reportado</option>
            </select>
        </div>
        <% } %>

        <div class="modal-footer">
            <button type="submit" class="btn btn-primary">Guardar</button>
            <button type="button" class="btn btn-primary" onclick="window.history.back();">Cancelar</button>
        </div>
    </form>

        </div>
    </div>
</main>
<%@ include file="footer.jsp" %>