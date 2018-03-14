
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>


 <page:template>
    <jsp:attribute name="title">Java Books</jsp:attribute>

    <jsp:body>
    <!-- Page Content -->


        <a href="<spring:url value="/login" />"/>
        <p>Log in as user</p>
        </a>

        <a href="<spring:url value="/login" />"/>
        <p>Log in as admin</p>
        </a>
        <div class="container">
        <!-- Page Heading/Breadcrumbs -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Java Zone </h1>
            </div>
        </div>

        <!-- /.row -->

        <!-- Intro Content -->
        <div class="row">
            <div class="col-md-6">
                <img class="img-responsive" src="${pageContext.request.contextPath}/resources/images/Duke3.png" alt="">
            </div>
            <div class="col-md-6">
                <h2>О приложении</h2>
                <p>Целью приложения является демонстрация возможностей Spring MVC, а так же использование с фрейморком таких технологий, как:</p>
                <p><b>HTML5</b> - языка для структурирования и представления содержимого всемирной паутины</p>
                <p><b>Bootstrap</b> - - интуитивно простой и в тоже время мощный интерфейсный фрейморк, повышающий скорость и облегчающий разработку web-приложений. </p>
                <p><b>AngularJS</b> - так же здесь демонстрируется работа популярного фреймворка в связке с Spring MVC.</p>
                <p>Здесь используется бесплатная тема <b>startbootstrap-modern-business-1.0.5</b>, найденная на просторах интернет. В теме 17 шаблонных страниц, но здесь используются не все.</p>
            </div>
        </div>
        <!-- /.row -->

        <!-- Team Members -->

        <div class="container">

            <a href="<spring:url value="/welcome/book/CategoriesAll" />"
            class="btn-group">
                <button type="button" class="btn btn-primary">Browse All</button>
            </a>

            <a href="<spring:url value="/welcome/book/categories?category=Spring" />"
             class="btn-group">
                <button type="button" class="btn btn-primary">Spring</button>
            </a>

            <a href="<spring:url value="/welcome/book/categories?category=OOP and Patterns" />"
            class="btn-group">
                <button type="button" class="btn btn-primary">OOP and Design Patterns</button>
            </a>

            <a href="<spring:url value="/welcome/book/categories?category=Java SE" />"
             class="btn-group">
                <button type="button" class="btn btn-primary">Java SE</button>
            </a>

            <a href="<spring:url value="/welcome/book/categories?category=Java EE" />"
             class="btn-group">
                <button type="button" class="btn btn-primary">Java EE</button>
            </a>

            <a href="<spring:url value="/welcome/book/categories?category=Hibernate" />"
            class="btn-group">
                <button type="button" class="btn btn-primary">Hibernate</button>
            </a>

            <a href="<spring:url value="/welcome/book/categories?category=Scala" />"
            class="btn-group">
                <button type="button" class="btn btn-primary">Scala</button>
            </a>


            <a href="<spring:url value="/welcome/book/categories?category=REST" />"
             class="btn-group">
                <button type="button" class="btn btn-primary">REST</button>
            </a>

            <a href="<spring:url value="/welcome/book/categories?category=Multithreading" />"
            class="btn-group">
                <button type="button" class="btn btn-primary">Multithreading</button>
            </a>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">Books on ${category} for everyone</h2>
            </div>
        </div>




<c:forEach items="${books}" var="book">
<div class="col-md-4 text-center">
                <div class="thumbnail">
                    <div class="carousel-inner" role="listbox">

                    <img  class="card-block text-nowrap" src="<spring:url value="/resources/images/${book.coverImage}.jpg"/>" alt="" width="200" height="300" >
                    <div class="caption">

                            <p>${book.price} $</p>
                        <p>${book.author.author1}</p>

                        <a href=" <spring:url value="/welcome/book?ISBN=${book.ISBN}" /> "

                                class="btn btn-primary"> <span
                                class="glyphicon-info-sign glyphicon"/></span> Details
                        </a>
                    </div>
                </div>
            </div>
</div>


</c:forEach>



    </div>
    <!-- /.container -->

    </jsp:body>

</page:template>
