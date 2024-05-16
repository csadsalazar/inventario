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
        <img src="resources/img/Logo_Invima-Te-Acompana_0 1.png" alt="Logo_Invima-Te-Acompana" height="35">
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
                  <p class="fw-normal">¡Bienvenido de nuevo!</p>
                  <h4 class="fw-bold">Inicia sesión</h4>
                </div>
              </div>
            </div>
            <form action="Login" method="POST">
              <div class="row gy-3 gy-md-4 overflow-hidden">
                <div class="col-12">
                  <label for="usuario" class="form-label">Dirección de correo electronico <span class="text-danger">*</span></label>
                  <input type="text" class="form-control" name="usuario" id="usuario" placeholder="name@example.com" required>
                </div>
                <div class="col-12">
                  <label for="contrasena" class="form-label">Contraseña <span class="text-danger">*</span></label>
                  <input type="password" class="form-control" name="contrasena" id="contrasena" required>
                </div>
                <div class="col-12">
                  <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" name="remember_me" id="remember_me">
                    <label class="form-check-label text-secondary" for="remember_me">
                      Recuerdame
                    </label>
                  </div>
                </div>
                <div class="col-12">
                  <div class="d-grid">
                    <button class="btn bsb-btn-xl btn-primary" type="submit">Iniciar sesión</button>
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
 <footer>
    <div class="mt-5 pt-5 pb-5 footer">
      <div class="container">
        <div class="row" style="font-size: 0.8rem;">
          <div class="col-lg-1"></div>
          <div class="col-lg-5 col-xs-12 text-xs">
            <img src="resources/img/Logo_Invima-Te-Acompana_Blanco 1.png" alt="Logo_Invima-Te-Acompana_Blanco 1" style="margin-block-end: 20px;">
            <p class="text-white">Sede principal: Carrera 10 #64 - 28 Bogotá, Colombia</p>
            <p class="text-white">Teléfono conmutador: (+57)(601) 7422121 </p>
            <p class="text-white">Contáctenos PQRSD</p>
            <p class="text-white">Horario de atención: Lunes a Viernes 7:30 a.m. a 3:30 p.m.</p>
            <p class="text-white">Línea anticorrupción: (+57)(601) 7458593</p>
            <p class="text-white">Notificaciones Judiciales: notificaciones_judiciales@invima.gov.co</p>
            <p class="text-white">Transparencia Invima: soytransparente@invima.gov.co</p>
            <p style="filter: invert(100%);"><img src="resources/img/icons/instagram.svg" class="mx-2"><img src="resources/img/icons/youtube.svg" class="mx-2"><img src="resources/img/icons/twitter.svg" class="mx-2"><img src="resources/img/icons/facebook.svg" class="mx-2"></p>
          </div>
          <div class="col-lg-3 col-xs-12 links text-decoration-none fw-bold text-sm">
            <h4 class="mt-lg-0 mt-sm-3 fs-3 mb-4">Enlaces de interes</h4>
              <ul class="m-0 p-0">
                <li class="mb-3"> 
                <img src="resources/img/icons/chevron-right.svg">
                <a class="text-decoration-none" href="#">Enlaces instituto</a>   
                </li>
                <li class=" mb-3"> 
                <img src="resources/img/icons/chevron-right.svg">
                <a class="text-decoration-none" href="#">Qué hacemos</a>
                </li>                
                <li class=" mb-3"> 
                <img src="resources/img/icons/chevron-right.svg">
                <a class="text-decoration-none" href="#">Atención al ciudadano</a>
                </li>                
                <li class=" mb-3"> 
                <img src="resources/img/icons/chevron-right.svg">
                <a class="text-decoration-none" href="#">Tramites y servicios</a>
                </li>
              </ul>
          </div>
          <div class="col-lg-3 col-xs-12 links text-decoration-none fw-bold text-sm">
              <ul class="m-0 p-0">
                <li class=" mb-3"> 
                <img src="resources/img/icons/chevron-right.svg">
                <a class="text-decoration-none " href="#">Normatividad</a>
                </li>
                <li class=" mb-3"> 
                <img src="resources/img/icons/chevron-right.svg">
                <a class="text-decoration-none" href="#">Paticipa</a>
                </li>                
                <li class=" mb-3"> 
                <img src="resources/img/icons/chevron-right.svg">
                <a class="text-decoration-none" href="#">Sala de prensa</a>
                </li>                
                <li class=" mb-3"> 
                <img src="resources/img/icons/chevron-right.svg">
                <a class="text-decoration-none" href="#">Sed faucibus</a>
                </li>
                <li class=" mb-3"> 
                <img src="resources/img/icons/chevron-right.svg">
                <a class="text-decoration-none" href="#">Transparencia</a>
                </li>
              </ul>
          </div>
        </div>
        <div class="row">
          <div class="col copyright">
            <p class=""></p>
            <nav class="d-flex justify-content-around align-items-center">
              <img src="resources/img/invima_blanco.png" alt="Logo-potencia-de-la-vida" height="35">
              <div class="input-wrapper d-flex align-items-center">        
              </div>            
              <img src="resources/img/logo-gov-co-blanco.png" alt="Logo_Invima-Te-Acompana" height="35">
          </nav>
          </div>
        </div>
      </div>
      </div>
      </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  </body>
</html>