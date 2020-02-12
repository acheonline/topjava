<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<section>
    <style>
        .green {
            color: green;
        }

        .red    {
            color: red;
        }
    </style>
    <a href="meals?action=add">Добавить еду</a>
    <table align="center" border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Дата и время</th>
            <th>Описание</th>
            <th>Калории</th>
            <th colspan="2"></th>
        </tr>
        <c:forEach items="${listTo}" var="mealTo">
                    <tr class=${mealTo.excess == 'true' ? 'red' : 'green'}>
                        <td>${mealTo.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
                        <td>${mealTo.description}</td>
                        <td>${mealTo.calories}</td>
                        <td><a href="meals?id=${mealTo.id}&action=delete">Delete</a></td>
                        <td><a href="meals?id=${mealTo.id}&action=edit">Edit</a></td>
                    </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>