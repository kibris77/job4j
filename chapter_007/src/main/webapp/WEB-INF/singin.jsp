<%--
  Created by IntelliJ IDEA.
  User: alexander
  Date: 17.11.18
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Singin</title>
</head>
<body>
<form action="${requestScope.sessionContext.contextPath}/chapter_007/singin" method="post">
    Логин:<br>
    <input type="text" name="login"><br>
    Password:<br>
    <input type="text" name="password"><br>
    <br><input type="submit" value="Войти">
</form>
</body>
</html>
