<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Hello Twitter</title>
</head>
<body>
<h3>Connected to Twitter</h3>

<p>
    You are now connected to your Twitter account.
    Click <a href="<spring:url value='/user/twitter/profile'/>">here</a> to see your Twitter Profile.
</p>
</body>
</html>