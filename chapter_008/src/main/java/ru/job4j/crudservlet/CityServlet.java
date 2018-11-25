package ru.job4j.crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Сервлет возвращает список городов в зависмости от страны.
 */
public class CityServlet extends HttpServlet {
    private ConcurrentHashMap<String, String[]> cities = new ConcurrentHashMap<>();

    @Override
    public void init() throws ServletException {
        String[] russia = {"Москва", "Ростов", "Сочи"};
        String[] usa = {"New York", "Los Angles", "Washington"};
        cities.put("Россия", russia);
        cities.put("USA", usa);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String country = req.getParameter("country");
        StringBuilder json = new StringBuilder();
        json.append("{\"cities\":[\"");
        System.out.println(country);
        String[] array = cities.get(country);
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                json.append(array[i]);
                if (i != array.length - 1) {
                    json.append("\",\"");
                } else {
                    json.append("\"]}");
                }
            }
        } else {
            json.append("\"]}");
        }
        System.out.println(json.toString());
        resp.setContentType("application/json");
        resp.addHeader("access-control-allow-origin", "*");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.append(json);
        writer.flush();
    }
}
