<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <li class="breadcrumb-item"><a href="managementobjects.jsp">Almacén</a></li>
            <li class="breadcrumb-item active" aria-current="page">Editar bienes seleccionados</li>
        </ol>
    </nav>
</div>

<main class="container">
    <div class="text-center">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Editar bienes seleccionados</h2>
    </div>

    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <form class="row g-3 py-3" method="POST" action="UpdateState">
        <div class="table-responsive">
            <table class="table" id="table">
                <thead>
                    <tr>
                        <th scope="col">Código</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Estado Actual</th>
                        <th scope="col">Nuevo Estado</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="bien" items="${bienes}">
                        <tr>
                            <td>${bien.code}</td>
                            <td>${bien.name}</td>
                            <td>${bien.state}</td>
                            <td>
                                <select name="newState_${bien.code}">
                                    <option value="activo">Activo</option>
                                    <option value="inactivo">Inactivo</option>
                                    <!-- Agrega más opciones según tus necesidades -->
                                </select>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="col">
            <button class="btn btn-primary" type="submit">Guardar Cambios</button>
        </div>
    </form>
</main>

<%@ include file="footer.jsp" %>