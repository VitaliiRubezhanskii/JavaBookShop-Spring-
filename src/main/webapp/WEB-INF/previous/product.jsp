<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>

<page:template>
    <jsp:attribute name="title">${book.bookTitle}</jsp:attribute>

    <jsp:body>
        <!-- Page Content -->
        <div class="container">

        <!-- Page Heading/Breadcrumbs -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Java Books </h1>

            </div>
        </div>


        <div class="row">
            <div class="col-md-5">
                <img src="<c:url value="/resources/images/${book.ISBN}.jpg"></c:url>"
                     alt="image" style="width: 70%" />
            </div>

            <div class="col-md-5">
                <h3>${book.bookTitle}</h3>

                <p>
                    <strong>ISBN : </strong>${book.ISBN}
                </p>
                <p>
                    <strong>Title</strong> : ${book.bookTitle}
                </p>
                <p>
                    <strong>Author</strong> : ${book.author.author1}
                </p>

                <p>
                    <strong>Category</strong> : ${book.category.category}
                </p>
                <p>
                    <strong>Price </strong> : ${book.price} $
                </p>






                    <c:url var="addAction"  value="/welcome/rest/cart/book?ISBN=${book.ISBN}" ></c:url>
                    <form:form action="${addAction}" commandName="book">

                <div class="row">
                    <label class="col-md-3 control-lable" for="bookQuantity"><p><strong>Quantity:</strong></p></label>
                        <div class="col-md-7">
                            <form:input type="text" path="bookQuantity" size="1"  />
                        </div>
                </div>

                    <a href= "<spring:url value="/welcome/rest/cart/book?ISBN=${book.ISBN}"/>" >
                        <input type="submit" value="<spring:message text="Add to cart" />"  class="btn btn-primary btn-sm" />
                </a>
                    </form:form>
            </form>
                <br>

                <p ng-controller="cartCtrl">
                    <a href="<spring:url value="/welcome/home" />"
                       class="btn btn-default"> <span
                            class="glyphicon-hand-left glyphicon"></span> Continue Shopping
                    </a>

                    <a href="<spring:url value="/welcome/rest/cart/" />" class="btn btn-default">
                    <span class="glyphicon-hand-right glyphicon"></span> View Cart
                </a>

                </p>
            </div>
        </div>


    </jsp:body>

</page:template>
