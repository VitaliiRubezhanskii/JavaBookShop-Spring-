<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {box-sizing: border-box}

/* Set height of body and the document to 100% */
body, html {
    height: 100%;
    margin: 0;
    font-family: Arial;
}

/* Style tab links */
.tablink {
    background-color: #555;
    color: white;
    float: left;
    border: none;
    outline: none;
    cursor: pointer;
    padding: 14px 16px;
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

#Home {background-color: white;}
#News {background-color: white;}
#Contact {background-color: white;}
#About {background-color: white;}
</style>


    <!-- Bootstrap Core CSS -->
    <spring:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" var="bootstrap"/>
    <link href="${bootstrap}" rel="stylesheet" />

    <!-- jQuery -->
    <spring:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js" var="jqueryjs"/>
    <script src="${jqueryjs}"></script>

    <!-- jQuery -->
    <spring:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.tablesorter.js" var="tablesorter"/>
    <script src="${tablesorter}"></script>

    <!-- Bootstrap Core JavaScript -->
    <spring:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" var="js"/>
    <script src="${js}"></script>


</head>
<body>

<div class=" navbar navbar-default navbar-fixed-top" id="navbar-collapse-2">

    <ul class="nav navbar-nav navbar-left">
        <li><a href="<c:url value='/welcome/home'/>">Home</a></li>
        <li><a href="#">About</a></li>

    </ul>
</div>

<div class="buttons" style="padding-top: 70px">
    <button class="tablink" onclick="openPage('Books', this, '#00008B')" id="defaultOpen">Books</button>
    <button class="tablink" onclick="openPage('Sales', this, '#00008B')">Sales</button>
</div>
    <div id="Books" class="tabcontent">


    <div class="container">


        <c:url var="addAction"  value="/welcome/admin/books/addBook" ></c:url>
        <form:form action="${addAction}" commandName="newBook">

           <div style="padding-top: 15px">

                <a href="<c:url value='/welcome/admin/books.xls' />">
                    Download Stock in Excel

                </a>

           </div>

            <div class="well lead" style="padding-top: 15px; text-align: center">Add Book</div>

		<form>

		 <div class="form-group">
                <label class="col-md-3 control-lable" for="ISBN"><span class="lead" >ISBN</span></label>
                <form:input type="text" path="ISBN" class="form-control input-sm" />
            </div>

            <div class="form-group">
                <label class="col-md-3 control-lable" for="bookTitle"><span class="lead" >Title</span></label>
                <form:input type="text" path="bookTitle" class="form-control input-sm" />
            </div>
			
			
			<div class="form-group">
                <label class="col-md-3 control-lable" for="author.author1"><span class="lead" >Author</span></label>
                <form:input type="text" path="author.author1" class="form-control input-sm" />
            </div>
			
            <div class="form-group">
                <label class="col-md-3 control-lable" for="category.category"><span class="lead" >Category</span></label>
                <form:input type="text" path="category.category" class="form-control input-sm" />
            </div>
			
			 <div class="form-group">
                <label class="col-md-3 control-lable" for="publisher"><span class="lead" >Publisher</span></label>
                <form:input type="text" path="publisher" class="form-control input-sm" />
            </div>
			
			 <div class="form-group">
                    <label class="col-md-3 control-lable" for="language"><span class="lead" >Language</span></label>
                    <form:input type="text" path="language" class="form-control input-sm" />
            </div>
			
			 <div class="form-group">
                <label class="col-md-3 control-lable" for="price"><span class="lead" >Price</span></label>
                <form:input type="text" path="price" class="form-control input-sm" />
            </div>
			
			 <div class="form-group">
                <label class="col-md-3 control-lable" for="inventoryStock"><span class="lead" >In Stock</span></label>
                <form:input type="text" path="inventoryStock" class="form-control input-sm" />
            </div>

            <div class="form-group">
                <label for="details"><span class="lead">Details</span></label>
               <!-- <textarea id="subject" name="subject" placeholder="Write something.." style="height:200px; width:1150px; border-radius: 5px; padding: 20px;"></textarea> -->

                <form:textarea type="text" path="details" placeholder="Write something.." class="form-control input-sm" />
            </div>

            <div class="column">
            <c:if test="${!empty newBook.bookTitle}">
                <input type="submit"   value="<spring:message text="Edit Category"/>"  class="btn btn-primary btn-sm"/>
            </c:if>
            <c:if test="${empty newBook.bookTitle}">
                <input type="submit" value="<spring:message text="Save" />"  class="btn btn-primary btn-sm" />
            </c:if>
            </div>
			
       </form>
	   
	   


</form:form>
       
        <br>
            <div class="generic-container">
                <div class="panel panel-default">
                    <table class="table table-hover tablesorter" id="myTable">
                        <thead>
                        <th>Book ID</th>
                        <th>ISBN</th>
                        <th>Title</th>
                        <th>Publisher</th>
                        <th>Language</th>
                        <th>Details</th>
                        </thead>
                        <tbody>

                        <c:forEach items="${listBooks}" var="book">
                            <tr>
                                <td>${book.bookId}</td>
                                <td>${book.ISBN}</td>
                                <td>${book.bookTitle}</td>
                                <td>${book.publisher}</td>
                                <td>${book.language}</td>
                                <td>${book.details}</td>

                                <td><a href="" class="btn btn-success custom-width">edit</a></td>
                                <td><a href="" class="btn btn-danger custom-width">delete</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
</div>








<div id="Sales" class="tabcontent">
  
            <div class="well lead">Sales and Orders</div>
        <div class="generic-container">
			<div class="generic-container">
                <div class="panel panel-default">
                    <table class="table table-hover">
                        <thead>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Cover</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>

                        </thead>
                        <tbody>


                            <c:set var="total" value="${x.book.price*x.book.bookQuantity}"/>


                        <c:forEach items="${orders}" var="order">
                            <c:set var="total" value="${order.book.price*order.book.bookQuantity}"/>
                            <tr>
                                <td>${order.customer.firstName}</td>
                                <td>${order.customer.lastName}</td>
                                <td>${order.customer.email}</td>
                                <td><img  class="card-block text-nowrap" src="<c:url value="/resources/images/${order.book.coverImage}.jpg"></c:url>" alt="" width="90" height="135" ></td>
                                <td>${order.book.bookTitle}</td>
                                <td>${order.book.bookQuantity}</td>
                                <td>${order.book.price}</td>
                                <td>${total}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <c:set var="total" value="${0}"/>

                    <c:forEach var="x" items="${orders}">
                        <c:set var="total" value="${total+x.book.price*x.book.bookQuantity}"/>
                    </c:forEach>

                    <h3 class="text-center">Total: ${total} $</h3>

                </div>
            </div>
         </div>
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
</script>
     
</body>
</html> 
