<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nav.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Object" %>
<%@ page import="controllers.ListObjects" %>
<div class="container mt-3">
    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="homea.jsp">Inicio</a></li>
            <li class="breadcrumb-item active" aria-current="page">Almacen</li>
        </ol>
    </nav>
</div>
<main class="container">
    <div class="text-center">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Almacen</h2>
    </div>
    <div class="d-grid gap-2 d-md-flex justify-content-md-between">
        <div>
            <button class="btn btn-primary" type="button" onclick="eliminarSeleccionados()">Eliminar seleccionados</button>
            <button class="btn btn-primary" type="button" onclick="deleteAllObjects()">Vaciar almacen</button>
        </div>
        <div>
            <a class="btn btn-primary" type="button" href="addobject.jsp">Agregar</a>
            <a class="btn btn-primary" type="button" href="uploadfile.jsp">Subir almacen</a>
        </div>
    </div>
    <br>
    <form id="deleteForm" action="DeleteObjectsServlet" method="post">
        <table class="table" id="table">
            <thead>
                <tr>
                    <th scope="col"><input type="checkbox" id="selectAllCheckbox" onclick="selectAllCheckboxes()"></th>
                    <th scope="col">Codigo</th>
                    <th scope="col">Placa</th>
                    <th class="campo" scope="col">Nombre</th>
                    <th class="campo" scope="col">Funcionario</th>
                    <th class="campo" scope="col">Dependencia</th>
                    <th class="campo" scope="col">Estado</th>
                    <th class="campo" scope="col">Fecha Creacion</th>
                    <th scope="col">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                ArrayList<Object> bienes = ListObjects.getObjects();
                for (Object bien : bienes) {
                %>
                <tr>
                    <td><input type="checkbox" name="selectedObjects" value="<%= bien.getCodigo() %>"></td>
                    <td><%= bien.getCodigo() %></td>
                    <td><%= bien.getPlaca() %></td>
                    <td class="campo"><%= bien.getNombre() %></td>
                    <td class="campo"><%= bien.getUsuario().getUsuario() %></td>
                    <td class="campo"><%= bien.getUsuario().getDependencia() %></td>
                    <td class="campo"><%= bien.getEstado() %></td>
                    <td class="campo"><%= bien.getFecha() %></td>
                    <td>
                        <div class="acciones">
                            <a href="seeobjecta.jsp?codigo=<%= bien.getCodigo() %>">
                                <img src="resources/img/icons/airplay.svg" alt="airplay">
                            </a>&nbsp;&nbsp;&nbsp;
                            <a href="editobject.jsp?codigo=<%= bien.getCodigo() %>">
                                <img src="resources/img/icons/edit.svg" alt="edit">
                            </a>&nbsp;&nbsp;&nbsp;
                        </div>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </form>
    <!-- Ajusta el botón de editar para que abra la ventana modal -->
<button class="btn btn-primary" type="button" onclick="editarObjetos()">Editar seleccionados</button>

<!-- Ventana modal para editar objetos -->
<div class="modal fade" id="editarObjetosModal" tabindex="-1" aria-labelledby="editarObjetosModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editarObjetosModalLabel">Editar Objetos</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
         <form id="editarObjetosForm">
    <input type="hidden" id="objetosSeleccionados">
    
    <!-- Campos para editar -->
    <div class="mb-3">
        <label for="nombre" class="form-label">Nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre">
    </div>
    <div class="mb-3">
        <label for="descripcion" class="form-label">Descripción</label>
        <textarea class="form-control" id="descripcion" name="descripcion"></textarea>
    </div>
    <div class="mb-3">
        <label for="valor" class="form-label">Valor</label>
        <input type="number" class="form-control" id="valor" name="valor">
    </div>
    <div class="mb-3">
        <label for="estado" class="form-label">Estado</label>
        <select class="form-select" id="estado" name="estado">
            <option value="Activo">Activo</option>
            <option value="Espera">Espera</option>
            <option value="Inactivo">Inactivo</option>
        </select>
    </div>
                <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-primary" onclick="guardarEdicion()">Guardar</button>
            </div>
</form>
            </div>
        </div>
    </div>
</div>
</main>

<script>
function editarObjetos() {
    var checkboxes = document.getElementsByName('selectedObjects');
    var objetosSeleccionados = [];
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            objetosSeleccionados.push(checkboxes[i].value);
        }
    }
    if (objetosSeleccionados.length > 0) {
        document.getElementById("objetosSeleccionados").value = JSON.stringify(objetosSeleccionados);
        var modal = new bootstrap.Modal(document.getElementById('editarObjetosModal'));
        modal.show();
    } else {
        alert("Por favor seleccione al menos un objeto para editar.");
    }
}

function guardarEdicion() {
    var form = document.getElementById("editarObjetosForm");
    var formData = new FormData(form);
    var objetosSeleccionados = JSON.parse(document.getElementById("objetosSeleccionados").value);
    
    // Agrega los objetos seleccionados al formulario
    for (var i = 0; i < objetosSeleccionados.length; i++) {
        var input = document.createElement("input");
        input.type = "hidden";
        input.name = "selectedObjects";
        input.value = objetosSeleccionados[i];
        form.appendChild(input);
    }

    // Enviar los datos al servlet utilizando AJAX
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "EditObjects", true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Aquí puedes manejar la respuesta del servidor si es necesario
            alert("Los objetos se han editado correctamente.");
            // Recargar la página o realizar alguna otra acción después de la edición
            window.location.reload();
        }
    };
    xhr.send(formData);
    
    var modal = new bootstrap.Modal(document.getElementById('editarObjetosModal'));
    modal.hide();
}

</script>
<%@ include file="footer.jsp" %>