<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="headera.jsp" %>
<%@ include file="nava.jsp" %>
<%@ page import="models.Bien" %>
<%@ page import="controllers.ListarBienPorCodigo" %>
<%
    // Verificar si el parámetro "codigo" está presente y no es nulo
    String codigoParameter = request.getParameter("codigo");
    int codigoBien = 0; // Valor predeterminado en caso de que el parámetro "codigo" esté ausente o sea nulo
    if (codigoParameter != null && !codigoParameter.isEmpty()) {
        // Convertir el parámetro "codigo" a un entero
        codigoBien = Integer.parseInt(codigoParameter);
    }
    // Obtener la información del bien utilizando el controlador ListarBienPorCodigo
    Bien bien = ListarBienPorCodigo.obtenerBienPorCodigo(codigoBien);
%>
<main>
    <div class="container">
        <h1>Inventario personalizado - INVIMA</h1>
        <h2>Editar bien</h2>  
    <br>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
  <form action="ActualizarBien" method="POST">
        <table id="agregarbien" class="table">
            <thead>
                <tr>
                    <th style="width: 15%;">Item</th>
                    <th style="width: 50%;">Información</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td data-label="Item">Código:</td>
                    <td data-label="Información"><input type="number" name="codigo" class="informacion" placeholder="Codigo" readonly desabled value="<%= (bien != null) ? bien.getCodigo() : "" %>"></td>
                </tr>
                <tr>
                    <td data-label="Item">Placa:</td>
                    <td data-label="Información"><input type="number" name="placa" class="informacion" placeholder="Placa" desabled readonly value="<%= (bien != null) ? bien.getPlaca() : "" %>"></td>
                </tr>
                <tr>
                <td data-label="Item">Responsable:</td>
                <td data-label="Información"><input type="text" name="usuario" class="informacion" placeholder="Responsable" required value="<%= (bien != null && bien.getUsuario() != null) ? bien.getUsuario().getUsuario() : "" %>"></td>
                </tr>
                <tr>
                    <td data-label="Item">Nombre:</td>
                    <td data-label="Información"><input type="text" name="nombre" class="informacion" placeholder="Nombre" required value="<%= (bien != null) ? bien.getNombre() : "" %>"></td>
                </tr>
                <tr>
                    <td data-label="Item">Ubicación:</td>
                    <td data-label="Información">
                    <select class="informacion" name="ubicacion" placeholder="Seleccione la ubicacion del bien" value="<%= (bien != null) ? bien.getUbicacion() : "" %>">
                    <option value="DIRECCION GENERAL">
                    DIRECCION GENERAL
                    </option>
                    <option value="DIRECCIÓN DE MEDICAMENTOS  Y PRODUCTOS BIOLÓGICOS (INCLUYE LOS 12 GRUPOS)">
                    DIRECCIÓN DE MEDICAMENTOS  Y PRODUCTOS BIOLÓGICOS (INCLUYE LOS 12 GRUPOS)
                    </option>
                    <option value="DIRECCIÓN DE ALIMENTOS Y BEBIDAS  (INCLUYE LOS 6 GRUPOS) ">
                    DIRECCIÓN DE ALIMENTOS Y BEBIDAS  (INCLUYE LOS 6 GRUPOS) 
                    </option>
                    <option value="DIRECCIÓN DE OPERACIONES SANITARIAS (INCLUYE TODOS LOS GRUPOS UBICADOS EN EL PISO 10)">
                    DIRECCIÓN DE OPERACIONES SANITARIAS (INCLUYE TODOS LOS GRUPOS UBICADOS EN EL PISO 10)
                    </option>
                    <option value="DIRECCIÓN DE RESPONSABILIDAD SANITARIA (INCLUYE LOS 6 GRUPOS)">
                    DIRECCIÓN DE RESPONSABILIDAD SANITARIA (INCLUYE LOS 6 GRUPOS)
                    </option>
                    <option value="DIRECCION DE DISPOSITIVOS MEDICOS Y OTRAS TECNOLOGIAS (INCLUYE LOS 4 GRUPOS) ">
                    DIRECCION DE DISPOSITIVOS MEDICOS Y OTRAS TECNOLOGIAS (INCLUYE LOS 4 GRUPOS) 
                    </option>
                    <option value="ATENCION AL CIUDADANO PISO (INCLUYE LOS 2 GRUPOS)">
                    ATENCION AL CIUDADANO PISO (INCLUYE LOS 2 GRUPOS)
                    </option>
                    <option value="DIRECCIÓN DE COSMETICOS, ASEO, PLAGUICIDAS Y PRODUCTOS DE HIGIENE DOMESTICA">
                    DIRECCIÓN DE COSMETICOS, ASEO, PLAGUICIDAS Y PRODUCTOS DE HIGIENE DOMESTICA
                    </option>
                    <option value="OFICINA DE ASUNTOS INTERNACIONALES (INCLUYE LOS 2 GRUPOS)"> 
                    OFICINA DE ASUNTOS INTERNACIONALES (INCLUYE LOS 2 GRUPOS)
                    </option>
                    <option value="DIRECCION GENERAL">
                    DIRECCION GENERAL
                    </option>
                    <option value="SECRETARIA GENERAL">
                    SECRETARIA GENERAL
                    </option>
                    <option value="OFICINA DE CONTROL INTERNO"> 
                    OFICINA DE CONTROL INTERNO
                    </option>
                    <option value="GRUPO DE TESORERIA">
                    GRUPO DE TESORERIA
                    </option>
                    <option value="GRUPO DE SOPORTE TECNOLOGICO">
                    GRUPO DE SOPORTE TECNOLOGICO 
                    </option>
                    <option value="OFICINA DE TECNOLOGIAS DE LA INFORMACIÓN (INCLUYE LOS 2 GRUPOS)">
                    OFICINA DE TECNOLOGIAS DE LA INFORMACIÓN (INCLUYE LOS 2 GRUPOS)
                    </option>
                    <option value="GRUPO DE GESTION ADMINISTRATIVA (INCLUYE CAFETERIAS, GUARDAS Y ZONAS COMUNES)">
                    GRUPO DE GESTION ADMINISTRATIVA (INCLUYE CAFETERIAS, GUARDAS Y ZONAS COMUNES)
                    </option>
                    <option value="OFICINA ASESORA JURÍDICA (INCLUYE LOS 5 GRUPOS)">
                    OFICINA ASESORA JURÍDICA (INCLUYE LOS 5 GRUPOS)
                    </option>
                    <option value="OFICINA ASESORA DE PLANEACION (INCLUYE LOS 2 GRUPOS Y EL ÁREA DE RIESGOS)">
                    OFICINA ASESORA DE PLANEACION (INCLUYE LOS 2 GRUPOS Y EL ÁREA DE RIESGOS)
                    </option>
                    <option value="GRUPO DE GESTIÓN CONTRACTUAL">
                    GRUPO DE GESTIÓN CONTRACTUAL
                    </option>
                    <option value="GRUPO FINANCIERO Y PRESUPUESTAL">
                    GRUPO FINANCIERO Y PRESUPUESTAL 
                    </option>
                    <option value="GRUPO DE COMUNICACIONES">
                    GRUPO DE COMUNICACIONES
                    </option>
                    <option value="GRUPO DE TALENTO HUMANO">
                    GRUPO DE TALENTO HUMANO
                    </option>
                    <option value="GRUPO UNIDAD DE REACCIÓN INMEDIATA -GURI">
                    GRUPO UNIDAD DE REACCIÓN INMEDIATA -GURI
                    </option>
                    <option value="GRUPO DE INSTRUCCION DISCIPLINARIA">
                    GRUPO DE INSTRUCCION DISCIPLINARIA  
                    </option>
                    <option value="GRUPO LABORATORIO FISICOQUIMICO DE ALIMENTOS Y BEBIDAS">
                    GRUPO LABORATORIO FISICOQUIMICO DE ALIMENTOS Y BEBIDAS
                    </option>
                    <option value="GRUPO LABORATORIO MICROBIOLOGIA DE ALIMENTOS Y BEBIDAS">
                    GRUPO LABORATORIO MICROBIOLOGIA DE ALIMENTOS Y BEBIDAS
                    </option>
                    <option value="GRUPO LABORATORIO DE FISICOQUÍMICO DE PRODUCTOS FARMACÉUTICOS Y OTRAS TECNOLOGÍAS">
                    GRUPO LABORATORIO DE FISICOQUÍMICO DE PRODUCTOS FARMACÉUTICOS Y OTRAS TECNOLOGÍAS
                    </option>
                    <option value="GRUPO DE LABORATORIO DE MICROBIOLOGÍA DE PRODUCTOS FARMACÉUTICOS Y OTRAS TECNOLOGÍAS">
                    GRUPO DE LABORATORIO DE MICROBIOLOGÍA DE PRODUCTOS FARMACÉUTICOS Y OTRAS TECNOLOGÍAS 
                    </option>
                    <option value="OFICINA DE LABORATORIOS  Y CONTROL DE CALIDAD">
                    OFICINA DE LABORATORIOS  Y CONTROL DE CALIDAD
                    </option>
                    <option value="GRUPO LABORATORIO DE PRODUCTOS BIOLOGICOS">
                    GRUPO LABORATORIO DE PRODUCTOS BIOLOGICOS
                    </option>
                    <option value="GRUPO DE GESTIÓN DOCUMENTAL Y CORRESPONDENCIA (INCLUYE DIFERENTES SEDES )">
                    GRUPO DE GESTIÓN DOCUMENTAL Y CORRESPONDENCIA (INCLUYE DIFERENTES SEDES )
                    </option>
                    <option value="GRUPO LABORATORIO FISICO-MECANICO DE DISPOSITIVOS MEDICOS Y OTRAS TECNOLOGIAS">
                    GRUPO LABORATORIO FISICO-MECANICO DE DISPOSITIVOS MEDICOS Y OTRAS TECNOLOGIAS
                    </option>
                    <option value="GRUPO LABORATORIO DE ORGANISMOS GENETICAMENTE MODIFICADOS -OGM">
                    GRUPO LABORATORIO DE ORGANISMOS GENETICAMENTE MODIFICADOS -OGM
                    </option>
                    <option value="GRUPO RED DE LABORATORIOS Y CALIDAD">
                    GRUPO RED DE LABORATORIOS Y CALIDAD
                    </option>
                    <option value="GRUPO DE TRABAJO TERRITORIAL CENTRO ORIENTE 2 (BOGOTA)">
                    GRUPO DE TRABAJO TERRITORIAL CENTRO ORIENTE 2 (BOGOTA)
                    </option>
                    <option value="ÁREA DE ALMACEN GENERAL E INVENTARIOS">
                    ÁREA DE ALMACEN GENERAL E INVENTARIOS
                    </option>
                    <option value="GRUPO DE CONTROL EN PUERTOS, AEROPUERTOS Y PASOS DE FRONTERA (AEROPUERTO BOGOTA)">
                    GRUPO DE CONTROL EN PUERTOS, AEROPUERTOS Y PASOS DE FRONTERA (AEROPUERTO BOGOTA)
                    </option>
                    <option value="GRUPO DE INSPECCIÓN VIGILANCIA Y CONTROL DE TRÁFICO POSTAL Y MENSAJERÍA EXPRESA">
                    GRUPO DE INSPECCIÓN VIGILANCIA Y CONTROL DE TRÁFICO POSTAL Y MENSAJERÍA EXPRESA
                    </option>
                    <option value="OFICINA PUERTO DE SANTA MARTA">
                    OFICINA PUERTO DE SANTA MARTA
                    </option>
                    <option value="OFICINA PASO FRONTERIZO DE PARAGUACHON (GUAJIRA)">
                    OFICINA PASO FRONTERIZO DE PARAGUACHON (GUAJIRA)
                    </option>
                    <option value="OFICINA PASO FRONTERIZO ARAUCA">
                    OFICINA PASO FRONTERIZO ARAUCA
                    </option>
                    <option value="OFICINA DE IBAGUE">
                    OFICINA DE IBAGUE
                    </option>
                    <option value="GRUPO DE TRABAJO TERRITORIAL CENTRO ORIENTE 3 - NEIVA">
                    GRUPO DE TRABAJO TERRITORIAL CENTRO ORIENTE 3 - NEIVA  
                    </option>
                    <option value="OFICINA PASO FRONTERIZO DE LETICIA">
                    OFICINA PASO FRONTERIZO DE LETICIA
                    </option>
                    <option value="OFICINA PASO FRONTERIZO DE SAN MIGUEL PUTUMAYO">
                    OFICINA PASO FRONTERIZO DE SAN MIGUEL PUTUMAYO
                    </option>
                    <option value="GRUPO DE TRABAJO TERRITORIAL COSTA CARIBE 1 - BARRANQUILLA">
                    GRUPO DE TRABAJO TERRITORIAL COSTA CARIBE 1 - BARRANQUILLA
                    </option>
                    <option value="OFICINA PUERTO DE BARRANQUILLA">
                    OFICINA PUERTO DE BARRANQUILLA
                    </option>
                    <option value="OFICINA PUERTO DE CARTAGENA">
                    OFICINA PUERTO DE CARTAGENA
                    </option>
                    <option value="GRUPO DE TRABAJO TERRITORIAL OCCIDENTE 2 - CALI">
                    GRUPO DE TRABAJO TERRITORIAL OCCIDENTE 2 - CALI
                    </option>
                    <option value="OFICINA PUERTO DE BUENAVENTURA">
                    OFICINA PUERTO DE BUENAVENTURA
                    </option>
                    <option value="GRUPO DE APOYO A NARIÑO PASTO">
                    GRUPO DE APOYO A NARIÑO PASTO 
                    </option>
                    <option value="OFICINA PASO FRONTERIZO DE  IPIALES">
                    OFICINA PASO FRONTERIZO DE  IPIALES
                    </option>
                    <option value="GRUPO DE TRABAJO TERRITORIAL OCCIDENTE 1 - MEDELLIN">
                    GRUPO DE TRABAJO TERRITORIAL OCCIDENTE 1 - MEDELLIN 
                    </option>
                    <option value="OFICINA AEROPUERTO DE RIONEGRO">
                    OFICINA AEROPUERTO DE RIONEGRO
                    </option>
                    <option value="GRUPO DE TRABAJO TERRITORIAL CENTRO ORIENTE 1 - BUCARAMANGA">
                    GRUPO DE TRABAJO TERRITORIAL CENTRO ORIENTE 1 - BUCARAMANGA 
                    </option>
                    <option value="OFICINA PASO FRONTERIZO DE CÚCUTA">
                    OFICINA PASO FRONTERIZO DE CÚCUTA
                    </option>
                    <option value="GRUPO DE TRABAJO TERRITORIAL  ORINOQUIA- VILLAVICENCIO">
                    GRUPO DE TRABAJO TERRITORIAL  ORINOQUIA- VILLAVICENCIO
                    </option>
                    <option value="GRUPO DE TRABAJO TERRITORIAL  EJE CAFETERO - ARMENIA">
                    GRUPO DE TRABAJO TERRITORIAL  EJE CAFETERO - ARMENIA
                    </option>
                    <option value="GRUPO DE TRABAJO TERRITORIAL COSTA CARIBE 2 - MONTERIA">
                    GRUPO DE TRABAJO TERRITORIAL COSTA CARIBE 2 - MONTERIA
                    </option>
                    </select>  
                    </td>
                    </tr>
                <tr>
                    <td data-label="Item">Descripción:</td>
                    <td data-label="Información"><input type="text" name="descripcion" class="informacion" placeholder="Descripción" required value="<%= (bien != null) ? bien.getDescripcion() : "" %>"></td>
                </tr>
                <tr>
                    <td data-label="Item">Valor:</td>
                    <td data-label="Información"><input type="number" name="valor" class="informacion" placeholder="Valor" required value="<%= (bien != null) ? bien.getValor() : "" %>"></td>
                </tr>
                  <tr>
                <td data-label="Item">Estado:</td>
                <td data-label="Información">
                <select class="informacion" name="estado" placeholder="Seleccione el estado del bien">
                    <option value="No reportado" <%= (bien != null && bien.getEstado().equals("No reportado")) ? "selected" : "" %>>
                        No reportado
                    </option>
                    <option value="Reportado" <%= (bien != null && bien.getEstado().equals("Reportado")) ? "selected" : "" %>>
                        Reportado
                    </option>
                </select>
                </td>
                </tr>
            </tbody>
        </table>
        <div class="button-container">
            <button class="button" value="Actualizar">Guardar</button>
            <a href="gestionbienes.jsp" class="button-second">Cancelar</a>
        </div>
    </form>
</main>
<%@ include file="footera.jsp" %>

