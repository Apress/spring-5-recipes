<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Spring Social - Connect to Facebook</title>
</head>
<body>
<h3>Connect to Facebook</h3>

<form action="<spring:url value='/connect/facebook'/>" method="POST">
    <div class="formInfo">
        <p>You aren't connected to Facebook yet. Click the button to connect this application with your Facebook account.</p>
    </div>
    <p><button type="submit">Connect to Facebook</button></p>
</form>
</body>
</html>