<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nava.jsp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Bien" %>
<%@ page import="controllers.ListarBienes" %>
<main>
    <div class="container">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Bienes totales</h2>
        <table class="table">
            <thead>
                <tr>
                    <th style="width: 15%;">Codigo</th>
                    <th style="width: 10%;">Placa</th>
                    <th style="width: 10%;">Nombre</th>
                    <th style="width: 15%;">Funcionario</th>
                    <th style="width: 25%;">Dependencia</th>
                    <th style="width: 20%;">Valor</th>
                    <th style="width: 25%;">Acciones</th>
                </tr>
            </thead> 
            <tbody>
                <%
                    ArrayList<Bien> bienes = ListarBienes.obtenerBienes();
                    for (Bien bien : bienes) {
                %>
                <tr>
                    <td data-label="Codigo"><%= bien.getCodigo() %></td>
                    <td data-label="Placa"><%= bien.getPlaca() %></td>
                    <td data-label="Nombre"><%= bien.getNombre() %></td>
                    <td data-label="Funcionario"><%= bien.getUsuario().getUsuario() %></td>
                    <td data-label="Dependencia"><%= bien.getUsuario().getDependencia() %></td>
                    <td data-label="Valor"><%= bien.getValor() %>&nbsp;COP</td>
                    <td data-label="Acciones">
                        <div class="acciones">
                            <a href="verbien.jsp?codigo=<%= bien.getCodigo() %>">
                                <ion-icon name="receipt-outline">
                            </a>&nbsp;
                            <a href="editarbien.jsp?codigo=<%= bien.getCodigo() %>">
                                <ion-icon name="create-outline">
                            </a>&nbsp;
                            <a id="openModal">
                                <ion-icon name="trash-outline">
                            </a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</main>

<script>
document.addEventListener("DOMContentLoaded", function() {
    var deleteButtons = document.querySelectorAll('.delete-btn');
    var modal = document.getElementById('myModal');
    var closeModalBtn = document.getElementsByClassName('close')[0];
    var confirmDeleteBtn = document.getElementById('confirm-delete-btn');
    var codigoBien;

    deleteButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            codigoBien = button.getAttribute('data-codigo');
            document.getElementById('delete-confirm').innerHTML = "¿Está seguro que desea eliminar el bien con código " + codigoBien + "?";
            modal.style.display = 'block';
        });
    });

    // Manejar clic en el botón de cerrar modal
    closeModalBtn.addEventListener('click', function() {
        modal.style.display = 'none';
    });

    // Manejar clic en el botón de confirmar eliminación
    confirmDeleteBtn.addEventListener('click', function() {
        // Realizar una solicitud AJAX para eliminar el bien utilizando el código obtenido
        fetch('/eliminar_bien?id=' + codigoBien, {
            method: 'DELETE',
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('El bien ha sido eliminado correctamente.');
                window.location.reload(); // Recargar la página para actualizar la lista de bienes
            } else {
                alert('No se pudo eliminar el bien.');
            }
        })
        .catch(error => {
            console.error('Error al eliminar el bien:', error);
        });
    });
});
</script>

<%@ include file="footera.jsp" %>
