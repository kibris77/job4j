<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alexander
  Date: 13.11.18
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Таблица пользователей</title>
</head>
<body>
<div class="container">
    <h2>Список пользователей</h2>
    <table class="table table-striped">
         <tr><th>ID</th><th>Name</th><th>Login</th><th>Email</th><th>Role</th><th>Страна</th><th>Город</th><th>Update</th><th>Delete</th></tr>
         <c:forEach items="${users}" var="user">
         <tr><td><c:out value="${user.id}"></c:out></td>
             <td><c:out value="${user.name}"></c:out></td>
             <td><c:out value="${user.login}"></c:out></td>
             <td><c:out value="${user.email}"></c:out></td>
             <td><c:out value="${user.role}"></c:out></td>
             <td><c:out value="${user.country}"></c:out></td>
             <td><c:out value="${user.city}"></c:out></td>
             <c:if test="${sessionScope.role == 'admin'}">
             <td>
                 <form action="${requestScope.sessionContext.contextPath}/" method="get">
                     <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                     <input type="hidden" name="method" value="update">
                     <input class="btn btn-primary" type="submit" value="Update">
                 </form>
             </td>
             <td>
                 <form action="${requestScope.sessionContext.contextPath}/" method="post">
                     <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                     <input type="hidden" name="action" value="delete">
                     <input class="btn" type="submit" value="Delete">
                 </form>

             </td>
             </c:if>
             <c:if test="${sessionScope.role == 'user' && sessionScope.id == user.id}">
             <td>
                     <form action="${requestScope.sessionContext.contextPath}/" method="get">
                         <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                         <input type="hidden" name="method" value="update">
                         <input class="btn btn-primary" type="submit" value="Update">
                     </form>
                 </td>
                 <td>
                         <form action="${requestScope.sessionContext.contextPath}/" method="post">
                             <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                             <input type="hidden" name="action" value="delete">
                             <input class="btn" type="submit" value="Delete">
                         </form>

                 </td>
             </c:if>
             </tr>
         </c:forEach>
     </table>

         <div class="row">
        <c:if test="${sessionScope.role == 'admin'}">
            <div class="col-sm-4">
         <form action="${requestScope.sessionContext.contextPath}/" method="get" class="form-inline">
             <br><input type="hidden" name="method" value="create">
             <br><input class="btn btn-primary" type="submit" value="Добавить поьзователя">
         </form>
        </c:if>
         <form action="${requestScope.sessionContext.contextPath}/" method="get" class="form-inline">
             <br><input type="hidden" name="method" value="unsign">
             <br><input class="btn" type="submit" value="Выйти">
         </form>
             </div>
         </div>
     </div>
</body>
</html>
