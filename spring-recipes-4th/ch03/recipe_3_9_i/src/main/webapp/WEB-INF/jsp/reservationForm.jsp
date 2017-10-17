<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Reservation Form</title>
    <style>
        .error {
            color: #ff0000;
            font-weight: bold;
        }
    </style>
</head>

<body>
<form:form method="post" modelAttribute="reservation">
    <form:errors path="*" cssClass="error"/>
    <table>
        <tr>
            <td>Court Name</td>
            <td><form:input path="courtName"/></td>
            <td><form:errors path="courtName" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Date</td>
            <td><form:input path="date"/></td>
            <td><form:errors path="date" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Hour</td>
            <td><form:input path="hour"/></td>
            <td><form:errors path="hour" cssClass="error"/></td>
        </tr>
        <tr>
            <td colspan="3"><input type="submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>
