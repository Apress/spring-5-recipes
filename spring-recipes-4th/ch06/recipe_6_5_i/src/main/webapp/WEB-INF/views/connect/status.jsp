<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Spring Social - Connections</title>
</head>
<body>
<h3>Spring Social - Connections</h3>

<c:forEach items="${providerIds}" var="provider">
    <h4></h4>
    <c:if test="${connectionMap[provider] != null}">
        You are connected to ${provider} as ${connectionMap[provider][0].displayName}
        <c:if test="${provider eq 'twitter'}">
            Click <a href="<c:url value="/twitter"/>">here</a> for twitter operations.
        </c:if>    </c:if>
    <c:if test="${connectionMap[provider] == null}">
        <div>
            You are not yet connected to ${provider}. Click <a href="<spring:url value="/connect/${provider}"/>">here</a> to connect to ${provider}.
        </div>
    </c:if>
</c:forEach>

</body>
</html>