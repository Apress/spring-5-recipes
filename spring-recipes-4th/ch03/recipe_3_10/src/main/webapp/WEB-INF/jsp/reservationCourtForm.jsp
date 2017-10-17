<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Reservation Court Form</title>
<style>
.error {
  color: #ff0000;
  font-weight: bold;
}
</style>
</head>

<body>
<form:form method="post" modelAttribute="reservation">
<table>
  <tr>
    <td>Court Name</td>
    <td><form:input path="courtName" /></td>
    <td><form:errors path="courtName" cssClass="error" /></td>
  </tr>
  <tr>
    <td colspan="3">
      <input type="hidden" value="0" name="_page"/>
      <input type="submit" value="Next" name="_target1" />
      <input type="submit" value="Cancel" name="_cancel" />
    </td>
  </tr>
</table>
</form:form>
</body>
</html>
