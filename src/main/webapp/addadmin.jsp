<%@ include file="headera.jsp" %>
<%@ include file="nav.jsp" %>

<div class="container mt-3">
    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="homea.jsp">Inicio</a></li>
            <li class="breadcrumb-item"><a href="managementadmins.jsp">Administradores</a></li>
            <li class="breadcrumb-item active" aria-current="page">Cargar almacen</li>
        </ol>
    </nav> 
</div>
<main class="container">
    <div class="text-center">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Agregar administrador</h2>
    </div>

    <!-- Mostrar error si está disponible -->
    <c:if test="${not empty error}">
        <p style="color: red;" class="py-3">${error}</p> 
    </c:if>

    <!-- Formulario para agregar administrador -->
    <form class="row g-3 needs-validation py-3" action="AddAdmin" method="POST">
        <div class="col-md-4">
            <label for="username" class="form-label">Usuario</label>
            <input type="text" class="form-control" name="username" id="username" required>
        </div>
        <div class="col-md-4">
            <label for="perfil" class="form-label">Perfil</label>
            <select class="form-select" id="perfil" name="perfil">
                <option value="1">Administrador</option>
            </select>
        </div>
        <div class="col-md-4">
            <button class="btn btn-primary" type="submit">Agregar</button>
        </div>
    </form>
</main>

<%@ include file="footer.jsp" %>
