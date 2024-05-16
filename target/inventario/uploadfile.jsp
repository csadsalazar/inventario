<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nav.jsp" %>
<div class="container mt-3">
    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="homea.jsp">Inicio</a></li>
            <li class="breadcrumb-item"><a href="managementobjects.jsp">Gestión bienes</a></li>
            <li class="breadcrumb-item active" aria-current="page">Cargar almacen</li>
        </ol>
    </nav>
</div>
<main class="container">
    <div class="text-center">
      <h1>Inventario personalizado - INVIMA</h1>
        <h2 class="mb-4">Cargar almacen</h2>
    </div>
    <form class="row g-3 needs-validation py-3" action="UploadServlet" method="POST" enctype="multipart/form-data">
        <div class="col-md-6">
            <input class="form-control" type="file" id="file" name="file" required>
        </div>
        <div class="col-md-4">
        <button class="btn btn-primary" id="btm">Enviar</button>
        </div>
    </form>
</main>
<%@ include file="footera.jsp" %>


