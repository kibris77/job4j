package ru.job4j.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет с формой для добавления пользователя.
 */
public class UserCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        printWriter.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"" + req.getContextPath() + "/user\" method=\"post\">\n" +
                "    ID:<br>\n" +
                "    <input type=\"text\" name=\"id\"><br>\n" +
                "    Имя:<br>\n" +
                "    <input type=\"text\" name=\"name\"><br>\n" +
                "    Логин:<br>\n" +
                "    <input type=\"text\" name=\"login\"><br>\n" +
                "    Email:<br>\n" +
                "    <input type=\"text\" name=\"email\">\n" +
                "    <input name=\"action\" type=\"hidden\" value=\"add\" />\n" +
                "    <br><input type=\"submit\" value=\"Отправить\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
        printWriter.flush();
    }
}
