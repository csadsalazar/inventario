<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="headera.jsp"%>
<%@include file="nava.jsp"%>
<main>
    <div class="container">
            <h1>Inventario personalizado - INVIMA</h1>
        <table class="table">
            <h2>Observaciones</h2>
            <thead>
                <tr>
                    <th style="width: 10    %;">Funcionario</th>
                    <th style="width: 15%;">Dependencia</th>
                    <th style="width: 20%;">Asunto</th>
                    <th style="width: 25%;">Observacion</th> 
                   
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td data-label="Funcionario">Luis</td>
                    <td data-label="Funcionario">Medicamentos</td>
                    <td data-label="Asunto">Cosa 1</td>
                    <td data-label="Observacion">Lorem ipsum dolor sit amet consectetur adipisicing elit. Nam recusandae numquam at eos blanditiis deleniti, necessitatibus commodi illo repellendus autem laborum molestias vitae consectetur voluptatum quae possimus eius doloremque provident!</td>
                </tr>
            </tbody>
        </table>
    </div>
</main>
<%@include file="footera.jsp"%>