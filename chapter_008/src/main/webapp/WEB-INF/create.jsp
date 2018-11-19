<%--
  Created by IntelliJ IDEA.
  User: alexander
  Date: 13.11.18
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание пользовтаеля</title>
</head>
<body>
    <form action="${requestScope.sessionContext.contextPath}/" method="post">
        ID:<br>
        <input type="text" name="id"><br>
        Имя:<br>
        <input type="text" name="name"><br>
        Логин:<br>
        <input type="text" name="login"><br>
        Email:<br>
        <input type="text" name="email"><br>
        Password:<br>
        <input type="text" name="password"><br>
        Role:<br>
        <select name="role">
            <option value="admin">Admin</option>
            <option value="user">User</option>
        </select>
        <input name="action" type="hidden" value="add"/>
        <br><input type="submit" value="Отправить">
    </form>
</body>
</html>
