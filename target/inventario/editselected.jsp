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
                <% for (int i = 0; i < objetosSeleccionados.size(); i++) {
                        Object bien = objetosSeleccionados.get(i);
                    %>
                    <input type="hidden" name="selectedIds" value="<%= bien.getCode() %>">
                    <div class="mb-3">
                        <label for="usuario_<%= i %>" class="form-label">Funcionario</label>
                        <input type="text" class="form-control" id="usuario_<%= i %>" name="usuario_<%= i %>">
                    </div>
                    <div class="mb-3">
                        <label for="descripcion_<%= i %>" class="form-label">Descripción</label>
                        <textarea class="form-control" id="descripcion_<%= i %>" name="descripcion_<%= i %>"></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="observacion_<%= i %>" class="form-label">Observación</label>
                        <textarea class="form-control" id="observacion_<%= i %>" name="observacion_<%= i %>"></textarea>
                    </div>
                    <div class="col-md-4">
                        <label for="dependencia_<%= i %>" class="form-label">Ubicación</label>
                        <select class="form-select" name="dependencia_<%= i %>" id="dependencia_<%= i %>">
                            <% ArrayList<Dependency> dependencias = ListDependencies.getDependencies();
                            for (Dependency dependencia : dependencias) { %>
                                <option value="<%= dependencia.getPK_idDependency() %>"><%= dependencia.getDependencyname() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label for="estado_<%= i %>" class="form-label">Estado</label>
                        <select class="form-select" id="estado_<%= i %>" name="estado_<%= i %>">
                            <option value="Reportado">Reportado</option>
                            <option value="En espera">En espera</option>
                            <option value="No reportado">No reportado</option>
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