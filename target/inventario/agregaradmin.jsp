    <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <%@ include file="headera.jsp" %>
    <%@ include file="nava.jsp" %>
        <main>
            <div class="container">
                <h1>Inventario personalizado - INVIMA</h1>
                <h2>Agregar administrador</h2>  
                <br>
                <c:if test="${not empty error}">
                    <p style="color: red;">${error}</p>
                </c:if>
                <div class="form-container">
                <form action="AgregarAdmin" method="POST">
                    <table id="agregarbien" class="table">
                        <thead>
                            <tr>
                                <th style="width: 15%;">Item</th>
                                <th style="width: 50%;">Información</th>
                            </tr>
                        </thead> 
                        <tbody>
                            <tr>
                                <td data-label="Item">Usuario:</td>
                                <td data-label="Información"><input type="text" name="usuario" class="informacion" placeholder="Usuario" required></td>
                            </tr>
                            <tr>
                                <td data-label="Item">Estado:</td>
                                <td data-label="Información">
                                    <select class="informacion" name="estado" placeholder="Seleccione el estado del administrador" style="width: 100%">
                                        <option value="Inactivo">Inactivo</option>
                                        <option value="Activo">Activo</option>
                                    </select>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="button-container">
                        <button class="button">Enviar</button>
                        <a href="gestionbienes.jsp" class="button-second">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>
    </main>
 <%@ include file="footera.jsp" %>
