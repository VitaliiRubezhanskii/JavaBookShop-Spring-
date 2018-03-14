<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

   
  <!--  <spring:url value="/resources/dir/bootstrap-3.3.7/dist/css/bootstrap-theme.css" var="bootstrapThemeCss" />
   <spring:url value="/resources/dir/bootstrap-3.3.7/dist/css/bootstrap-theme.min.css" var="bootstrapThemeMinCss" />
   
    <spring:url value="/resources/dir/bootstrap-3.3.7/dist/css/bootstrap.css" var="bootstrapCss" />
    <spring:url value="/resources/dir/bootstrap-3.3.7/dist/css/bootstrap.min.css" var="bootstrapMinCss" />
   <spring:url value="/resources/dir/js/jquery-3.3.1.min.js" var="jqueryJs" />
   <spring:url value="/resources/dir/bootstrap-3.3.7/dist/js/bootstrap.min.js" var="bootstrapJs" />  --> 
   <spring:url value="/resources/dir/styles/main.css" var="mainCss" />   
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">


  
 <!--     <link href="${bootstrapThemeCss}" rel="stylesheet" />
     <link href="${bootstrapThemeMinCss}" rel="stylesheet" />
    <link href="${bootstrapMinCss}" rel="stylesheet" />
     <link href="${bootstrapCss}" rel="stylesheet" />
    <script src="${jqueryJs}"></script>
    <script src="${bootstrapJs}"></script>  -->
   <link href="${mainCss}" rel="stylesheet" />  
    
</head>


<body>


<!-- Navbar -->
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">

            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            
            <ul class="nav navbar-nav navbar-left">
                <li><a href="#">About</a></li>
                <li><a href="#"></a></li>
                <li><a href="#">WHAT</a></li>
                <li><a href="#">WHERE</a></li>
            </ul>
        </div>
     </div>
</nav>


<!-- First Container -->
<div class="container-fluid bg-1 text-center">
  <!--  <h3 class="first">JAVA BOOKS</h3>
    <h3 class="margin">FOR THOSE WHO LIKE JAVA</h3> -->   <!-- <img src="../../../resources/images/java-banner.png" class="img-responsive img-circle margin" style="display:inline" alt="Bird" width="300" height="500"> -->
    <img id="image" src="<c:url value="/resources/images/coder.jpg"></c:url>"
         alt="image" style="width: 15%"  class="img-responsive margin" />
</div>



<!-- Third Container (Grid) -->
<div class="container-fluid bg-3 text-center">
    <!-- <h3 class="margin">Where To Find Me?</h3><br> -->
    <div class="row">
        <c:forEach items="${products}" var="book">
        <img src="<c:url value="/resources/images/${book.photo}.jpg"></c:url>"
             alt="image" style="width: 15%" width="10" height="10" class="img-responsive margin" />
        <div class="col-sm-4">
            <p>${book.title}</p>

        </div>
        </c:forEach>
    </div>
</div>

<!-- Footer -->
<footer class="container-fluid bg-4 text-center">
    <p>© 2018 Java Books</p>
    <p>Created by Rubezhanskii Vitalii</p>
</footer>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</body>
</html>
