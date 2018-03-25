<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<!-- Template by html.am -->
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>3 Column Layout</title>





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

	

	<style>
* {box-sizing: border-box}
body {font-family: "Lato", sans-serif;}

/* Style the tab */
.tab {
    float: left;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
    width: 120%;
    height: 600px;
	
}

/* Style the buttons inside the tab */
.tab button {
    display: block;
    background-color: inherit;
    color: black;
    padding: 15px 10px;
    width: 60%;
    border: none;
    outline: none;
    text-align: left;
    cursor: pointer;
    transition: 0.3s;
    font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
    background-color: #ddd;
}

/* Create an active/current "tab button" class */
.tab button.active {
    background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
    float: left;
    padding: 0px 0px;
    border: 1px solid #ccc;
    width: 80%;
    border-left: none;
    height: 200px
	}
	/* Style tab links */
.tablink {
    background-color: #555;
    color: white;
    float: left;
    border: none;
    outline: none;
    cursor: pointer;
    padding: 5px 6px;
    font-size: 17px;
    width: 25%;
}

.tablink:hover {
    background-color: #777;
}

/* Style the tab content (and add height:100% for full page content) */
.tabcontent {
    color: black;
    display: none;
    padding: 100px 20px;
    height: 100%;
}
	
</style>	
</head>

<body>

		  
		  

	
	
        <div class=" navbar navbar-default navbar-fixed-top" id="navbar-collapse-2">

		<ul class="nav navbar-nav">
			<li><a href="<spring:url value="/welcome/home"/>">Home</a></li>
			<li><a href="#">About</a></li>
		</ul>
		
          <ul class="nav navbar-nav navbar-right" style="padding: 0px 40px">
              <li>
                  <a href="#"> My Orders <span class="badge">0</span></a>
              </li>
              <li>

                  <a href="<spring:url value="/welcome/rest/cart/"/>"> My Cart <span class="badge">${countCartItems}</span></a>
              </li>
				
			
          </ul>

          </div>
		    <!-- Page Content -->

        <div class="container" style="padding-bottom: 50px">

        <!-- Page Heading/Breadcrumbs -->
        <div class="row" style="padding-top: 50px">
            <div class="col-lg-12">
                <h1 class="page-header">Java Books </h1>

            </div>
        </div>


        <div class="row">
            <div class="col-md-5" style="padding-bottom: 50px">
               <img  class="card-block text-nowrap" src="<c:url value="/resources/images/${book.coverImage}.jpg"></c:url>" alt="" width="300" height="450" >
            </div>

            <div class="col-md-5">
                <h3>Spring REST</h3>

                <p>
                    <strong>ISBN : </strong>${book.ISBN}
                </p>
                <p>
                    <strong>Title</strong> : ${book.bookTitle}
                </p>
				
				 <p>
                    <strong>Author(s)</strong> :${book.author.author1},${book.author.author2},${book.author.author3}
                </p>
                <p>
                    <strong>Category</strong> :${book.category.category}
                </p>
                <p>
                    <strong>Price </strong> : ${book.price} $
                </p>
                <p>
                    <strong>In Stock </strong> : ${book.inventoryStock} books avaliable
                </p>

                    <c:url var="addAction"  value="/welcome/rest/cart/book?ISBN=${book.ISBN}" ></c:url>
                    <form:form action="${addAction}" commandName="book">

                    <div class="row">
                        <label class="col-md-3 control-lable" for="bookQuantity"><p><strong>Quantity:</strong></p></label>
                <div class="col-md-7">
                    <form:input type="number" min="0" value="0" path="bookQuantity" size="1"  />
                </div>
            </div>


                            <div id="warning" class="alert alert-warning" style="display:none;">
                                <strong>Warning!</strong> This book already exists in the cart
                            </div>



                        <c:if test="${trigger}">
                            <input type="button" onclick="show()" value="<spring:message text="Add to cart" />"  class="btn btn-primary btn-sm" />
                        </c:if>

                        <c:if test="${not trigger}">
                            <a href= "<spring:url value="/welcome/rest/cart/book?ISBN=${book.ISBN}"/>" >
                                <input type="submit"  value="<spring:message text="Add to cart" />"  class="btn btn-primary btn-sm" />
                            </a>
                        </c:if>

                    </form:form>

                <br>
                <a href="<spring:url value="/welcome/home"/>"
                   class="btn btn-default"> <span
                        class="glyphicon-hand-left glyphicon"></span> back
                </a>


                    <a href="<spring:url value="/welcome/rest/cart/"/>" class="btn btn-default">
                    <span class="glyphicon-hand-right glyphicon"></span> View Cart
                </a>
            </div>
        </div>
		  
		
	<button class="tablink" onclick="openPage('About Book', this, '#00008B')">About Book</button>
	<button class="tablink" onclick="openPage('About Author', this, '#00008B')">About Author</button>
	
<div id="About Book" class="tabcontent" style="padding-top:30px;padding-bottom:30px">

    ${book.details}

</div>

<div id="About Author" class="tabcontent" style="padding-top:30px;padding-bottom:30px">
${book.author.aboutAuthor}

</div>

	<script>
function openPage(pageName,elmnt,color) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablink");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].style.backgroundColor = "";
    }
    document.getElementById(pageName).style.display = "block";
    elmnt.style.backgroundColor = color;

}
// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();


function show() {
    if(document.getElementById('warning').style.display=='none') {
        document.getElementById('warning').style.display='block';
    }
    return false;
}
    </script>

</body>

</html>