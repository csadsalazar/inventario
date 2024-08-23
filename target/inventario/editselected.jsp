<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nav.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Object" %>
<%@ page import="controllers.ObjectsEditMasive" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="models.Dependency" %>
<%@ page import="controllers.ListDependencies" %> 
<%
    // Obtener los IDs desde la URL
    String idsParam = request.getParameter("ids"); 
    List<Object> objects = null;
 
    if (idsParam != null && !idsParam.isEmpty()) {
        // Convertir los IDs a una lista de Long 
        String[] ids = idsParam.split(","); 
        try { 
            objects = ObjectsEditMasive.getObjects(ids); // Llamada para obtener los objetos
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al obtener los datos de los objetos.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al conectar con la base de datos.");
        }
    } else {
        request.setAttribute("error", "No se han seleccionado objetos.");
    }
%>

    <div class="container mt-3">
    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="homea.jsp">Inicio</a></li>
            <li class="breadcrumb-item"><a href="managementobjects.jsp">Almacen</a></li>
            <li class="breadcrumb-item active" aria-current="page">Editar seleccionados</li>
        </ol>
    </nav> 
    </div>
<main class="container">
      <div class="text-center">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Editar bienes</h2>   
      </div>
<form action="UpdateMasiveObjects" method="POST" class="row g-3 py-3">
    <input type="hidden" name="ids" value="<%= idsParam %>" />

    <% 
        if (objects != null && !objects.isEmpty()) { 
    %>
    <!-- Añadir un campo para el usuario -->
        <div class="col-md-4">
            <label for="user" class="form-label">Funcionario</label>
            <input type="text" class="form-control" name="user" id="user" value="">
        </div>
        <div class="col-md-4">
            <label for="name" class="form-label">Nombre:</label>
            <input type="text" class="form-control" id="name" name="name" value="" />
        </div>
        <div class="col-md-4">
            <label for="description" class="form-label">Descripción:</label>
            <input type="text" class="form-control" id="description" name="description" value="" />
        </div>
        <div class="col-md-4">
            <label for="observation" class="form-label">Observación:</label>
            <input type="text" class="form-control" id="observation" name="observation" value="" />
        </div>
        <div class="col-md-4">
        <label for="dependency" class="form-label">Dependencia</label>
        <select class="form-select" name="dependency" id="dependency" > 
            <option value="">Seleccione una dependencia</option>
            <%
            ArrayList<Dependency> dependencias = ListDependencies.getDependencies();
            for (Dependency dependencia : dependencias) {
            %>
            <option value="<%= dependencia.getPK_idDependency() %>"><%= dependencia.getDependencyname() %></option>
            <%  
            }
            %>
        </select>
        </div>
        <div class="col-md-4">
            <label for="state" class="form-label">Estado</label>
            <select class="form-select" id="state" name="state">
                <option value="">Seleccione una opción</option>
                <option value="No reportado">No reportado</option>
                <option value="En espera">En espera</option>
                <option value="Reportado">Reportado</option>
            </select>
        </div> 
        <div class="col-md-4">
            <label for="condition" class="form-label">Condición</label>
            <select class="form-select" id="condition" name="condition">
                <option value="">Seleccione una opción</option>
                <option value="Inactivo">Inactivo</option>
                <option value="Activo">Activo</option>
            </select>
        </div>
        <div>
        </div>
        <div class="col">
            <button type="submit" class="btn btn-primary">Actualizar</button>
            <a class="btn btn-primary" href="managementobjects.jsp">Cancelar</a>
        </div>
    <% 
        } else {
    %>
        <p>No hay objetos para editar o no tienes una sesión abierta.</p>
    <% 
        } 
    %>
</form>
</main>
<%@ include file="footer.jsp" %>
