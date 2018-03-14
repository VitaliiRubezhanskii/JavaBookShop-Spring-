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
</head>



<body>
<page:template>
        <jsp:attribute name="title">Java Books</jsp:attribute>
        <jsp:body>

    <c:url var="addAction"  value="/welcome/rest/categories/create" ></c:url>


<div class="container">
    <div class="well lead">Add Category</div>

    <div class="form-group">
            <form:form action="${addAction}" commandName="newCategory">

              <div class="row">
                  <div class="form-group col-md-12">

                    <c:if test="${!empty newCategory.category}">
                      <div class="col-md-7">
                                 <label class="col-md-3 control-lable" for="categoryId">Category ID</label>
                                <form:input path="categoryId" readonly="true" size="8"  disabled="true"   />
                                <form:hidden path="categoryId" />
                    </c:if>
                      </div>
                  </div>
              </div>

                <div class="row">

                    <div class="form-group col-md-7">

                        <label class="col-md-3 control-lable" for="categoryId"><span class="lead" >Category</span></label>

                        <div class="col-md-7">
                            <form:input type="text" path="category" class="form-control input-sm" />
                        </div>


                        <div class="column">
                        <c:if test="${!empty newCategory.category}">
                            <input type="submit"   value="<spring:message text="Edit Category"/>"  class="btn btn-primary btn-sm"/>
                        </c:if>
                        <c:if test="${empty newCategory.category}">
                            <input type="submit" value="<spring:message text="Save" />"  class="btn btn-primary btn-sm" />
                        </c:if>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>

<br>




<c:if test="${!empty listCategories}">
<div class="generic-container">
    <div class="panel panel-default">
    <table class="table table-hover">
        <thead>
            <th>Category ID</th>
            <th>Category</th>
        </thead>
        <tbody>

        <c:forEach items="${listCategories}" var="category">
            <tr>
                <td>${category.categoryId}</td>
                <td>${category.category}</td>

                <td><a href="<c:url value='/welcome/rest/edit/${category.categoryId}' />" class="btn btn-success custom-width">edit</a></td>
                <td><a href="<c:url value='/welcome/rest/remove/${category.categoryId}' />" class="btn btn-danger custom-width">delete</a></td>
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
</body>
</html>
