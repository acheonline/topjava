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
        <c:forEach items="${mapTo}" var="entry">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo" scope="request"/>
            <c:choose>
                <c:when test="${entry.value.excess == 'true'}">
                    <tr class="red">
                        <td>${entry.value.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
                        <td>${entry.value.description}</td>
                        <td>${entry.value.calories}</td>
                        <td><a href="meals?uuid=${entry.key}&action=delete">Delete</a></td>
                        <td><a href="meals?uuid=${entry.key}&action=edit">Edit</a></td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr class="green">
                        <td>${entry.value.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
                        <td>${entry.value.description}</td>
                        <td>${entry.value.calories}</td>
                        <td><a href="meals?uuid=${entry.key}&action=delete">Delete</a></td>
                        <td><a href="meals?uuid=${entry.key}&action=edit">Edit</a></td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </table>
</section>
</body>
</html>