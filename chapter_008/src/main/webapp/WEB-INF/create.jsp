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
    <meta charset="UTF-8">
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
                ['#exampleID', 'ID'],
                ['#exampleNAME', 'Имя'],
                ['#exampleLOGIN', 'Логин'],
                ['#exampleEMAIL', 'Email'],
                ['#examplePASS', 'Пароль']
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
                        console.log(arr["cities"][i]);
                        $('#exampleCITY').append('<option value="' + arr["cities"][i] +'">' + arr["cities"][i]+ '</option>');
                    }
                }
            });
        }
    </script>
</head>
<body>
<div class="container">
    <h2>Добавление нового пользователя</h2>
    <form action="${requestScope.sessionContext.contextPath}/" method="post" class="form-horizontal">
        <div class="form-group">
            <label for="exampleID">ID:</label>
            <input type="text" name="id" class="form-control" id="exampleID" >
        </div>
        <div class="form-group">
            <label for="exampleNAME">Имя:</label>
            <input type="text" name="name" class="form-control" id="exampleNAME">
        </div>
        <div class="form-group">
            <label for="exampleLOGIN">Логин:</label>
            <input type="text" name="login" class="form-control" id="exampleLOGIN">
        </div>
        <div class="form-group">
            <label for="exampleLOGIN">Email:</label>
            <input type="text" name="email" class="form-control" id="exampleEMAIL">
        </div>
        <div class="form-group">
            <label for="examplePASS">Пароль:</label>
            <input type="text" name="password" class="form-control" id="examplePASS">
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
            <label for="exampleROLE">Роль:</label>
        <select class="form-control" name="role" class="form-control" id="exampleROLE">
            <option value="admin">Admin</option>
            <option value="user">User</option>
        </select>
        </div>
        <input name="action" type="hidden" value="add"/>
        <div class="form-group">
        <input class="btn btn-primary" type="submit" value="Отправить" onclick="return validate()">
        </div>
    </form>
    <div id ="message">

    </div>
</div>
</body>
</html>
