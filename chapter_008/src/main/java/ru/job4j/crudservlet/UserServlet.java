package ru.job4j.crudservlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Основной класс Сервлет программы обрабатывает запросы сервера.
 */
public class UserServlet extends HttpServlet {
    private final Validate validateService = ValidateService.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(HttpServlet.class);
    private final Map<String, Function<HttpServletRequest, String>> dispatch = new HashMap<>();

    public UserServlet() {
        dispatch.put("add", add());
        dispatch.put("update", update());
        dispatch.put("delete", delete());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (req.getParameter("method") != null && req.getParameter("method").equals("create")) {
            req.getRequestDispatcher("/WEB-INF/create.jsp").forward(req, resp);
        } else if (req.getParameter("method") != null && req.getParameter("method").equals("update")) {
            User user = null;
            try {
                user = validateService.findById(req.getParameter("id"), false);
            } catch (WrongDataException e) {
                e.printStackTrace();
            }
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/update.jsp").forward(req, resp);
        } else if (req.getParameter("method") != null && req.getParameter("method").equals("unsign")) {
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("users", validateService.findAll());
            req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        dispatch.get(req.getParameter("action")).apply(req);
        resp.sendRedirect(req.getContextPath() + "/");
    }

    private Function<HttpServletRequest, String> add() {
        return reqest -> {
            String result;
            try {
                String id = reqest.getParameter("id");
                String name = reqest.getParameter("name");
                String login = reqest.getParameter("login");
                String email = reqest.getParameter("email");
                String password = reqest.getParameter("password");
                String role = reqest.getParameter("role");
                String country = reqest.getParameter("country");
                String city = reqest.getParameter("city");
                validateService.add(id, name, login, email, password, role, country, city);
                result = "Данные обновлены";
            } catch (WrongDataException e) {
                result = e.getMessage();
            }
            return result;
        };
    }

    private Function<HttpServletRequest, String> update() {
        return reqest -> {
            String result;
            try {
                String id = reqest.getParameter("id");
                String name = reqest.getParameter("name");
                String login = reqest.getParameter("login");
                String email = reqest.getParameter("email");
                String role = reqest.getParameter("role");
                String country = reqest.getParameter("country");
                String city = reqest.getParameter("city");
                validateService.update(id, name, login, email, null, role, country, city);
                result = "Данные обновлены";
            } catch (WrongDataException e) {
                result = e.getMessage();
            }
            return result;
        };
    }

    private Function<HttpServletRequest, String> delete() {
        return reqest -> {
            String result;
            try {
                String id = reqest.getParameter("id");
                if (!validateService.delete(id)) {
                    throw new WrongDataException("Пользователь не существует");
                }
                result = "Данные обновлены";
            } catch (WrongDataException e) {
                result = e.getMessage();
            }
            return result;
        };
    }
}
