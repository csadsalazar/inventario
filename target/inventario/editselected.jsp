<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nav.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Object" %>
<%@ page import="controllers.ListObjectsEdit" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Dependency" %>
<%@ page import="controllers.ListDependencies" %>
<%@ page import="java.sql.SQLException" %>

<%
    // Obtener los IDs desde la URL
    String idsParam = request.getParameter("ids"); 
    List<Object> objects = null;
 
    if (idsParam != null && !idsParam.isEmpty()) {
        // Convertir los IDs a una lista de Long
        String[] ids = idsParam.split(",");
        try { 
            objects = ListObjectsEdit.getObjects(ids); // Llamada para obtener los objetos
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

<main class="container">
      <div class="text-center">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Editar bienes</h2>   
      </div>
    <h1>Editar Objetos Seleccionados</h1>
    <form action="UpdateObjects" method="POST" class="row g-3 py-3">
        <input type="hidden" name="ids" value="<%= idsParam %>" />

        <% 
            if (objects != null && !objects.isEmpty()) {
        %>
            <div class="col-md-4">
                <label for="usuario" class="form-label">Funcionario</label>
                <input type="text" class="form-control" name="usuario" id="usuario" required>
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
                <label for="dependencia" class="form-label">Dependencia</label>
                <select class="form-select" name="dependencia" id="dependencia" >
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
                <label for="observacion" class="form-label">Observación Administrador</label>
                <textarea class="form-control" name="observacion" id="observacion" rows="1"></textarea>
            </div>
            <div class="col-md-4">
                <label for="estado" class="form-label">Estado</label>
                <select class="form-select" id="estado" name="estado">
                    <option value="No reportado">No reportado</option>
                    <option value="No reportado">En espera</option>
                    <option value="Reportado">Reportado</option>
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
            <p>No hay objetos para editar.</p>
        <% 
            } 
        %>
    </form>
</main>
<%@ include file="footer.jsp" %>
