package ru.job4j.crudservlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Класс фильтр для проверки авторизации пользователей.
 */
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        if (req.getRequestURI().contains("/singin")) {
            filterChain.doFilter(req, res);
        } else {
            if (session.getAttribute("role") == null) {
                res.sendRedirect(String.format("%s/singin", req.getContextPath()));
            } else {
                filterChain.doFilter(req, res);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
