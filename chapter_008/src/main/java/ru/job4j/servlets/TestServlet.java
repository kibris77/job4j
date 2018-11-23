package ru.job4j.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Тестовый класс сервлет для формы с Ajax.
 */
public class TestServlet extends HttpServlet {
    ConcurrentHashMap<String, TestUser> users = new ConcurrentHashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer data = new StringBuffer();
        String line;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        storeJSON(data.toString());
        resp.setContentType("application/json");
        resp.addHeader("access-control-allow-origin", "*");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.append(sendJSON());
        writer.flush();
    }

    /**
     * Метод для преобразоания и сохраниния JSON строки в хранилище.
     * @param data - строка в формате JSON.
     */
    private void storeJSON(String data) {
        if (data.contains("name") && data.contains("surname") && data.contains("sex") && data.contains("description")) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                TestUser user = mapper.readValue(data, TestUser.class);
                users.put(user.getName(), user);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Мтеод для преобразования хранилища в JSON строку.
     * @return строка JSON.
     * @throws JsonProcessingException
     */
    private String sendJSON() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(users);
        return json;
    }
}
