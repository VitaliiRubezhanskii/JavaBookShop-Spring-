<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>


<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<script src="<c:url value='/resources/dir/js/controller/controller.js' />"></script>
</head>
<body>


<page:template>
<jsp:attribute name="title">Java Books</jsp:attribute>
<jsp:body>


<section class="container" ng-app="cartApp">
    <div ng-controller="cartCtrl" ng-init="initCartId('${cartId}')">

        <div>
            <a class="btn btn-danger pull-left" ng-click="clearCart()"> <span
                    class="glyphicon glyphicon-remove-sign"></span> Clear Cart
            </a> <a href="<spring:url value="/checkout?cartId=${cartId}"/>"
                    class="btn btn-success pull-right"> <span
                class="glyphicon-shopping-cart glyphicon"></span> Check out
        </a>
        </div>


        <c:if test="${!empty listItems}">
            <div class="generic-container">
                <div class="panel panel-default">
                    <table class="table table-hover">
                        <thead>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Category</th>
                        <th>Price</th>
                        <th>Quantity</th>

                        </thead>
                        <tbody>

                        <c:forEach items="${listItems}" var="item">

                            <tr>
                                <td>${item.book.bookTitle}</td>
                                <td>${item.book.author.author1}</td>
                                <td>${item.book.category.category}</td>
                                <td>${item.book.price}</td>



                                <c:url var="addAction"  value="/welcome/rest/order/save" ></c:url>
                              <form:form action="${addAction}" commandName="cartItem">
                                  <td> <form:input type="text" path="bookQuantity" size="1"/></td>
                                </form:form>



                                <c:if test="${!empty item.book.bookId}">
                                <td><a href="<c:url value='/welcome/rest/cart/edit/${item.book.bookId}' />" class="btn btn-success custom-width">edit</a></td>
                                <td><a href="<c:url value='/welcome/rest/cart/remove/${item.book.bookId}' />" class="btn btn-danger custom-width">delete</a></td>
                                </c:if>
                            </tr>


                        </c:forEach>


                        </tbody>
                    </table>

                </div>
            </div>
        </c:if>

<c:set var="total" value="${0}"/>

        <c:forEach var="x" items="${listItems}">
            <c:set var="total" value="${total+x.book.price*x.bookQuantity}"/>
        </c:forEach>

        <h3 class="text-center">Total: ${total}</h3>


        <a href="<spring:url value="/welcome/home" />"
           class="btn btn-default"> <span
                class="glyphicon-hand-left glyphicon"></span> Continue shopping
        </a>


        <a href="<c:url value='/welcome/rest/cart/remove/${item.book.bookId}' />"
           class="btn pull-right btn-warning custom-width">Place Order</a>
    </div>
</section>

</jsp:body>
</page:template>
</body>
</html>