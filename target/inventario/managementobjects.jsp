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
                    <td class="campo"><%= bien.getDate() %></td>
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
    </div>
    <!-- Botón para editar seleccionados -->
    <button class="btn btn-primary" type="button" onclick="editarObjetos()">Editar seleccionados</button>
    </form>
    </main>
    
<script>
    function editarObjetos() {
        var checkboxes = document.getElementsByName('selectedIds');
        var selectedIds = [];
        for (var checkbox of checkboxes) {
            if (checkbox.checked) {
                selectedIds.push(checkbox.value);
            }
        }
        // Si no se seleccionó ningún objeto, mostrar un mensaje o manejar el caso según sea necesario
        if (selectedIds.length === 0) {
            alert("Por favor, selecciona al menos un objeto para editar.");
            return;
        }

        // Enviar los IDs seleccionados al servlet usando POST
        var form = document.getElementById('editForm');
        var selectedIdsInput = document.createElement('input');
        selectedIdsInput.setAttribute('type', 'hidden');
        selectedIdsInput.setAttribute('name', 'selectedIds');
        selectedIdsInput.setAttribute('value', selectedIds.join(','));
        form.appendChild(selectedIdsInput);

        // Mostrar los IDs seleccionados en la consola antes de enviar el formulario
        console.log("IDs seleccionados:", selectedIds);

        // Enviar el formulario
        form.submit();
    }
</script>


<%@ include file="footer.jsp" %>