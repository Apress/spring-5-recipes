<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Welcome</title>
</head>

<body>
<h2>Welcome to Court Reservation System</h2>
Today is <fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/>.

<hr/>
Handling time : ${handlingTime} ms

<br/>
Locale : ${pageContext.response.locale}

</body>
</html>
