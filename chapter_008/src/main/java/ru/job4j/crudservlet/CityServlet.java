package ru.job4j.crudservlet;

import com.fasterxml.jackson.databind.ObjectMapper;

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
        System.out.println(country);
        ObjectMapper objectMapper = new ObjectMapper();
        String[] array = cities.get(country);
        String jsonOut = objectMapper.writeValueAsString(array);
        System.out.println(jsonOut);
        resp.setContentType("application/json");
        resp.addHeader("access-control-allow-origin", "*");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.append(jsonOut);
        writer.flush();
    }
}
