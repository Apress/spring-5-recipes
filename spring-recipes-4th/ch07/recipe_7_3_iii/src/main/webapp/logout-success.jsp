<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login</title>
    <link type="text/css" rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.10/semantic.min.css">
    <style type="text/css">
        body {
            background-color: #DADADA;
        }
        body > .grid {
            height: 100%;
        }
        .column {
            max-width: 450px;
        }
    </style>
</head>

<body>
<div class="ui middle aligned center aligned grid">
    <div class="column">
        <h2 class="ui header">You have been successfully logged out</h2>
        <div>Goto <a href="<c:url value="/"/>" class="link">todo list</a>.</div>
    </div>
</div>
</body>
</html>
