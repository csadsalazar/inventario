/*package controllers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*") // Se aplica a todas las URL
public class AuthenticationFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            // El usuario no ha iniciado sesión, redirige a la página de inicio de sesión
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsp");
        } else {
            // El usuario ha iniciado sesión, permite que la solicitud continúe
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
        // Método de inicialización del filtro (no es necesario implementarlo para este ejemplo)
    }

    public void destroy() {
        // Método de destrucción del filtro (no es necesario implementarlo para este ejemplo)
    }
}
*/

