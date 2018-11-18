package ru.job4j.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс сервлет для авторизации пользоватлей.
 */
public class SinginServlet extends HttpServlet {
    private final ValidateService validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/singin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = validateService.findByLogin(req.getParameter("login"));
        if (user != null && validateService.isAuthorized(req.getParameter("login"), req.getParameter("password"))) {
            req.getSession().setAttribute("role", user.getRole());
            req.getSession().setAttribute("id", user.getId());
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            doGet(req, resp);
        }
    }
}
