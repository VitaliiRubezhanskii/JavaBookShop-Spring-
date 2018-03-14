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

    <c:url var="addAction"  value="/welcome/rest/books/addBook" ></c:url>


<div class="container">
    <div class="well lead">Checkout</div>

    <div class="form-group">
        <form:form action="${addAction}" commandName="customer">

            <div class="row">
                <div class="form-group col-md-12">

                    <c:if test="${!empty customer.customerId}">
                    <div class="col-md-7">
                        <label class="col-md-3 control-lable" for="customerId">ID</label>
                        <form:input path="customerId" readonly="true" size="8"  disabled="true"   />
                        <form:hidden path="customerId" />
                        </c:if>
                    </div>
                </div>
            </div>

            <!-- =========================================First Name================================================-->
            <div class="row">
                <div class="form-group col-md-7">
                    <label class="col-md-3 control-lable" for="firstName"><span class="lead" >First Name</span></label>
                    <div class="col-md-7">
                        <form:input type="text" path="firstName" class="form-control input-sm" />
                    </div>
                </div>
            </div>

            <!-- =========================================Last Name==============================================-->
            <div class="row">
                <div class="form-group col-md-7">
                    <label class="col-md-3 control-lable" for="lastName"><span class="lead" >Last Name</span></label>
                    <div class="col-md-7">
                        <form:input type="text" path="lastName" class="form-control input-sm" />
                    </div>
                </div>
            </div>

            <!-- =========================================Address==============================================-->
            <div class="row">
                <div class="form-group col-md-7">
                    <label class="col-md-3 control-lable" for="address"><span class="lead" >Address</span></label>
                    <div class="col-md-7">
                        <form:input type="text" path="address" class="form-control input-sm" />
                    </div>
                </div>
            </div>

            <!-- =========================================City===========================================-->
            <div class="row">
                <div class="form-group col-md-7">
                    <label class="col-md-3 control-lable" for="city"><span class="lead">City</span></label>
                    <div class="col-md-7">
                        <form:input type="text" path="city" class="form-control input-sm" />
                    </div>
                </div>
            </div>

            <!-- =========================================Zip===========================================-->
            <div class="row">
                <div class="form-group col-md-7">
                    <label class="col-md-3 control-lable" for="zip"><span class="lead">Zip</span></label>
                    <div class="col-md-7">
                        <form:input type="text" path="zip" class="form-control input-sm" />
                    </div>
                </div>
            </div>

            <!-- =========================================Country===========================================-->
            <div class="row">
                <div class="form-group col-md-7">
                    <label class="col-md-3 control-lable" for="country"><span class="lead">Country</span></label>
                    <div class="col-md-7">
                        <form:input type="text" path="country" class="form-control input-sm" />
                    </div>
                </div>
            </div>

            <!-- =========================================Phone Home===========================================-->
            <div class="row">
                <div class="form-group col-md-7">
                    <label class="col-md-3 control-lable" for="phoneHome"><span class="lead">Phone Home</span></label>
                    <div class="col-md-7">
                        <form:input type="text" path="phoneHome" class="form-control input-sm" />
                    </div>
                </div>
            </div>
`
            <!-- =========================================Phone Mobile===========================================-->
            <div class="row">
                <div class="form-group col-md-7">
                    <label class="col-md-3 control-lable" for="phoneMobile"><span class="lead">Phone Mobile</span></label>
                    <div class="col-md-7">
                        <form:input type="text" path="phoneMobile" class="form-control input-sm" />
                    </div>
                </div>
            </div>

            <!-- =========================================Email===========================================-->
            <div class="row">
                <div class="form-group col-md-7">
                    <label class="col-md-3 control-lable" for="email"><span class="lead" >Email</span></label>
                    <div class="col-md-7">
                        <form:input type="text" path="email" class="form-control input-sm" />
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

</jsp:body>
</page:template>
</body>
</html>