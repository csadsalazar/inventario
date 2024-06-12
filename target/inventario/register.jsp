<%@ page contentType="text/html; charset=utf-8" %>
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
        HttpSession session1 = request.getSession();
        String username = (String) session1.getAttribute("username");
    %>
    <header>
    <nav class="navbar navbar-expand-lg navbar-light bg-primary">
      <div class="container">
        <img src="resources/img/logo blanco gov.co.png" alt="img gov.co" class="py-2" height="45px">
      </div>
    </nav>
    <nav class="w-100 d-flex justify-content-around align-items-center py-2">
        <img src="resources/img/Logo-potencia-de-la-vida 1.png" alt="Logo-potencia-de-la-vida" height="35">
        <div class="input-wrapper d-flex align-items-center">        
        </div>            
        <img src="resources/img/Invima-logo.jpg" alt="Logo_Invima-Te-Acompana" height="35">
    </nav>
    <nav class="navbar navbar-expand-lg navbar-light bg mb-5" id="options">
      <div class="container">
        <img src="resources/img/invima_blanco.png" alt="Logo_Invima-Te-Acompana" height="45" >
      </div>
    </nav>
</header>
<section class="p-3 p-md-4 p-xl-5">
  <div class="container">
    <div class="card border-light-subtle shadow-sm">
      <div class="row g-0">
        <div class="col-12 col-md-6">
          <div class="card-body p-3 p-md-4 p-xl-5">
            <div class="row">
              <div class="col-12">
                <div class="mb-5">
                  <img loading="lazy" src="resources/img/invima-logo.jpg"  class="mb-5" alt="invima-logo" width="90" height="50">
                  <h4 class="fw-bold">Ingresa tus datos</h4>
                  <p class="fw-normal">Por favor, ingresa tus datos reales.</p>
                </div>
              </div>
            </div>
            <form action="AddUser" method="POST">
              <div class="row gy-3 gy-md-4 overflow-hidden">
                <div class="col-12">
                  <label for="username" class="form-label">Dependencia<span class="text-danger">*</span></label>
                  <input type="text" class="form-control" name="username" id="username" placeholder="name@example.com" required>
                </div>
                <div class="col-12">
                  <label for="password" class="form-label">Cargo <span class="text-danger">*</span></label>
                  <input type="password" class="form-control" name="password" id="contrasena" required>
                </div>
                <div class="col-12">
                  <label for="password" class="form-label">Tipo de documento <span class="text-danger">*</span></label>
                  <input type="password" class="form-control" name="password" id="contrasena" required>
                </div>
                <div class="col-12">
                  <label for="password" class="form-label">Cédula <span class="text-danger">*</span></label>
                  <input type="password" class="form-control" name="password" id="contrasena" required>
                </div>
                <input type="hiden" name="username" id="username" value="<%= username %>">
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
