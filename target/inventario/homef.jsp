<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="headerf.jsp"%>
<%@include file="navf.jsp"%>
<%@include file="usuario.jsp"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Bien" %>
<%@ page import="controllers.ListarBienesUsuario" %>

        <table class="table">
            <h2>Bienes del usuario</h2>
            <br>
            <thead>
                <tr>
                    <th style="width: 15%;">Nombre</th> 
                    <th style="width: 15%;">Placa</th>
                    <th style="width: 15%;">Codigo</th> 
                    <th style="width: 25%;">Acciones</th> 
                </tr>
            </thead>
            <tbody>  

            <%
                    ArrayList<Bien> bienes = ListarBienesUsuario.obtenerBienUsuario();
                    for (Bien bien : bienes) {
            %>

                <tr>
                    <td data-label="Nombre"><%= bien.getNombre() %></td>
                    <td data-label="Placa"><%= bien.getPlaca() %></td>
                    <td data-label="Codigo"><%= bien.getCodigo() %></td>
                    <td data-label="Acciones">
                    <%= bien.getUsuario().getUsuario() %>
                        <div class="acciones">
                            <a id="cargarImagenes">
                                <ion-icon name="share-outline">
                            </a>&nbsp;
                            <a href="verbienf.jsp">
                                <ion-icon name="receipt-outline">
                            </a>&nbsp;
                            <a id="verificarModal">
                                <ion-icon name="checkmark-circle-outline">
                            </a>
                        </div>   
                    </td>
                </tr>
                <%  
                    }
                %>
            </tbody>
        </table>
        <div class="button-container">
            <a href="agregarobservacion.jsp" class="button">Observaciones</a>
            <a id="reporteModal" class="button">Finalizar reporte</a>
        </div>
    </div>
</main>
<%@include file="footerf.jsp"%>
