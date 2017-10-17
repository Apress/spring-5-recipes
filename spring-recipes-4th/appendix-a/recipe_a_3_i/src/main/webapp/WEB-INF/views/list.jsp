<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype HTML>
<html>
<head>
    <title>Spring Recipes - Contact Sample</title>
</head>
<body>
<h1>Contacts</h1>
<table>
    <tr><th>Name</th><th>Email</th></tr>
    <c:forEach items="${contacts}" var="contact">
        <tr><td>${contact.name}</td><td>${contact.email}</td></tr>
    </c:forEach>
</table>
<a href="<c:url value="/contact/new"/>">New Contact</a>
</body>
</html>