<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добро пожаловать</title>
</head>
<body>
${message}!
<br>
<br>
<form action="/sign-in" method="post">
    <input type="submit" value="Выйти" name="exit">
</form>
</body>
</html>