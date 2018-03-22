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
</head>




<body>
<div class=" navbar navbar-default navbar-fixed-top" id="navbar-collapse-2">

    <ul class="nav navbar-nav" style="text-center">
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

<div class="row" style="padding-top: 55px;padding-left: 120px">
    <a href="<spring:url value="/welcome/home" />"
       class="btn btn-default"> <span
            class="glyphicon-hand-left glyphicon"></span> Continue shopping
    </a>
</div>

        <c:url var="addAction"  value="/welcome/rest/saveCustomer" ></c:url>





        <div id="formgroup"  class="container" style="padding-bottom: 80px;display:block" >
            <div class="well lead">Checkout</div>



            <div class="form-group" >
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
                    <div  class="row">
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

                                    <input type="submit" onclick="show()" value="<spring:message text="Save" />"  class="btn btn-primary btn-sm" />

                            </div>
                        </div>
                    </div>

                </form:form>

            </div>



            <div id="footer-wrapper">
                <footer id="footer">
                    <p class="text-center" ><font size="2" face="verdana">(c) 2018 <a href=""> Vitalii Rubezhanskii</a></font> </p>
                </footer>
            </div>
        </div>

<script>

    function show() {

        if(document.getElementById('success').style.display=='none') {
            document.getElementById('success').style.display='block';
        }
        hide();
        return false;
    }
    function  hide() {
        if(document.getElementById('formgroup').style.display=='block') {
            document.getElementById('formgroup').style.display='none';
        }
        return false;

    }
</script>

</body>
</html>