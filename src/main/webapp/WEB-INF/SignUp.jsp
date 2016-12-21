<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<p><font color="red">${message}</font></p>
<form action="/sign-up" method="post">
    <table>
        <tr>
            <td>Имя пользователя</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>Пароль:<br></td>
            <td><input type="password" name="pass"></td>
        </tr>
        <tr>
            <td>Повтор пароля</td>
            <td><input type="password" name="pass2"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Зарегистрироваться"></td>
        </tr>
    </table>
</form>
</body>
</html>