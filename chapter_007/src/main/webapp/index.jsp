<%@ page import="ru.job4j.crudservlet.User" %>
<%@ page import="ru.job4j.crudservlet.MemoryStore" %><%--
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
         <tr><th>ID</th><th>Name</th><th>Login</th><th>Email</th><th>Update</th><th>Delete</th></tr>
         <% for (User user : MemoryStore.getInstance().findAll()) { %>
         <tr><td><%= user.getId()%></td><td><%= user.getName()%></td><td><%= user.getLogin()%></td>
             <td><%= user.getEmail()%></td>
             <td>
                 <form action="<%=request.getContextPath()%>/update.jsp" method="post">
                     <input type="hidden" name="id" value="<%=user.getId()%>">
                     <input type="submit" value="Update">
                 </form>
             </td>
             <td>
                 <form action="<%=request.getContextPath()%>/user" method="post">
                     <input type="hidden" name="id" value="<%=user.getId()%>">
                     <input type="hidden" name="action" value="delete">
                     <input type="submit" value="Delete">
                 </form>
             </td></tr>
         <% } %>
     </table>
     <form action="<%=request.getContextPath()%>/create.jsp" method="get">
         <br><input type="submit" value="Добавить поьзователя">
     </form>
</body>
</html>
