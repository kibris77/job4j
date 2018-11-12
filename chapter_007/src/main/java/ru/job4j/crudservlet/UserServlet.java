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
        resp.setContentType("text/html");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        List<User> users = validateService.findAll();
        printWriter.append("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Title</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<table class=\"tftable\" border=\"1\">\n"
                + "    <tr><th>ID</th><th>Name</th><th>Login</th><th>Email</th><th>Update</th><th>Delete</th></tr>");
        for (User user : users) {
           printWriter.append(String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s"
                           + "</td>"
                           + "<td>"
                           + "<form action=\"" + req.getContextPath() + "/edit\" method=\"get\">\n"  +
                           "    <input type=\"hidden\" name=\"id\" value=\"" + user.getId() + "\">\n" +
                           "    <input type=\"hidden\" name=\"name\" value=\""+ user.getName() + "\">\n" +
                           "    <input type=\"hidden\" name=\"login\" value=\""+ user.getLogin() + "\">\n" +
                           "    <input type=\"hidden\" name=\"email\" value=\"" + user.getEmail() + "\">\n" +
                           "    <input type=\"submit\" value=\"Изменить\">\n" +
                           "</form>"
                           +"</td>"
                           + "<td>"
                           + "<form action=\"" + req.getContextPath() + "/user\" method=\"post\">\n" +
                           "    <input type=\"hidden\" name=\"action\" value=\"delete\">\n" +
                           "    <input type=\"hidden\" name=\"id\" value=\"" + user.getId()+ "\">\n" +
                           "    <input type=\"submit\" value=\"Удалить\">\n" +
                           "</form>"
                           +"</td>"
                           + "</tr>\n",
                   user.getId(), user.getName(), user.getLogin(), user.getEmail()));
        }
        printWriter.append("</table>\n" +
                "<form action=\"" + req.getContextPath() + "/create\" method=\"get\">\n" +
                "    <input type=\"submit\" value=\"Добавить пользователя\">\n" +
                "</form>"
                + "</body>\n"
                + "</html>");
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append(dispatch.get(req.getParameter("action")).apply(req));
        printWriter.flush();
        doGet(req, resp);
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
