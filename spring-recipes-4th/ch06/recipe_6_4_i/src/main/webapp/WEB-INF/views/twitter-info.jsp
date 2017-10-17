<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Spring Recipes Social - Profile Page</title>
</head>
<body>
<h3>Twitter Profile</h3>

<p>
    <table>
        <tr><td>Name:</td><td>${profile.name}</td></tr>
        <tr><td>Username:</td><td>${profile.username}</td></tr>
        <tr><td>Email:</td><td>${profile.email}</td></tr>
    </table>
</p>
</body>
</html>