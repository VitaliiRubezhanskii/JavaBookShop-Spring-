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



    <c:url var="act"  value="/welcome/rest/order/searchResult" ></c:url>
        <form:form modelAttribute="order" action="${act}" method="get" class="form-horizontal"
                   id="search-owner-form">


        <div class="form-group">

            <div class="control-group" id="lastName">
                <label class="col-sm-2 control-label">Last name </label>
                <div class="col-sm-10">
                    <form:input class="form-control" path="customer.lastName" size="30" maxlength="80"/>
                    <span class="help-inline"><form:errors path="*"/></span>
                </div>
            </div>
        </div>

            <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Find Owner</button>
            </div>
            </div>

        <br>
        </form:form>


        <c:if test="${!empty listOrders}">

            <div class="generic-container">
                <div class="panel panel-default">
                    <table class="table table-hover">
                        <thead>
                        <th>Global ID</th>
                        <th>Customer First Name</th>
                        <th>Customer Last Name</th>
                        <th>Country</th>
                        <th>City</th>
                        <th>Address</th>
                        <th>Status</th>
                        </thead>
                        <tbody>

                        <c:forEach items="${listOrders}" var="order">
                            <tr>
                                <td>${order.globalId}</td>
                                <td>${order.customer.firstName}</td>
                                <td>${order.customer.lastName}</td>
                                <td>${order.customer.country}</td>
                                <td>${order.customer.city}</td>
                                <td>${order.customer.address}</td>



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
