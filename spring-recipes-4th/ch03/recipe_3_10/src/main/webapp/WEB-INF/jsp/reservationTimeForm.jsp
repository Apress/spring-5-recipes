<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Reservation Time Form</title>
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
    <td>From Date</td>
    <td><form:input path="fromDate" /></td>
    <td><form:errors path="fromDate" cssClass="error" /></td>
  </tr>
  <tr>
    <td>To Date</td>
    <td><form:input path="toDate" /></td>
    <td><form:errors path="toDate" cssClass="error" /></td>
  </tr>
  <tr>
    <td>Period</td>
    <td><form:select path="period" items="${periods}" /></td>
    <td><form:errors path="period" cssClass="error" /></td>
  </tr>
  <tr>
    <td>Hour</td>
    <td><form:input path="hour" /></td>
    <td><form:errors path="hour" cssClass="error" /></td>
  </tr>
  <tr>
    <td colspan="3">
      <input type="hidden" value="1" name="_page"/>
      <input type="submit" value="Previous" name="_target0" />
      <input type="submit" value="Next" name="_target2" />
      <input type="submit" value="Cancel" name="_cancel" />
    </td>
  </tr>
</table>
</form:form>
</body>
</html>
