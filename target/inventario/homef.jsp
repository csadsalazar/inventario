<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="headerf.jsp"%>
<%@include file="navf.jsp"%>
<%@include file="usuario.jsp"%>
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
                <tr>
                    <td data-label="Nombre">PC</td>
                    <td data-label="Placa">18119</td>
                    <td data-label="Codigo">198109</td>
                    <td data-label="Acciones">
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
            </tbody>
        </table>
        <div class="button-container">
            <a href="agregarobservacion.jsp" class="button">Observaciones</a>
            <a id="reporteModal" class="button">Finalizar reporte</a>
        </div>
    </div>
</main>
<%@include file="footerf.jsp"%>
