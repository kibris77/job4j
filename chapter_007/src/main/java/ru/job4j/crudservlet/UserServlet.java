package ru.job4j.crudservlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Основной класс Сервлет программы обрабатывает запросы сервера.
 */
public class UserServlet extends HttpServlet {
    private final ValidateService validateService = ValidateService.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(HttpServlet.class);
    private final Map<String, Function<HttpServletRequest, String>> dispatch = new HashMap<>();

    public UserServlet() {
        dispatch.put("add", add());
        dispatch.put("update", update());
        dispatch.put("delete", delete());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendError(400);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        dispatch.get(req.getParameter("action")).apply(req);
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    Function<HttpServletRequest, String> add() {
        return reqest -> {
            String result;
            try {
                String id = reqest.getParameter("id");
                String name = reqest.getParameter("name");
                String login = reqest.getParameter("login");
                String email = reqest.getParameter("email");
                validateService.add(id, name, login, email);
                result = "Данные обновлены";
            } catch (WrongDataException e) {
                result = e.getMessage();
            }
            return result;
        };
    }

    Function<HttpServletRequest, String> update() {
        return reqest -> {
            String result;
            try {
                String id = reqest.getParameter("id");
                String name = reqest.getParameter("name");
                String login = reqest.getParameter("login");
                String email = reqest.getParameter("email");
                validateService.update(id, name, login, email);
                result = "Данные обновлены";
            } catch (WrongDataException e) {
                result = e.getMessage();
            }
            return result;
        };
    }

    Function<HttpServletRequest, String> delete() {
        return reqest -> {
            String result;
            try {
                String id = reqest.getParameter("id");
                validateService.delete(id);
                result = "Данные обновлены";
            } catch (WrongDataException e) {
                result = e.getMessage();
            }
            return result;
        };
    }
}
