<%@ page import="ru.job4j.crudservlet.User" %>
<%@ page import="ru.job4j.crudservlet.ValidateService" %>
<%@ page import="ru.job4j.crudservlet.WrongDataException" %><%--
  Created by IntelliJ IDEA.
  User: alexander
  Date: 13.11.18
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование пользователя</title>
</head>
<body>
<%try {
       User user = ValidateService.getInstance().findById(request.getParameter("id"), false);
%>
<form action="<%=request.getContextPath()%>/user" method="post">
    ID:<br>
    <%=user.getId()%>
    <input type="hidden" name="id" value="<%=user.getId()%>"><br>
    Имя:<br>
    <input type="text" name="name" value="<%=user.getName()%>"><br>
    Логин:<br>
    <input type="text" name="login" value="<%=user.getLogin()%>"><br>
    Email:<br>
    <input type="text" name="email" value="<%=user.getEmail()%>">
    <input name="action" type="hidden" value="update"/>
    <br><input type="submit" value="Отправить">
</form>
<%} catch (WrongDataException e) {%>
    <h1><%=e.getMessage()%></h1>
<%}%>
</body>
</html>
