<!DOCTYPE html>
<%@tag description="Template Site tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="headerTemplate" tagdir="/WEB-INF/tags" %>

<%@attribute name="title" fragment="true" %>
<%@attribute name="header" fragment="true" %>

<html>
<head>
    <title><jsp:invoke fragment="title"/></title>

    <!-- Bootstrap Core CSS -->
    <spring:url value="/resources/dir/css/bootstrap.css" var="bootstrap"/>
    <link href="${bootstrap}" rel="stylesheet" />

    <!-- Custom CSS -->
    <spring:url value="/resources/dir/css/modern-business.css" var="startertemplate"/>
    <link href="${startertemplate}" rel="stylesheet" />

    <!-- Custom Fonts -->
    <spring:url value="/resources/dir/font-awesome/css/font-awesome.min.css" var="fontawesome"/>
    <link href="${fontawesome}" rel="stylesheet" />

    <!-- jQuery -->
    <spring:url value="/resources/dir/js/jquery-2.1.4.min.js" var="jqueryjs"/>
    <script src="${jqueryjs}"></script>

    <!-- Bootstrap Core JavaScript -->
    <spring:url value="/resources/dir/js/bootstrap.min.js" var="js"/>
    <script src="${js}"></script>
</head>

<body>


<headerTemplate:header-template/>

<jsp:doBody/>


    <!-- Footer -->
    <footer>

        <div class="row">
            <div class="navbar navbar-default navbar-fixed-bottom" >
                <div class="container">
                    <p class="navbar-text pull-left">&copy;<script>var date = new Date(); document.write(date.getFullYear() + " ")</script>
                        <a href="" target="_blank" >Vitalii Rubezhanskii</a>
                    </p>
                </div>
            </div>
        </div>

    </footer>


</body>

</html>
