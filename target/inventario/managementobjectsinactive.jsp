<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nav.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Object" %>
<%@ page import="controllers.ListObjectsInactive" %>
<%@ page import="models.Dependency" %>
<%@ page import="controllers.ListDependencies" %>
<div class="container mt-3">
    <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="homea.jsp">Inicio</a></li>
            <li class="breadcrumb-item"><a href="managementobjects.jsp">Almacén</a></li>
            <li class="breadcrumb-item active" aria-current="page">Bienes inactivos</li>
        </ol>
    </nav> 
</div>
<main class="container">  
    <div class="text-center">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Almacén (bienes inactivos)</h2>
    </div>
    <div class="d-grid gap-2 d-md-flex justify-content-md-between">
        <div>    
        </div>
        <div>
            <button class="btn btn-primary" type="button" onclick="activeSelected()">Activar seleccionados</button>
        </div>
    </div>
    <br>
    <form id="activeForm" action="ActiveCondition" method="POST">
    <input type="hidden" id="selectedIds" name="selectedIds" />
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
                        <th class="campo" scope="col">Condicion</th>
                        <th class="campo" scope="col">Fecha Creacion</th>
                        <th class="campo" scope="col">Admin Modf</th> 
                        <th scope="col">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <% ArrayList<Object> bienes = ListObjectsInactive.getObjects();
                    for (Object bien : bienes) { %>
                    <tr>
                        <td><input type="checkbox" name="selectedIds" value="<%= bien.getCode() %>"></td>
                        <td><%= bien.getCode() %></td>
                        <td><%= bien.getPlate() %></td>
                        <td class="campo"><%= bien.getName() %></td>
                        <td class="campo"><%= bien.getUser().getName() %></td>
                        <td class="campo"><%= bien.getPK_idDependency().getDependencyname() %></td>
                        <td class="campo"><%= bien.getState() %></td>
                        <td class="campo"><%= bien.getCondition() %></td>
                        <td class="campo"><%= bien.getDate() %></td>
                        <td class="campo"><%= bien.getAdmin().getName() %></td>
                        <td>
                            <div class="acciones">
                                <a href="seeobjecta.jsp?codigo=<%= bien.getCode() %>">
                                    <img src="resources/img/icons/airplay.svg" alt="airplay">
                                </a>
                            </div>
                        </td>
                    </tr>
                    <% } %>
                </tbody> 
            </table>
        </div>
        <input type="hidden" id="selectedIds" name="selectedIds" />
    </form>

<script>
function activeSelected() {
    var form = document.getElementById("activeForm");
    var checkboxes = document.getElementsByName("selectedIds");
    var selectedIds = [];
    
    for (var checkbox of checkboxes) {
        if (checkbox.checked) {
            selectedIds.push(checkbox.value);
        }
    }
    
    if (selectedIds.length === 0) {
        // Mostrar alerta con SweetAlert
        Swal.fire({
                title: '¡Espere!',
                text: 'Por favor seleccione al menos un registro',
                icon: 'warning',
                confirmButtonColor: '#139EC8'
        });
        return; // Evitar enviar el formulario si no hay registros seleccionados
    } 
    
    // Asignar los IDs seleccionados al campo oculto
    document.getElementById("selectedIds").value = selectedIds.join(",");
    
    // Enviar el formulario
    form.submit();
}

function selectAllCheckboxes() {
    var checkboxes = document.getElementsByName("selectedIds");
    var selectAllCheckbox = document.getElementById("selectAllCheckbox");
    
    for (var checkbox of checkboxes) {
        checkbox.checked = selectAllCheckbox.checked;
    }
}
</script>
</main>
<%@ include file="footer.jsp" %>