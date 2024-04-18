<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="headerf.jsp"%>
<%@include file="navf.jsp"%>
<%@include file="usuario.jsp"%>
<%@ page import="models.Bien" %>

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
                    <td data-label="Nombre"></td>
                    <td data-label="Placa"></td>
                    <td data-label="Codigo"></td>
                    <td data-label="Acciones">
                        <div class="acciones">
                            <a id="cargarImagenes">
                            <img src="resources/img/camera.svg" alt="camera">
                            </a>&nbsp;
                            <a href="verbienf.jsp">
                            <img src="resources/img/airplay.svg" alt="airplay">
                            </a>&nbsp;
                            <a id="verificarModal">
                            <img src="resources/img/check-circle.svg" alt="check-circle">
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
 