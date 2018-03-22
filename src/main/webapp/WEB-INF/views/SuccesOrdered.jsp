<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

    <html>
        <head>
        <title>Title</title>
        </head>
            <body>
            <!-- Bootstrap Core CSS -->
            <spring:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" var="bootstrap"/>
            <link href="${bootstrap}" rel="stylesheet" />

            <!-- Custom CSS -->
            <spring:url value="/resources/styles/FrontPage.css" var="frontPage"/>
            <link href="${frontPage}" rel="stylesheet" />

            <!-- Custom Fonts -->
            <spring:url value="/resources/styles/FrontPage2.css" var="frontPage2"/>
            <link href="${frontPage2}" rel="stylesheet" />



            <!-- Custom Fonts -->
            <spring:url value="/resources/styles/test.css" var="test"/>
            <link href="${test}" rel="stylesheet" />

            <!-- Custom Fonts -->
            <spring:url value="/resources/styles/header.css" var="header"/>
            <link href="${header}" rel="stylesheet" />

            <!-- jQuery -->
            <spring:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" var="jqueryjs"/>
            <script src="${jqueryjs}"></script>

            <!-- Bootstrap Core JavaScript -->
            <spring:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" var="js"/>
            <script src="${js}"></script>

            <div class=" navbar navbar-default navbar-fixed-top" id="navbar-collapse-2">

                <ul class="nav navbar-nav">
                    <li><a href="<spring:url value="/welcome/home"/>">Home</a></li>
                    <li><a href="#">About</a></li>
                </ul>

                <ul class="nav navbar-nav navbar-right" style="padding: 0px 40px">
                    <li>
                        <a href="#"> My Orders <span class="badge">0</span></a>
                    </li>


                </ul>

            </div>

            <div class="row" style="padding-top: 55px;padding-left: 120px">
                <a href="<spring:url value="/welcome/home" />"
                   class="btn btn-default"> <span
                        class="glyphicon-hand-left glyphicon"></span> Continue shopping
                </a>
            </div><br>

                <div class="generic-container">
                    <div class="panel panel-default">

                        <div id="success" class="alert alert-success" style="display:block;">
                            Your order successfuly saved
                        </div>

                    </div>
                </div>
            </body>
    </html>
