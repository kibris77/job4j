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
    <title>Таблица пользователей</title>
</head>
<body>
     <table class="tftable" border="1">
         <tr><th>ID</th><th>Name</th><th>Login</th><th>Email</th><th>Role</th><th>Update</th><th>Delete</th></tr>
         <c:forEach items="${users}" var="user">
         <tr><td><c:out value="${user.id}"></c:out></td>
             <td><c:out value="${user.name}"></c:out></td>
             <td><c:out value="${user.login}"></c:out></td>
             <td><c:out value="${user.email}"></c:out></td>
             <td><c:out value="${user.role}"></c:out></td>
             <c:if test="${sessionScope.role == 'admin'}">
             <td>
                 <form action="${requestScope.sessionContext.contextPath}/" method="get">
                     <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                     <input type="hidden" name="method" value="update">
                     <input type="submit" value="Update">
                 </form>
             </td>
             <td>
                 <form action="${requestScope.sessionContext.contextPath}/" method="post">
                     <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                     <input type="hidden" name="action" value="delete">
                     <input type="submit" value="Delete">
                 </form>

             </td>
             </c:if>
             <c:if test="${sessionScope.role == 'user' && sessionScope.id == user.id}">
             <td>
                     <form action="${requestScope.sessionContext.contextPath}/" method="get">
                         <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                         <input type="hidden" name="method" value="update">
                         <input type="submit" value="Update">
                     </form>
                 </td>
                 <td>
                         <form action="${requestScope.sessionContext.contextPath}/" method="post">
                             <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                             <input type="hidden" name="action" value="delete">
                             <input type="submit" value="Delete">
                         </form>

                 </td>
             </c:if>
             </tr>
         </c:forEach>
     </table>
    <c:if test="${sessionScope.role == 'admin'}">
     <form action="${requestScope.sessionContext.contextPath}/" method="get">
         <br><input type="hidden" name="method" value="create">
         <br><input type="submit" value="Добавить поьзователя">
     </form>
    </c:if>
     <form action="${requestScope.sessionContext.contextPath}/" method="get">
         <br><input type="hidden" name="method" value="unsign">
         <br><input type="submit" value="Выйти">
     </form>
</body>
</html>
