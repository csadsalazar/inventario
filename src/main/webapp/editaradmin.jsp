<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nava.jsp" %>
<%@ page import="models.Administrador" %>
<%@ page import="controllers.ListarAdministradorPorCodigo" %>
<h2>Editar administrador</h2>  
    </div>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p> 
    </c:if>
           <form class="row g-3 needs-validation" method="POST" action="EditarAdministrador">
            <% 
                // Obtener el código del administrador de la solicitud
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                // Obtener el administrador por su código
                Administrador admin = ListarAdministradorPorCodigo.obtenerAdministradorPorCodigo(codigo);
            %>
            <div class="col-md-4">
            <label for="usuario" class="form-label">Usuario</label>
            <input type="hidden" class="form-control" name="codigo" value="<%= admin.getPK_idAdministrador() %>">
            </div>
            <div class="col-md-4">
            <label for="estado" class="form-label">Estado</label>
            <select class="form-select" id="estado" name="estado">
                <option value="Inactivo" <%= admin.getEstado().equals("Inactivo") ? "selected" : "" %>>Inactivo</option>
                <option value="Activo" <%= admin.getEstado().equals("Activo") ? "selected" : "" %>>Activo</option>
            </select>
            </div>
            <div class="col-md-3">
            <button class="btn btn-primary" type="submit">Guardar</button>
            <button class="btn btn-primary" type="submit">Cancelar</button>
            </div>
        </form>
    </div>
</main>
<%@ include file="footera.jsp" %> 