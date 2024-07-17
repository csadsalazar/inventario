<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nav.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Object" %>
<%@ page import="controllers.ListObjects" %>
<%@ page import="models.Dependency" %>
<%@ page import="controllers.ListDependencies" %>
<div class="container mt-3">
    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="homea.jsp">Inicio</a></li>
            <li class="breadcrumb-item active" aria-current="page">Edición Masiva</li>
        </ol>
    </nav>
</div>
<main class="container"> 
    <h1 class="text-center">Edición Masiva de Registros</h1>
    
    <%-- Aquí se mostrará la información de los registros seleccionados --%>
    <% ArrayList<Object> bienes = ListObjects.getObjects(); %>
    <% for (Object bien : bienes) { %>
        <%-- Verificar si este objeto está en los IDs seleccionados --%>
        <% boolean isSelected = isSelected(request, bien.getCode()); %>
        <% if (isSelected) { %>
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">Código: <%= bien.getCode() %></h5>
                    <p class="card-text">Nombre: <%= bien.getName() %></p>
                    <p class="card-text">Placa: <%= bien.getPlate() %></p>
                    <p class="card-text">Funcionario: <%= bien.getUser().getName() %></p>
                    <p class="card-text">Dependencia: <%= bien.getPK_idDependency().getDependencyname() %></p>
                    <p class="card-text">Estado Actual: <%= bien.getState() %></p>
                    
                    <%-- Formulario para editar campos específicos del objeto --%>
                    <form action="EditMasive" method="POST">
                        <input type="hidden" name="codigo" value="<%= bien.getCode() %>">
                        <div class="mb-3">
                            <label for="descripcion_<%= bien.getCode() %>" class="form-label">Descripción</label>
                            <input type="text" class="form-control" id="descripcion_<%= bien.getCode() %>" name="descripcion_<%= bien.getCode() %>" value="">
                        </div>
                        <div class="mb-3">
                            <label for="usuario_<%= bien.getCode() %>" class="form-label">Usuario</label>
                            <input type="text" class="form-control" id="usuario_<%= bien.getCode() %>" name="usuario_<%= bien.getCode() %>" value="">
                        </div>
                        <div class="mb-3">
                            <label for="dependencia_<%= bien.getCode() %>" class="form-label">Dependencia</label>
                            <select class="form-select" id="dependencia_<%= bien.getCode() %>" name="dependencia_<%= bien.getCode() %>">
                                <% ArrayList<Dependency> dependencies = ListDependencies.getDependencies(); %>
                                <% for (Dependency dependency : dependencies) { %>
                                    <option value="<%= dependency.getPK_idDependency() %>"><%= dependency.getDependencyname() %></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="estado_<%= bien.getCode() %>" class="form-label">Estado</label>
                            <input type="text" class="form-control" id="estado_<%= bien.getCode() %>" name="estado_<%= bien.getCode() %>" value="">
                        </div>
                        <div class="mb-3">
                            <label for="observacion_<%= bien.getCode() %>" class="form-label">Observación</label>
                            <textarea class="form-control" id="observacion_<%= bien.getCode() %>" name="observacion_<%= bien.getCode() %>"></textarea>
                        </div>
                        <input type="hidden" name="original_estado_<%= bien.getCode() %>" value="<%= bien.getState() %>">
                        <button type="submit" class="btn btn-primary">Actualizar</button>
                    </form>
                </div>
            </div>
        <% } %>
    <% } %>
</main>
<%@ include file="footer.jsp" %>
