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

<div class="row" style="padding-top: 55px;padding-left: 120px">
    <a href="<spring:url value="/welcome/home" />"
       class="btn btn-default"> <span
            class="glyphicon-hand-left glyphicon"></span> Continue shopping
    </a>
</div>

<section class="container" style="padding-top: 15px">

    <c:if test="${empty listItems}">

        <h1>Your cart is empty</h1>
    </c:if>


    <c:if test="${!empty listItems}">
        <div>
            <a href="" class="btn btn-danger pull-left">
                <span class="glyphicon glyphicon-remove-sign"></span> Clear Cart
            </a>

            <a href="<spring:url value="/welcome/rest/checkout"/>"
                    class="btn btn-success pull-right">
                <span class="glyphicon-shopping-cart glyphicon"></span> Check out
        </a>
        </div>


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

                        <c:url var="addAction"  value="/welcome/rest/cart/saveOrder" ></c:url>
                        <form:form action="${addAction}" commandName="cartItem">

                        <c:forEach items="${listItems}" var="item">

                            <tr>
                                <td> <img  class="card-block text-nowrap" src="<c:url value="/resources/images/${item.book.coverImage}.jpg"></c:url>" alt="" width="90" height="135" ></td>
                                <td>${item.book.bookTitle}</td>
                                <td>${item.book.author.author1}</td>
                                <td>${item.book.category.category}</td>
                                <td>${item.book.price}</td>



                                <c:if test="${!empty item.book.bookId}">
                                <td><a href="<c:url value='/welcome/rest/cart/edit/${item.book.bookId}' />" class="btn btn-success custom-width">edit</a></td>
                                <td><a href="<c:url value='/welcome/rest/cart/remove/${item.book.bookId}' />" class="btn btn-danger custom-width">delete</a></td>
                                </c:if>
                            </tr>


                        </c:forEach>

                            <a href= "<spring:url value="/welcome/rest/cart/book?ISBN=${book.ISBN}"/>" >
                                <input type="submit" value="<spring:message text="Place Order" />"  class="btn btn-primary btn-sm" />
                            </a>




                        </form:form>
                        </tbody>
                    </table>


                </div>
                </div>

                </div>
            </div>


<c:set var="total" value="${0}"/>

        <c:forEach var="x" items="${listItems}">
            <c:set var="total" value="${total+x.book.price*x.book.bookQuantity}"/>
        </c:forEach>

        <h3 class="text-center">Total: ${total} $</h3>


        <a href="<spring:url value="/welcome/home" />"
           class="btn btn-default"> <span
                class="glyphicon-hand-left glyphicon"></span> Continue shopping
        </a>


    </div>
        </c:if>
    <div id="footer-wrapper">
        <footer id="footer">
            <p class="text-center" ><font size="2" face="verdana">(c) 2018 <a href=""> Vitalii Rubezhanskii</a></font> </p>
        </footer>
    </div>
</section>


</body>
</html>