<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype HTML>
<html>
<head>
    <title>Spring Recipes - Contact Sample</title>
</head>
<body>
<h1>Contact</h1>
<form:form method="post" modelAttribute="contact">
    <fieldset>
        <legend>Contact Information</legend>
        <div>
            <div><form:label path="name">Name</form:label></div>
            <div><form:input path="name"/></div>
        </div>
        <div>
            <div><form:label path="email">Email Address</form:label></div>
            <div><form:input path="email" type="email"/></div>
        </div>
        <div><button>Save</button></div>
    </fieldset>
    <form

</form:form>
</html>