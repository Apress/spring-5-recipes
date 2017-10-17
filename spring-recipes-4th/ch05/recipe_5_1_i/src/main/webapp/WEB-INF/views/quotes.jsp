<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quotes - All</title>
</head>
<body>
<h1>Quotes</h1>
<table>
    <thead>
    <tr>
        <th>Joke</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="quote" items="${quotes}">
        <tr>
            <td>${quote.joke}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
