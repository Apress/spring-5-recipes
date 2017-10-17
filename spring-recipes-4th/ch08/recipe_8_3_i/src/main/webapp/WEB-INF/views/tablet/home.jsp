<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<body>

<h1>Welcome Tablet User</h1>
<p>
    Your User-Agent header: <c:out value="${header['User-Agent']}" />
</p>
<p>
    Your type of device: <c:out value="${requestScope.currentDevice}" />
</p>
<p>
    Your site preferences <c:out value="${requestScope.currentSitePreference}" />
</p>
</body>
</html>