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
 * Основной класс программы обрабатывает запросы сервера.
 */
public class UserServlet extends HttpServlet {
    private final ValidateService validateService = ValidateService.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(HttpServlet.class);
    private final Map<String, Function<HttpServletRequest, Boolean>> dispatch = new HashMap<>();

    public UserServlet() {
        dispatch.put("add", add());
        dispatch.put("update", update());
        dispatch.put("delete", delete());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        List<User> users = validateService.findAll();
        for (User user : users) {
           printWriter.append(String.format("ID - %s, Name - %s, Login - %s, E-mail - %s<br/>",
                   user.getId(), user.getName(), user.getLogin(), user.getEmail()));
        }
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append(dispatch.get(req.getParameter("action")).apply(req) ? "Finish" : "Data Error");
        printWriter.flush();
        doGet(req, resp);
    }

    Function<HttpServletRequest, Boolean> add() {
        return reqest -> {
            boolean result = false;
            try {
                String id = reqest.getParameter("id");
                String name = reqest.getParameter("name");
                String login = reqest.getParameter("login");
                String email = reqest.getParameter("email");
                validateService.add(id, name, login, email);
                result = true;
            } catch (Exception e) {
                LOGGER.error(e);
            }
            return result;
        };
    }

    Function<HttpServletRequest, Boolean> update() {
        return reqest -> {
            boolean result = false;
            try {
                String id = reqest.getParameter("id");
                String name = reqest.getParameter("name");
                String login = reqest.getParameter("login");
                String email = reqest.getParameter("email");
                validateService.update(id, name, login, email);
                result = true;
            } catch (Exception e) {
                LOGGER.error(e);
            }
            return result;
        };
    }

    Function<HttpServletRequest, Boolean> delete() {
        return reqest -> {
            boolean result = false;
            try {
                String id = reqest.getParameter("id");
                validateService.delete(id);
                result = true;
            } catch (Exception e) {
                LOGGER.error(e);
            }
            return result;
        };
    }
}
