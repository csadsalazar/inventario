<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="headerf.jsp"%>
<%@ include file="navf.jsp"%>
<%@ include file="usuario.jsp"%>
        <div class="form-container">
            <div class="form-group">
                <label for="asunto">Asunto:</label>
                <input id="asunto" type="text" name="asunto" required>
            </div>
            <div class="form-group">
                <label for="information">Información:</label>
                <textarea id="information" name="information" rows="5" cols="30" required></textarea>
            </div>
            <div class="button-container">
                <button class="button">Enviar</button>
                <a href="homef.jsp" class="button">Cancelar</a>
            </div>
        </div>
    </div>
</main>
<%@ include file="footerf.jsp"%>
