    <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <%@ include file="headera.jsp" %>
    <%@ include file="nava.jsp" %>
    <c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
    </c:if>
        <h2>Agregar Administrador</h2>  
        </div>
        <form class="row g-3 needs-validation" method="POST" action="AgregarAdmin">
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
        <button class="btn btn-primary" type="submit">Enviar</button>
        <button class="btn btn-primary" type="submit">Cancelar</button>
        </div>
        </form>
    </main>
 <%@ include file="footera.jsp" %>