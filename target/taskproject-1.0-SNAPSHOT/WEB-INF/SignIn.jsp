<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>
<p><font color="green">${exitMessage}</font></p>
<form action="/sign-in" method="post">
    <p><font color="red">${errorMessage}</font></p>
    <p><font color="orange">${emptyMessage}</font></p>
    <table>
        <tr>
            <td>Enter your name:</td>
            <td><input type="text" name ="name"><td>
            <td><a href="/sign-up">Регистрация</a> </td>
        </tr>
        <tr>
            <td>Enter your password:</td>
            <td><input type="password" name ="pass"></td>
        </tr>
    </table>
    <input type="submit" value="Войти">
</form>
</body>
</html>
