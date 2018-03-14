<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>


<html>
<head>


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
</head>
<body>



<div class=" navbar navbar-default navbar-fixed-top" id="navbar-collapse-2">

    <ul class="nav navbar-nav">
        <li><a href="<spring:url value="/welcome/home"/>">Home</a></li>
        <li><a href="#">About</a></li>
    </ul>



</div>



<section class="container" style="padding-top: 15px">








            <div class="generic-container">
                <div class="panel panel-default">

                    <table class="table table-hover">
                        <thead>
                            <th></th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Category</th>
                            <th>Price</th>
                        </thead>

                        <tbody>


                        <c:forEach items="${searchResults}" var="result">

                            <tr>
                                <td> <img  class="card-block text-nowrap" src="<c:url value="/resources/images/${result.coverImage}.jpg"></c:url>" alt="" width="90" height="135" ></td>
                                <td>${result.bookTitle}</td>
                                <td>${result.author.author1}</td>
                                <td>${result.category.category}</td>
                                <td>${result.price}</td>



                                <a href="<spring:url value="/welcome/book?ISBN=${result.ISBN}" /> "
                                   class="btn btn-primary"> <span
                                        class="glyphicon-info-sign glyphicon"/></span> Details
                                </a>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                     </div>
                </div>

    </div>
    <div id="footer-wrapper">
        <footer id="footer">
            <p class="text-center" ><font size="2" face="verdana">(c) 2018 <a href=""> Vitalii Rubezhanskii</a></font> </p>
        </footer>
    </div>
</section>


</body>
</html>