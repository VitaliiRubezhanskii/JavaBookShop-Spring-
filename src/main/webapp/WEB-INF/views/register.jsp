<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration Form</title>

    <!-- Bootstrap Core CSS -->
    <spring:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" var="bootstrap"/>
    <link href="${bootstrap}" rel="stylesheet" />


</head>

<body>
<div class="generic-container">


    <div class=" navbar navbar-default navbar-fixed-top" id="navbar-collapse-2">

        <ul class="nav navbar-nav navbar-left">
            <li><a href="#">Home</a></li>
            <li><a href="#">About</a></li>

        </ul>
    </div>

    <form:form method="POST" modelAttribute="user" class="form-horizontal">




        <div class="well lead" style="padding-top: 90px" >Registration Form</div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="username">Username</label>
                <div class="col-md-7">
                    <form:input type="text" path="username" id="username" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="username" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="password">Password</label>
                <div class="col-md-7">
                    <form:input type="password" path="password" id="password" class="form-control input-sm" />
                    <div class="has-error">
                        <form:errors path="password" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>



        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="role">Roles</label>
                <form:select path="role">
                    <form:option value="ROLE_USER" />

                </form:select>
            </div>
        </div>

        <input type="submit" value="Register" class="btn btn-primary btn-sm"/>
        or <a href="<c:url value='/welcome/home' />">Cancel</a>
    </form:form>


    <c:if test="${user.username != null}">

    <p>User ${userName} registered successfully. Please log in now</p>
        <a href= "<spring:url value="/login"/>" >
            <input type="submit" value="<spring:message text="Log In" />"  class="btn btn-default btn-outline btn-circle collapsed"  style="float:left"
                   aria-expanded="false"   />
        </a>
    </c:if>
</div>
</body>
</html>