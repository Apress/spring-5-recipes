<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html> 
<head>
<title>Reservations</title>
</head>
<body> 

<table border="1">
<tr><th>Court</th><th>Date</th><th>Hour</th><th>Player Name</th><th>Player Phone</th></tr> 
  <c:forEach items="${reservations}" var="reservation">
  <tr>
    <td>${reservation.courtName}</td>
    <td>${reservation.date}</td>
    <td>${reservation.hour}</td>
    <td>${reservation.player.name}</td>
    <td>${reservation.player.phone}</td>
  </tr>
  </c:forEach>


</body> 
