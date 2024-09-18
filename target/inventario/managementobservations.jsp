<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nav.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Observation" %>
<%@ page import="models.User" %>
<%@ page import="models.Dependency" %>
<%@ page import="controllers.ListObservations" %>
    <div class="container mt-3">
    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="homea.jsp">Inicio</a></li>
            <li class="breadcrumb-item active" aria-current="page">Observaciones</li>
        </ol>
    </nav>
</div>
<main class="container">
    <div class="text-center">
      <h1>Inventario personalizado - INVIMA</h1>
        <h2>Observaciones</h1>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Funcionario</th>
            <th scope="col">Dependencia</th>
            <th scope="col">Asunto</th>
            <th scope="col">Observación</th>
        </tr>
        </thead>
        <tbody>
            <%
                ArrayList<Observation> observaciones = ListObservations.getObservations();
                for (Observation observacion : observaciones) {
            %>
            <tr>
            <td><%= observacion.getUser().getUser() %></td>
            <td><%= observacion.getUser().getPK_idDependencia().getDependencyname() %></td>
            <td><%= observacion.getAffair() %></td>
            <td><%= observacion.getInformation() %></td>
            </tr> 
            <%   
              }
            %>
        </tbody>
        </table> 
</main>
<%@ include file="footer.jsp" %>