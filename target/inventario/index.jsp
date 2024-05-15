<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="icon" type="image/x-icon" href="resources/img/checkinvima.png">
    <title>Inicio de sesi&oacute;n - Invima</title>
    <link rel="stylesheet" href="resources/css/styles.css"/>
  </head>
  <body>
    <main>
      <div class="box">
        <div class="inner-box">
          <div class="forms-wrap">
              <form action="Login" autocomplete="off" class="sign-in-form" method="POST">
              <div class="logo">
                <img src="resources/img/logo.png" alt="easyclass"/>
              </div>

              <div class="heading">
                <h2>Bienvenidos!</h2>
              </div>

              <div class="actual-form">
                <div class="input-wrap">
                  <input
                    type="text"
                    minlength="4"
                    class="input-field"
                    autocomplete="off"
                    name="inputUserName"
                    required
                  />
                  <label>Usuario</label>
                </div>

                <div class="input-wrap">
                  <input
                    type="password"
                    minlength="4"
                    class="input-field"
                    autocomplete="off"
                    name="inputUserPassword"
                    required
                  />
                  <label>Contrase&ntilde;a</label>
                </div>
                <button class="sign-btn" name="enviar" value="iniciar">Enviar</button>
              </div>
            </form>
          </div>

          <div class="carousel">
            <div class="images-wrapper">
              <img src="#" class="image img-1 show" alt=""/>
            </div>
          </div>
        </div>
      </div>
    </main>
    <script src="resources/js/index.js"></script>
  </body>
</html>