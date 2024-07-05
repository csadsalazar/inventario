<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nav.jsp" %>
<%@ page import="models.User" %>
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
                User admin = ListAdministratorsById.getAdministratorsById(codigo);
            %>
            <div class="col-md-4">
            <input type="hidden" class="form-control" name="codigo" value="<%= admin.getPK_idUser() %>"> 
            <label for="usuario" class="form-label">Usuario</label>
            <input type="text" class="form-control" name="usuario" readonly value="<%= admin.getUser() %>">
            </div>
            <div class="col-md-4"> 
            <label for="perfil" class="form-label">Perfil</label>
            <select class="form-select" id="perfil" name="perfil">
                <option value="1" <%= admin.getProfile().equals("1") ? "selected" : "" %>>Administrador</option>
                <option value="2" <%= admin.getProfile().equals("2") ? "selected" : "" %>>Usuario</option>
            </select>
            </div>
            <div>
            </div>
            <div class="col-md-3">
            <button class="btn btn-primary">Guardar</button>
            </div>
        </form>
</main>
<%@ include file="footer.jsp" %> 