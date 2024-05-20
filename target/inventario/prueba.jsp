<!DOCTYPE html>
<html>
<head>
    <title>Enviar Correo</title>
</head>
<body>
    <h2>Enviar Correo</h2>
    <form action="EnviarCorreoServlet" method="post">
        <label>Correo Electrónico:</label>
        <input type="email" name="email" required><br>
        <label>Asunto:</label>
        <input type="text" name="subject" required><br>
        <label>Mensaje:</label><br>
        <textarea name="message" rows="4" cols="50" required></textarea><br>
        <input type="submit" value="Enviar">
    </form>
</body>
</html>
