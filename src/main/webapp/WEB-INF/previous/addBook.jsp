<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>

<html>
<head>
    <title>Admin</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/resources/dir/css/login.css'/>" rel="stylesheet"></link>
    <script src = "//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>


    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <script>
        $(function() {
            $("#datepicker").datepicker();
        });
    </script>
</head>



<body>

<page:template>
    <jsp:attribute name="title">Java Books</jsp:attribute>
    <jsp:body>

        <c:url var="addAction"  value="/welcome/admin/books/addBook" ></c:url>

        <a href="<spring:url value="/welcome/home" />"
           class="btn-group">
            <button type="button" class="btn btn-primary">Home</button>
        </a>

        <a href="<spring:url value="/welcome/book/categories?category=OOP and Patterns" />"
           class="btn-group">
            <button type="button" class="btn btn-primary">Add Category</button>
        </a>

        <a href="<spring:url value="/welcome/book/categories?category=Java SE" />"
           class="btn-group">
            <button type="button" class="btn btn-primary">View Sales</button>
        </a>

        <div class="container">
            <div class="well lead">Add Book</div>

            <div class="form-group">
                <form:form action="${addAction}" commandName="newBook">

                    <div class="row">
                        <div class="form-group col-md-12">

                            <c:if test="${!empty newBook.bookId}">
                            <div class="col-md-7">
                                <label class="col-md-3 control-lable" for="bookId">Book ID</label>
                                <form:input path="bookId" readonly="true" size="8"  disabled="true"   />
                                <form:hidden path="bookId" />


                            </div>
                            </c:if>
                        </div>
                    </div>



                    <!-- =========================================Title==============================================-->
                    <div class="row">
                        <div class="form-group col-md-7">
                            <label class="col-md-3 control-lable" for="bookTitle"><span class="lead" >Title</span></label>
                            <div class="col-md-7">
                                <form:input type="text" path="bookTitle" class="form-control input-sm" />
                            </div>
                        </div>
                    </div>

                    <!-- =========================================Cover==============================================-->
                    <div class="row">
                        <div class="form-group col-md-7">
                            <label class="col-md-3 control-lable" for="coverImage"><span class="lead" >CoverImage</span></label>
                            <div class="col-md-7">
                                <form:input type="text" path="coverImage" class="form-control input-sm" />
                            </div>
                        </div>
                    </div>

                    <!-- =========================================Price==============================================-->
                    <div class="row">
                        <div class="form-group col-md-7">
                            <label class="col-md-3 control-lable" for="price"><span class="lead" >Price</span></label>
                            <div class="col-md-7">
                                <form:input type="text" path="price" class="form-control input-sm" />
                            </div>
                        </div>
                    </div>

                    <!-- =========================================Category===========================================-->
                    <div class="row">
                        <div class="form-group col-md-7">
                            <label class="col-md-3 control-lable" for="category.category"><span class="lead">Category</span></label>
                            <div class="col-md-7">
                                <form:input type="text" path="category.category" class="form-control input-sm" />
                            </div>
                        </div>
                    </div>

                    <!-- =========================================Year===========================================-->
                    <div class="row">
                        <div class="form-group col-md-7">
                            <label class="col-md-3 control-lable" for="publishedYear"><span class="lead">Year</span></label>
                            <div class="col-md-7">
                                <form:input type="text" path="publishedYear" class="form-control input-sm" />
                            </div>
                        </div>
                    </div>

                    <!-- =========================================Publisher===========================================-->
                    <div class="row">
                        <div class="form-group col-md-7">
                            <label class="col-md-3 control-lable" for="publisher"><span class="lead">Publisher</span></label>
                            <div class="col-md-7">
                                <form:input type="text" path="publisher" class="form-control input-sm" />
                            </div>
                        </div>
                    </div>

                    <!-- =========================================ISBN===========================================-->
                    <div class="row">
                        <div class="form-group col-md-7">
                            <label class="col-md-3 control-lable" for="ISBN"><span class="lead">ISBN</span></label>
                            <div class="col-md-7">
                                <form:input type="text" path="ISBN" class="form-control input-sm" />
                            </div>
                        </div>
                    </div>

                    <!-- =========================================Language===========================================-->
                    <div class="row">
                        <div class="form-group col-md-7">
                            <label class="col-md-3 control-lable" for="language"><span class="lead">Language</span></label>
                            <div class="col-md-7">
                                <form:input type="text" path="language" class="form-control input-sm" />
                            </div>
                        </div>
                    </div>

                    <!-- =========================================Details===========================================-->
                     <div class="row">
                        <div class="form-group col-md-7">
                            <label class="col-md-3 control-lable" for="details"><span class="lead" >Details</span></label>
                            <div class="col-md-7">
                                <form:input type="text" path="details" class="form-control input-sm" />
                            </div>


                            <div class="column">
                                <c:if test="${!empty newBook.bookTitle}">
                                    <input type="submit"   value="<spring:message text="Edit Category"/>"  class="btn btn-primary btn-sm"/>
                                </c:if>
                                <c:if test="${empty newBook.bookTitle}">
                                    <input type="submit" value="<spring:message text="Save" />"  class="btn btn-primary btn-sm" />
                                </c:if>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
        <br>



        <c:if test="${!empty listBooks}">
            <div class="generic-container">
                <div class="panel panel-default">
                    <table class="table table-hover">
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

                                <td><a href="<c:url value='/welcome/rest/books/edit/${book.bookId}' />" class="btn btn-success custom-width">edit</a></td>
                                <td><a href="<c:url value='/welcome/rest/books/remove/${book.bookId}' />" class="btn btn-danger custom-width">delete</a></td>
                            </tr>


                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>
    </jsp:body>
</page:template>


<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script src="<c:url value='/resources/dir/js/app.js' />"></script>
<script src="<c:url value='/resources/dir/js/user_service.js' />"></script>
<script src="<c:url value='/resources/dir/js/user_controller.js' />"></script>
<script src="<c:url value='/resources/dir/js/jquery-2.1.4.min.js' />"></script>
</body>
</html>
