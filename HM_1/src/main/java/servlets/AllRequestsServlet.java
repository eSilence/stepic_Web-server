package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * сервлет, который обрабатывает запросы на /mirror
 */
public class AllRequestsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        String key = request.getParameter("key");

        if (key != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(key);
        }
        else{
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().println("");
        }
        response.setContentType("text/html;charset=utf-8");
    }
}
