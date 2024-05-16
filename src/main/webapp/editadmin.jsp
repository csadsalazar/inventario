<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nav.jsp" %>
<%@ page import="models.Admin" %>
<%@ page import="controllers.ListAdministratorsById" %>
   <div class="container mt-3">
    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="homea.jsp">Inicio</a></li>
            <li class="breadcrumb-item"><a href="managementobjects.jsp">Almacen</a></li>
            <li class="breadcrumb-item active" aria-current="page">Editar administrador</li>
        </ol>
    </nav>
    </div>
     <main class="container">
      <div class="text-center">
      <h1>Inventario personalizado - INVIMA</h1>
          <h2>Editar administrador</h1>
    </div>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p> 
    </c:if>
           <form class="row g-3" method="POST" action="EditAdmin">
            <% 
                // Obtener el código del administrador de la solicitud
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                // Obtener el administrador por su código
                Admin admin = ListAdministratorsById.getAdministratorsById(codigo);
            %>
            <div class="col-md-4">
            <label for="usuario" class="form-label">Usuario</label>
            <input type="text" class="form-control" name="codigo" readonly value="<%= admin.getPK_idAdministrador() %>">
            </div>
            <div class="col-md-4">
            <label for="estado" class="form-label">Estado</label>
            <select class="form-select" id="estado" name="estado">
                <option value="Inactivo" <%= admin.getEstado().equals("Inactivo") ? "selected" : "" %>>Inactivo</option>
                <option value="Activo" <%= admin.getEstado().equals("Activo") ? "selected" : "" %>>Activo</option>
            </select>
            </div>
            <div>
            </div>
            <div class="col-md-3">
            <button class="btn btn-primary">Guardar</button>
            <button class="btn btn-secondary" type="submit">Cancelar</button>
            </div>
        </form>
</main>
<%@ include file="footera.jsp" %> 