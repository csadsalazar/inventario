<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headerf.jsp"%>
<%@ page import="java.util.List" %>
 <div class="container mt-3">
    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="homea.jsp">Inicio</a></li>
            <li class="breadcrumb-item active" aria-current="page">Agregar observación</li>
        </ol>
    </nav>
    </div>
     <main class="container">
      <div class="text-center">
      <h1>Inventario personalizado - INVIMA</h1>
          <h2>Agregar observación</h1>
      </div>
    <form class="row g-3 needs-validation py-3" method="POST" action="AddObservations">
      <div class="col-md-4">
          <label for="asunto" class="form-label">Asunto</label>
          <input type="text" class="form-control" name="asunto" id="asunto" required>
      </div>
      <div class="col-md-4">
          <label for="informacion" class="form-label">Información</label>
          <textarea class="form-control" name="informacion" id="informacion" rows="3"></textarea>
      </div>
      <div>
      </div>
      <div class="col">
          <button class="btn btn-primary">Enviar</button>
      </div>
    </form>
  </main>
  <%@ include file="footer.jsp"%>