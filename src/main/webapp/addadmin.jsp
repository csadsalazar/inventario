<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nav.jsp" %>
    <div class="container mt-3">
    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="homea.jsp">Inicio</a></li>
            <li class="breadcrumb-item"><a href="managementobjects.jsp">Administradores</a></li>
            <li class="breadcrumb-item active" aria-current="page">Cargar almacen</li>
        </ol>
    </nav>
    </div>
     <main class="container">
      <div class="text-center">
      <h1>Inventario personalizado - INVIMA</h1>
          <h2>Agregar administrador</h1>
      </div>
        <form class="row g-3 needs-validation py-3" method="POST" action="AddAdmin">
            <div class="col-md-4">
                <label for="usuario" class="form-label">Usuario</label>
                <input type="text" class="form-control" name="usuario" id="usuario" required>
            </div>
            <div class="col-md-4">
                <label for="estado" class="form-label">Estado</label>
                <select class="form-select" id="estado" name="estado">
                    <option value="Inactivo">Inactivo</option>
                    <option value="Activo">Activo</option>
                </select>
            </div>
        <div>
        </div>
        <div class="col-md-4">
        <button class="btn btn-primary">Enviar</button>
        <a class="btn btn-secondary" type="submit" href="managementadmins.jsp">Cancelar</a>
        </div>
        </form>
    </main>
 <%@ include file="footera.jsp" %>