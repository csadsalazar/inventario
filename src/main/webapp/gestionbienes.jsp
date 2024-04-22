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
                    <!-- <th style="width: 20%;">Valor</th> -->
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
                  <!--  <td data-label="Valor"><%= bien.getValor() %>&nbsp;COP</td> -->
                    <td data-label="Acciones">
                        <div class="acciones">
                            <a href="verbien.jsp?codigo=<%= bien.getCodigo() %>">
                            <img src="resources/img/airplay.svg" alt="airplay">
                            </a>&nbsp;&nbsp;&nbsp;
                            <a href="editarbien.jsp?codigo=<%= bien.getCodigo() %>">
                            <img src="resources/img/edit.svg" alt="edit">
                            </a>&nbsp;&nbsp;&nbsp; 
                            <a onclick="action('<%= bien.getCodigo() %>')">
                            <img src="resources/img/trash.svg" alt="trash" >
                            </a>
                        </div>  
                    </td>
                </tr>
                <%  
                    }
                %>
            </tbody>
        </table>
    </div>
</main>
<%@ include file="footera.jsp" %>
