<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
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
<form action="${requestScope.sessionContext.contextPath}/" method="post">
    ID:<br>
    ${user.id}
    <input type="hidden" name="id" value="${user.id}"><br>
    Имя:<br>
    <input type="text" name="name" value="${user.name}"><br>
    Логин:<br>
    <input type="text" name="login" value="${user.login}"><br>
    Email:<br>
    <input type="text" name="email" value="${user.email}"><br>
    Role: ${user.role}<br>
    <c:if test="${sessionScope.role == 'admin' && user.role =='admin'}">
    <select name="role">
        <option selected value="admin">Admin</option>
        <option value="user">User</option>
    </select><br>
    </c:if>
    <c:if test="${sessionScope.role == 'admin' && user.role =='user'}">
        <select name="role">
            <option value="admin">Admin</option>
            <option selected value="user">User</option>
        </select><br>
    </c:if>
    <c:if test="${sessionScope.role == 'user'}">
    <input type="hidden" name="role" value="${user.role}">
    </c:if>
    <input name="action" type="hidden" value="update"/>
    <br><input type="submit" value="Отправить">

</form>
</body>
</html>
