<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Spring Recipes - Spring Social - Twitter</title>
</head>
<body>
<h1>Twitter Profile</h1>
Hello ${screenname} you've got ${friends} friends.

<h1>Timeline</h1>
<table>
<c:forEach items="${timeline}" var="tweet">
    <tr>
        <td>${tweet.createdAt}</td>
        <td>${tweet.text}</td>
        <td>${tweet.fromUser}</td>
    </tr>
</c:forEach>
</table>

<h1>Send Tweet</h1>
<form method="POST">
    <textarea name="status" rows="4" cols="35" maxlength="140" />
</form>

</body>
</html>