<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
    <c:url var="formLogin" value="/signin/authenticate" />
    <c:if test="${param.error eq 'bad_credentials'}">
        <div class="error">
            The login information was incorrect please try again.
        </div>
    </c:if>
    <form method="post" action="${formLogin}">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <table>
            <tr><td><label for="username">Username</label></td><td><input type="text" name="username"/></td></tr>
            <tr><td><label for="password">Password</label></td><td><input type="password" name="password"/></td></tr>
            <tr><td colspan="2"><button>Login</button></td> </tr>
        </table>
    </form>
</body>

</html>