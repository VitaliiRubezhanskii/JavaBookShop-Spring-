<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>

<html>
<head>
    <meta name="viewport" http-equiv="Content-Type" content="text/html, width=device-width,initial-scale=1.0"; charset=UTF-8">
    <title>JavaBooks</title>
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
            width: 35%;
            height: 800px;
        }

        /* Style the buttons inside the tab */
        .tab button {
            display: block;
            background-color: inherit;
            color: black;
            padding: 15px 15px;
            width: 100%;
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
            float: right;
            padding: 0px 25px;
            border: 1px solid #ccc;
            width: 88%;
            border-left: none;
            height: 3px
        }


    </style>
</head>

    <body>

        <!-- For login user -->
        <c:url value="/logout" var="logoutUrl" />
        <form action="${logoutUrl}" method="post" id="logoutForm">
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </form>

        <div class=" navbar navbar-default navbar-fixed-top" id="navbar-collapse-2">
            <ul class="nav navbar-nav navbar-left">
                    <li>
                        <c:if test="${pageContext.request.userPrincipal.name == null}">
                        <a href= "<spring:url value="/login"/>" >
                            <input type="submit" value="<spring:message text="Log In" />"  class="btn btn-default btn-outline btn-circle collapsed"  style="float:left"
                                   aria-expanded="false"   />
                        </a>
                        </c:if>
                        <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <a href= "<spring:url value="/logout"/>" >
                                <input type="submit" value="<spring:message text="Log Out" />"  class="btn btn-default btn-outline btn-circle collapsed"  style="float:left"
                                       aria-expanded="false"   />
                            </a>
                        </c:if>

                        <c:if test="${pageContext.request.userPrincipal.name == 'admin'}">
                            <a href= "<spring:url value="/welcome/admin/books"/>" >
                                <input type="submit" value="<spring:message text="Administrator" />"  class="btn btn-default btn-outline btn-circle collapsed"  style="float:left"
                                       aria-expanded="false"   />
                            </a>
                        </c:if>


                    </li>
                    <li>
                        <c:if test="${pageContext.request.userPrincipal.name == null}">
                        <a href= "<spring:url value="/register"/>" >
                            <input type="submit" value="<spring:message text="Register" />"  class="btn btn-default btn-outline btn-circle collapsed"  style="float:left"
                                   aria-expanded="false"   />
                        </a>
                        </c:if>
                    </li>
            </ul>



		
            <ul class="nav navbar-nav navbar-left">
                <li><a href="#">Home</a></li>
                <li><a href="#">About</a></li>

            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li>
                      <a href="#"> My Orders <span class="badge">0</span></a>
                </li>
                <li>
                        <a href="<spring:url value="/welcome/rest/cart/"/>"> ${pageContext.request.userPrincipal.name} Cart <span class="badge">${countCartItems}</span></a>
                </li>
            </ul>
          </div>
	
          <div class="tab" style="position:fixed; padding-top: 70px; width: 150px" >
              <button class="tablinks" onclick="openCity(event, 'Browse All')">Browse All</button>
              <button class="tablinks" onclick="openCity(event, 'Spring')" id="defaultOpen">Spring</button>
              <button class="tablinks" onclick="openCity(event, 'Hibernate')">Hibernate</button>
              <button class="tablinks" onclick="openCity(event, 'Java EE')">Java EE</button>
              <button class="tablinks" onclick="openCity(event, 'Java SE')">Java SE</button>
              <button class="tablinks" onclick="openCity(event, 'OOP and Patterns')">OOP</button>
              <button class="tablinks" onclick="openCity(event, 'Multithreading')">Multithreading</button>
              <button class="tablinks" onclick="openCity(event, 'Scala')">Scala</button>
              <button class="tablinks" onclick="openCity(event, 'REST')">REST</button>
		  </div>
		
    <div class="container" style="padding-bottom: 300px;padding-top: 48px">
        <div class="column">

            <div id="Spring" class="tabcontent">
                <h3 class="text-center">Spring</h3>
                <c:forEach items="${SpringBooks}" var="SpringBook">
                <div class="col-md-4 text-center">
                            <div class="thumbnail">
                                <div class="carousel-inner" role="listbox">
                                <img  class="card-block text-nowrap" src="<spring:url value="/resources/images/${SpringBook.coverImage}.jpg"/>" alt="" width="150" height="200" >
                                    <div class="caption">
                                        <p>Price: ${SpringBook.price}</p>
                                        <p>Author: ${SpringBook.author.author1}</p>
                                        <a href="<spring:url value="/welcome/book?ISBN=${SpringBook.ISBN}" /> "
                                                class="btn btn-primary"> <span
                                                class="glyphicon-info-sign glyphicon"/></span> Details
                                        </a>
                                    </div>
                                 </div>
                             </div>
                </div>
                 </c:forEach>
            </div>

            <div id="Hibernate" class="tabcontent">
              <h3 class="text-center">Hibernate</h3>
                <c:forEach items="${HibernateBooks}" var="HibernateBook">
                    <div class="col-md-4 text-center">
                        <div class="thumbnail">
                            <div class="carousel-inner" role="listbox">

                                <img  class="card-block text-nowrap" src="<spring:url value="/resources/images/${HibernateBook.coverImage}.jpg"/>" alt="" width="150" height="200" >
                                <div class="caption">

                                    <p>Price: ${HibernateBook.price}</p>
                                    <p>Author: ${HibernateBook.author.author1}</p>

                                    <a href="<spring:url value="/welcome/book?ISBN=${HibernateBook.ISBN}" /> "
                                       class="btn btn-primary"> <span
                                            class="glyphicon-info-sign glyphicon"/></span> Details
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div id="Java EE" class="tabcontent">
              <h3 class="text-center">Java EE</h3>
                <c:forEach items="${JavaEEbooks}" var="JavaEEbook">
                    <div class="col-md-4 text-center">
                        <div class="thumbnail">
                            <div class="carousel-inner" role="listbox">

                                <img  class="card-block text-nowrap" src="<spring:url value="/resources/images/${JavaEEbook.coverImage}.jpg"/>" alt="" width="150" height="200" >
                                <div class="caption">

                                    <p>Price: ${JavaEEbook.price}</p>
                                    <p>Author: ${JavaEEbook.author.author1}</p>

                                    <a href="<spring:url value="/welcome/book?ISBN=${JavaEEbook.ISBN}" /> "
                                       class="btn btn-primary"> <span
                                            class="glyphicon-info-sign glyphicon"/></span> Details
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div id="Java SE" class="tabcontent">
              <h3 class="text-center">Java SE</h3>
                <c:forEach items="${JavaSEbooks}" var="JavaSEbook">
                    <div class="col-md-4 text-center">
                        <div class="thumbnail">
                            <div class="carousel-inner" role="listbox">

                                <img  class="card-block text-nowrap" src="<spring:url value="/resources/images/${JavaSEbook.coverImage}.jpg"/>" alt="" width="150" height="200" >
                                <div class="caption">

                                    <p>Price: ${JavaSEbook.price}</p>
                                    <p>Author: ${JavaSEbook.author.author1}</p>

                                    <a href="<spring:url value="/welcome/book?ISBN=${JavaSEbook.ISBN}" /> "
                                       class="btn btn-primary"> <span
                                            class="glyphicon-info-sign glyphicon"/></span> Details
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div id="OOP and Patterns" class="tabcontent">
              <h3 class="text-center">OOP and Patterns</h3>
                <c:forEach items="${OOPbooks}" var="OOPbook">
                    <div class="col-md-4 text-center">
                        <div class="thumbnail">
                            <div class="carousel-inner" role="listbox">

                                <img  class="card-block text-nowrap" src="<spring:url value="/resources/images/${OOPbook.coverImage}.jpg"/>" alt="" width="150" height="200" >
                                <div class="caption">

                                    <p>Price: ${OOPbook.price}</p>
                                    <p>Author: ${OOPbook.author.author1}</p>

                                    <a href="<spring:url value="/welcome/book?ISBN=${OOPbook.ISBN}" /> "
                                       class="btn btn-primary"> <span
                                            class="glyphicon-info-sign glyphicon"/></span> Details
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div id="Multithreading" class="tabcontent">
              <h3 class="text-center">Multithreading</h3>
                <c:forEach items="${MultiBooks}" var="MultiBook">
                    <div class="col-md-4 text-center">
                        <div class="thumbnail">
                            <div class="carousel-inner" role="listbox">

                                <img  class="card-block text-nowrap" src="<spring:url value="/resources/images/${MultiBook.coverImage}.jpg"/>" alt="" width="150" height="200" >
                                <div class="caption">

                                    <p>Price: ${MultiBook.price}</p>
                                    <p>Author: ${MultiBook.author.author1}</p>

                                    <a href="<spring:url value="/welcome/book?ISBN=${MultiBook.ISBN}" /> "
                                       class="btn btn-primary"> <span
                                            class="glyphicon-info-sign glyphicon"/></span> Details
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div id="Scala" class="tabcontent">
                <h3 class="text-center">Scala</h3>
                <c:forEach items="${ScalaBooks}" var="ScalaBook">
                    <div class="col-md-4 text-center">
                        <div class="thumbnail">
                            <div class="carousel-inner" role="listbox">

                                <img  class="card-block text-nowrap" src="<spring:url value="/resources/images/${ScalaBook.coverImage}.jpg"/>" alt="" width="150" height="200" >
                                <div class="caption">

                                    <p>Price: ${ScalaBook.price}</p>
                                    <p>Author: ${ScalaBook.author.author1}</p>

                                    <a href="<spring:url value="/welcome/book?ISBN=${ScalaBook.ISBN}" /> "
                                       class="btn btn-primary"> <span
                                            class="glyphicon-info-sign glyphicon"/></span> Details
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div id="REST" class="tabcontent">
                <h3 class="text-center">REST</h3>
                <c:forEach items="${RESTbooks}" var="RESTbook">
                    <div class="col-md-4 text-center">
                        <div class="thumbnail">
                            <div class="carousel-inner" role="listbox">

                                <img  class="card-block text-nowrap" src="<spring:url value="/resources/images/${RESTbook.coverImage}.jpg"/>" alt="" width="150" height="200" >
                                <div class="caption">

                                    <p>Price: ${RESTbook.price}</p>
                                    <p>Author: ${RESTbook.author.author1}</p>

                                    <a href="<spring:url value="/welcome/book?ISBN=${RESTbook.ISBN}" /> "
                                       class="btn btn-primary"> <span
                                            class="glyphicon-info-sign glyphicon"/></span> Details
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div id="Browse All" class="tabcontent">
                <h3 class="text-center">All Books</h3>
                <c:forEach items="${AllBooks}" var="book">
                    <div class="col-md-4 text-center">
                        <div class="thumbnail">
                            <div class="carousel-inner" role="listbox">

                                <img  class="card-block text-nowrap" src="<spring:url value="/resources/images/${book.coverImage}.jpg"/>" alt="" width="150" height="200" >
                                <div class="caption">

                                    <p>Price: ${book.price}</p>
                                    <p>Author: ${book.author.author1}</p>

                                    <a href="<spring:url value="/welcome/book?ISBN=${book.ISBN}" /> "
                                       class="btn btn-primary"> <span
                                            class="glyphicon-info-sign glyphicon"/></span> Details
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

	<div id="footer-wrapper">
		<footer id="footer">
			<p class="text-center" ><font size="2" face="verdana">(c) 2018 <a href=""> Vitalii Rubezhanskii</a></font> </p>
		</footer>
	</div>
	
	<script>
    function openCity(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}
document.getElementById("defaultOpen").click();
// Get the element with id="defaultOpen" and click on it
    </script>

    </body>

</html>