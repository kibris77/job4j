<%--
  Created by IntelliJ IDEA.
  User: alexander
  Date: 17.11.18
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Singin</title>
</head>
<body>
<div class="container">
    <h2>Вход на сайт</h2>
    <form class="form-horizontal" action="${requestScope.sessionContext.contextPath}/singin" method="post">
        <div class="form-group">
            <label class="control-label col-sm-2">Логин:</label>
            <div class="col-sm-10">
                <input type="text" name="login">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2">Пароль:</label>
            <div class="col-sm-10">
                <input type="text" name="password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <input class="btn btn-primary" type="submit" value="Войти">
            </div>
        </div>
        <c:if test="${requestScope.wronglogin != null && requestScope.wronglogin == 1}">
        <div>
            Вы ввели не верный логин или пароль.
        </div>
        </c:if>

    </form>
</div>
</body>
</html>
