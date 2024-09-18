<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Dependency" %>
<%@ page import="models.Charge" %>
<%@ page import="models.DocumentType" %>
<%@ page import="controllers.ListDependencies" %>
<%@ page import="controllers.ListDocumentType" %>
<%@ page import="controllers.ListCharge" %>
<%@ page import="dto.UserResponse" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login-Invima</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="resources/css/estilos.css">
    <link rel="icon" type="image/x-icon" href="resources/img/checkinvima.png">
    <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://unpkg.com/bs-brain@2.0.4/components/logins/login-4/assets/css/login-4.css">
</head>
<body>
    <% 
        UserResponse userResponse = (UserResponse) session.getAttribute("userResponse");
        if (userResponse == null) {
    %>
        <p>Error: No se ha encontrado la información del usuario.</p>
        <a href="index.jsp">Volver al inicio</a>
    <% 
        return;
        }
        HttpSession session1 = request.getSession();
        String username = (String) session1.getAttribute("username");
    %>
    <header>
        <!-- Tu código de cabecera -->
    </header>
    <section class="p-3 p-md-4 p-xl-5">
        <div class="container">
            <div class="card border-light-subtle shadow-sm"> 
                <div class="row g-0">  
                    <div class="col-12 col-md-6">
                        <div class="card-body p-3 p-md-4 p-xl-5">
                            <div class="row">
                                <div class="col-12">
                                    <img loading="lazy" src="resources/img/invima-logo.png"  class="mb-5" alt="invima-logo" width="90" height="50">
                                    <h4 class="fw-bold">Ingresa tus datos</h4>
                                    <p class="fw-normal">Por favor, ingresa tus datos reales.</p>
                                </div>
                            </div>
                            <form action="AddUser" method="POST">
                                <div class="row gy-3 gy-md-4 overflow-hidden">
                                    <div class="col-12">
                                        <label for="nombreUsuarioCompleto" class="form-label">Nombre Completo</label>
                                        <input class="form-control" type="text" value="<%= userResponse.getNombreCompleto() %>" disabled>
                                        <input class="input" type="hidden" name="nombreUsuarioCompleto" id="nombreUsuarioCompleto" value="<%= userResponse.getNombreCompleto() %>">
                                    </div> 
                                    <div class="col-12">
                                        <label for="nombreUsuario" class="form-label">Usuario</label>
                                        <input class="form-control" type="text" value="<%= userResponse.getNombreUsuario() %>" disabled>
                                        <input class="input" type="hidden" name="nombreUsuario" id="nombreUsuario" value="<%= userResponse.getNombreUsuario() %>">
                                    </div> 
                                    <div class="col-12">
                                        <label for="correoUsuario" class="form-label">Correo</label>                  
                                        <input class="form-control" type="text" value="<%= userResponse.getCorreo() %>" disabled>
                                        <input class="input" type="hidden" name="correoUsuario" id="correoUsuario" value="<%= userResponse.getCorreo() %>">
                                    </div> 
                                    <div class="col-12">
                                        <label for="dependencia" class="form-label">Dependencia</label>
                                        <select class="form-select" name="dependencia" id="dependencia" required>
                                            <%
                                                ArrayList<Dependency> dependencias = ListDependencies.getDependencies();
                                                for (Dependency dependencia : dependencias) {
                                            %>
                                                <option value="<%= dependencia.getPK_idDependency() %>"><%= dependencia.getDependencyname() %></option>
                                                <%  
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="col-12">
                                        <label for="cargo" class="form-label">Cargo</label>
                                        <select class="form-select" name="cargo" id="cargo" required>
                                            <%
                                                ArrayList<Charge> charges = ListCharge.getCharges();
                                                for (Charge charge : charges) {
                                            %>
                                                <option value="<%= charge.getPK_idCharge() %>"><%= charge.getName() %></option>
                                                <%  
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="col-12">
                                        <label for="tipodocumento" class="form-label">Tipo de documento</label>
                                        <select class="form-select" name="tipodocumento" id="tipodocumento" required>
                                            <%
                                                ArrayList<DocumentType> documents = ListDocumentType.getDocumentType();
                                                for (DocumentType document : documents) {
                                            %> 
                                                <option value="<%= document.getPK_idChargeType() %>"><%= document.getName() %></option>
                                                <%  
                                                }
                                            %> 
                                        </select>
                                    </div>
                                    <div class="col-12">
                                        <label for="cedula" class="form-label">Cédula <span class="text-danger">*</span></label>
                                        <input type="number" class="form-control" name="cedula" id="cedula" required>
                                    </div>
                                    <input type="hidden" name="username" id="username" value="<%= username %>">
                                    <div class="col-12">
                                        <div class="d-grid">
                                            <button class="btn bsb-btn-xl btn-primary" type="submit">Finalizar registro</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-12 col-md-6">
                        <img class="img-fluid rounded-start w-100 h-100 object-fit-cover" loading="lazy" src="resources/img/img-login.png" alt="BootstrapBrain Logo">
                    </div>
                </div>
            </div>
        </div>
    </section>
    <%@include file="footer.jsp"%>