package ru.job4j.crudservlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserServletTest {

    @Test
    public void whenAddUserThenStoreIt() throws IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("action")).thenReturn("add");
        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("name")).thenReturn("alex");
        when(req.getParameter("login")).thenReturn("alex");
        when(req.getParameter("email")).thenReturn("alex");
        when(req.getParameter("password")).thenReturn("123");
        when(req.getParameter("role")).thenReturn("user");

        new UserServlet().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getName(), is("alex"));
    }

    @Test
    public void whenUpdateUserThenStoreIt() throws IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("action")).thenReturn("update");
        when(req.getParameter("id")).thenReturn("5");
        when(req.getParameter("name")).thenReturn("ivan123");
        when(req.getParameter("login")).thenReturn("ivan");
        when(req.getParameter("email")).thenReturn("ivan");
        when(req.getParameter("password")).thenReturn("12345");
        when(req.getParameter("role")).thenReturn("user");
        new UserServlet().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getName(), is("ivan123"));
    }

    @Test
    public void whenDeleteUserThenStoreIt() throws IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("action")).thenReturn("delete");
        when(req.getParameter("id")).thenReturn("5");
        new UserServlet().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getName(), is("petr"));
    }
}
