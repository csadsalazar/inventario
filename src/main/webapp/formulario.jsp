<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modal Ejemplo</title>
    <link rel="stylesheet" href="resources/css/formulario.css">
</head>
<body>
    <form action="#" id="frmvalidacion" name="frmvalidacion" novalidate>
        <h1>FORMULARIO REGISTRO</h1>
        <label for="Tidoc">Tipo Documento</label>
        <select name="Tidoc" id="Tidoc">
            <option value="1">Cedula de Ciudadania</option>
            <option value="2">Tarjeta de Identidad</option>
            <option value="3">Pasaporte de Extranjeria</option>
        </select>
        <div id="Nodoc">
        <label for="Nodoc">No. Documento</label>
        <input type="number" id="Nodoc" name="Nodoc" placeholder="Numero Documento" class="">
        <div class="feedback"></div>
        </div>
        <div id="Nom">
        <label for="Nom">Nombre</label>
        <input type="text" id="Nom" name="Nom" placeholder="Nombre">
        <div class="feedback"></div>
        </div>
        <div id="Apl">
        <label for="Apl">Apellido</label>
        <input type="text" id="Apl" name="Apl" placeholder="Apellido">
        <div class="feedback"></div>
        </div>
        <div id="FeNa">
        <label for="FeNa">Fecha Nacimiento</label>
        <input type="date" id="FeNa" name="FeNa">
        <div class="feedback"></div>
        </div>
        <div id="Coel">
        <label for="Coel">Correo electrónico</label>
        <input type="email" id="Coel" name="Coel" placeholder="Correo electrónico">
        <div class="feedback"></div>
        </div>
        <div id="Pass">
        <label for="Pass">Password</label>
        <input type="password" id="Pass" name="Pass" placeholder="Contraseña">
        <div class="feedback"></div>
        </div>
        <div id="CoPass">
        <label for="CoPass">Comprobar password</label>
        <input type="password" id="CoPass" name="CoPass" placeholder="Confirmacion contraseña">
        <div class="feedback"></div>
        </div>
        <label>Aceptar Términos de uso</label> 
        <input type="checkbox" name="Te" id="Te">
        <button type="submit" >Registrarse</button>
        <button type="button" id="abrirModal">Ver Términos de uso</button>
    </form>
    <div id="ventanaModal" class="modal"> 
        <div id="contenidoModal" >
            <span class="cerrarModal">X</span>
            <h1>Esta a punto de elminar el bien</h1>
            <p>¿Desea continuar?</p>
            <button class="cerrarModal" style="background-color: #139EC8;  color: white;">Aceptar</button>
            <button class="cerrarModal" style="background-colo white; color: black;" >Cancelar</button>
        </div>
    </div>
    <script src="resources/js/validaciones.js"></script>

</body>
</html>