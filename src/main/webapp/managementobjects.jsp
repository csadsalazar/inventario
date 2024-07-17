<%@ page contentType="text/html; charset=utf-8" %>
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
            <li class="breadcrumb-item active" aria-current="page">Almacén</li>
        </ol>
    </nav>
</div>
<main class="container">  
    <div class="text-center">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Almacén</h2>
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
    <form id="editForm" action="EditMasive" method="POST">
        <div class="table-responsive">
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
                        <th class="campo" scope="col">Admin Modf</th>
                        <th scope="col">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <% ArrayList<Object> bienes = ListObjects.getObjects();
                    for (Object bien : bienes) { %>
                    <tr>
                        <td><input type="checkbox" name="selectedIds" value="<%= bien.getCode() %>"></td>
                        <td><%= bien.getCode() %></td>
                        <td><%= bien.getPlate() %></td>
                        <td class="campo"><%= bien.getName() %></td>
                        <td class="campo"><%= bien.getUser().getName() %></td>
                        <td class="campo"><%= bien.getPK_idDependency().getDependencyname() %></td>
                        <td class="campo"><%= bien.getState() %></td>
                        <td class="campo"><%= bien.getDate() %></td>
                        <td class="campo"><%= bien.getAdmin() %></td>
                        <td>
                            <div class="acciones">
                                <a href="seeobjecta.jsp?codigo=<%= bien.getCode() %>">
                                    <img src="resources/img/icons/airplay.svg" alt="airplay">
                                </a>&nbsp;&nbsp;&nbsp;
                                <a href="editobject.jsp?codigo=<%= bien.getCode() %>">
                                    <img src="resources/img/icons/edit.svg" alt="edit">
                                </a>&nbsp;&nbsp;&nbsp;
                            </div>
                        </td>
                    </tr>
                    <% } %>
                </tbody> 
            </table>
                <input type="hidden" id="selectedIds" name="selectedIds" />
        </div>
            <div>
                <button class="btn btn-primary" type="button" onclick="editarSeleccionados()">Editar seleccionados</button>
            </div>
    </form>

<script>
    function editarSeleccionados() {
        // Obtener todos los checkboxes seleccionados
        var checkboxes = document.querySelectorAll('input[name="selectedIds"]:checked');
        var selectedIds = [];
        
        checkboxes.forEach(function(checkbox) {
            selectedIds.push(checkbox.value);
        });

        // Asignar los IDs seleccionados al campo hidden en el formulario
        document.getElementById('selectedIds').value = selectedIds.join(',');

        // Enviar el formulario para editar los bienes seleccionados
        document.getElementById('editForm').submit();
    }

    function selectAllCheckboxes() {
        var checkboxes = document.querySelectorAll('input[name="selectedIds"]');
        checkboxes.forEach(function(checkbox) {
            checkbox.checked = document.getElementById('selectAllCheckbox').checked;
        });
    }
</script>


</main>
<%@ include file="footer.jsp" %>