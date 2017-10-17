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
        <h2 class="ui header">Log-in to your account</h2>
        <form method="POST" action="<c:url value="/login" />" class="ui large form">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="ui stacked segment">
                <div class="field">
                    <div class="ui left icon input">
                        <i class="user icon"></i>
                        <input type="text" name="username" placeholder="E-mail address">
                    </div>
                </div>
                <div class="field">
                    <div class="ui left icon input">
                        <i class="lock icon"></i>
                        <input type="password" name="password" placeholder="Password">
                    </div>
                </div>
                <div class="field">
                    <div class="ui toggle checkbox">
                        <input type="checkbox" name="remember-me">
                        <label>Remember Me</label>
                    </div>
                </div>
                <button class="ui fluid large submit green button">Login</button>
            </div>
            <c:if test="${not empty param.error}">
                <div class="ui error message" style="display: block;">
                    Authentication Failed<br/>
                    Reason : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                    </font>
                </div>
            </c:if>
        </form>
    </div>
</div>
</body>
</html>
