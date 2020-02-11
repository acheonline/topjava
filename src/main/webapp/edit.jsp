<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <title>Добавление еды</title>
</head>
<body>
<section>
    <form align="center" method="post" action="meals" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="id" value="${mapTo.key}">
        <h1>Описание еды</h1>
        <dl align="center">
            <input type="text" style="text-align:center;" name="description" size=55 value="<c:out value="${meal.description}" />">
        </dl>
        <h1>Время приема</h1>
        <dl align="center">
            <input type="datetime-local" style="text-align:center;" name="datetime" size=55 value="<c:out value="${meal.dateTime}"/>">
        </dl>
        <h1>Калории</h1>
        <dl>
            <input type="text" style="text-align:center;" name="calories" size=55 value="${meal.calories}">
        </dl>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
</body>
</html>