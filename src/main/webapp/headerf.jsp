<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="controllers.UserController" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Funcionario-Invima</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="resources/css/estilos.css">
    <link rel="icon" type="image/x-icon" href="resources/img/logo_visto_bueno.png"> 
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
  </head>
  <body>
    <%
        HttpSession s = request.getSession();
        String username = (String) s.getAttribute("username");

        // Redirige a index.jsp si el nombre de usuario es null
        if (username == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        // Obtener el rol del usuario desde la base de datos
        int userRole = -1;
        try {
            userRole = UserController.getUserRoleByUsername(username);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al obtener el rol del usuario desde la base de datos.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        // Redirigir según el rol del usuario
        if (userRole != 2) {
            response.sendRedirect("index.jsp");
        }

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
        <img src="resources/img/Logo_Invima-Te-Acompana_0 1.png" alt="Logo_Invima-Te-Acompana" height="35">
    </nav>
    <nav class="navbar navbar-expand-lg navbar-light bg" id="options">
      <div class="container">
        <img src="resources/img/invima_blanco.png" alt="Logo_Invima-Te-Acompana" height="45" >
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav ms-auto mt-2 text-center">
            <li class="nav-item">
              <a class="nav-link text-white" href="homef.jsp">Inicio</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-white" href="Logout">Cerrar Sesión</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
</header>