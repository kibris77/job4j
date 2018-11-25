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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function validate() {
            var isError = false;
            var result = '';
            var regexp = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            var arr = new Map([
                ['#exampleNAME', 'Имя'],
                ['#exampleLOGIN', 'Логин'],
                ['#exampleEMAIL', 'Email'],
            ]);
            for(let key of arr.keys()) {
                if($(key).val() == "") {
                    result +=arr.get(key) + " ";
                    isError = true;
                }
            }
            if(isError) {
                $('#message').empty();
                $('#message').append("Вы не заполнили поля: " + result);
            }
            if(!isError && !regexp.test($('#exampleEMAIL').val())){
                $('#message').empty();
                $('#message').append("Вы запольнили поле Email в неверном формате.");
                isError = true;
            }
            return !isError;
        }

        function choseCity() {
            $.get({
                url: 'http://localhost:8082/city?country=' + $("#exampleCOUNTRY").val(),
                dataType: "json",
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",
                complete: function(json) {
                    var arr = JSON.parse(json.responseText);
                    $('#exampleCITY').empty();
                    for (var i = 0; i < arr["cities"].length; ++i) {
                        if(arr["cities"][i] == '${user.city}') {
                            $('#exampleCITY').append('<option selected value="' + arr["cities"][i] + '">' + arr["cities"][i] + '</option>');
                        } else {
                            $('#exampleCITY').append('<option value="' + arr["cities"][i] + '">' + arr["cities"][i] + '</option>');
                        }
                    }
                }
            });
        }

        function setCountry() {
            $('#exampleCOUNTRY').find('option').each(
                function() {
                    if($(this).val() == "${user.country}"){
                        $(this).prop("selected", true);
                    }
                }
            );
            choseCity();
        }
    </script>
</head>
<body>
<script>
    $(window).on("load",function() {
        setCountry();
    });
</script>
<div class="container">
    <h2>Редактирование пользователя</h2>
    <form action="${requestScope.sessionContext.contextPath}/" method="post" class="form-horizontal">
        <div class="form-group">
            <label>ID: ${user.id}</label>
        </div>
        <input type="hidden" name="id" value="${user.id}">
        <div class="form-group">
            <label>Имя:</label>
            <input type="text" name="name" value="${user.name}" class="form-control" id="exampleNAME"><br>
        </div>
        <div class="form-group">
            <label>Логин:</label>
            <input type="text" name="login" value="${user.login}" class="form-control" id="exampleLOGIN"><br>
        </div>
        <div class="form-group">
            <label>Email:</label>
            <input type="text" name="email" value="${user.email}" class="form-control" id="exampleEMAIL"><br>
        </div>
        <div class="form-group">
            <label for="exampleCOUNTRY">Страна:</label>
            <select class="form-control" name="country" class="form-control" id="exampleCOUNTRY" onchange="choseCity()">
                <option value="none"></option>
                <option value="Россия">Россия</option>
                <option value="USA">USA</option>
            </select>
        </div>
        <div class="form-group">
            <label for="exampleCITY">Город:</label>
            <select class="form-control" name="city" class="form-control" id="exampleCITY">

            </select>
        </div>
        <div class="form-group">
            <label>Role: ${user.role}</label>
        </div>
        <c:if test="${sessionScope.role == 'admin' && user.role =='admin'}">
        <div class="form-group">
            <select name="role" class="form-control" id="exampleROLE">
                <option selected value="admin">Admin</option>
                <option value="user">User</option>
            </select>
        </div>
        </c:if>
        <c:if test="${sessionScope.role == 'admin' && user.role =='user'}">
        <div class="form-group">
            <select name="role" class="form-control" id="exampleROLE1">
                <option value="admin">Admin</option>
                <option selected value="user">User</option>
            </select><br>
        </div>
        </c:if>
        <c:if test="${sessionScope.role == 'user'}">
        <input type="hidden" name="role" value="${user.role}">
        </c:if>
        <input name="action" type="hidden" value="update"/>
        <div class="form-group">
            <input class="btn btn-primary" type="submit" value="Отправить" onclick="return validate()">
        </div>
    </form>
    <div id ="message">
    </div>
</div>
</body>
</html>
