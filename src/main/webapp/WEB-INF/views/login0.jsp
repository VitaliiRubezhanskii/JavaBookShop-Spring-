<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login page</title>

    <!-- Bootstrap Core CSS -->
    <spring:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" var="bootstrap"/>
    <link href="${bootstrap}" rel="stylesheet" />
    <!-- Custom Fonts -->
    <spring:url value="/resources/styles/login.css" var="login"/>
    <link href="${login}" rel="stylesheet" />

</head>

<body>
<div id="mainWrapper">
    <div class="login-container">
        <div class="login-card">
            <div class="login-form">



                    <c:if test="${not empty error}">
                        <div class="error">${error}</div>
                    </c:if>
                    <c:if test="${not empty msg}">
                        <div class="msg">${msg}</div>
                    </c:if>

                    <form name='loginForm'
                          action="<c:url value='/login' />" method='POST'>

                        <table>
                            <tr>
                                <td>User:</td>
                                <td><input type='text' name='username'></td>
                            </tr>
                            <tr>
                                <td>Password:</td>
                                <td><input type='password' name='password' /></td>
                            </tr>
                            <tr>
                                <td colspan='2'><input name="submit" type="submit"
                                                       value="submit" /></td>
                            </tr>
                        </table>

                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}" />

                    </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>