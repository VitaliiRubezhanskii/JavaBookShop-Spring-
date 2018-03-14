<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
    <title>Create new customer</title>
    <style>
        .username.ng-valid {
            background-color: lightgreen;
        }
        .username.ng-dirty.ng-invalid-required {
            background-color: red;
        }
        .username.ng-dirty.ng-invalid-minlength {
            background-color: yellow;
        }

        .email.ng-valid {
            background-color: lightgreen;
        }
        .email.ng-dirty.ng-invalid-required {
            background-color: red;
        }
        .email.ng-dirty.ng-invalid-email {
            background-color: yellow;
        }

    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/resources/dir/css/login.css'/>" rel="stylesheet"></link>
</head>

<body ng-app="myApp" class="ng-cloak">


<div class="generic-container" ng-controller="BookController as ctrl">

    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">Book Form </span></div>

        <div class="formcontainer">
            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">



                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="isbn">ISBN</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.book.isbn" id="isbn" class="form-control input-sm" placeholder="Enter your country. [This field is validation free]"/>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="title">Title</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.book.title" id="title" class="form-control input-sm" placeholder="Enter your country. [This field is validation free]"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="author">Author 1</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.book.author" id="author" class="form-control input-sm" placeholder="Enter your City. [This field is validation free]"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="author1">Author 2</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.book.author1" id="author1" class="form-control input-sm" placeholder="Enter your City. [This field is validation free]"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="author2">Author 3</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.book.author2" id="author2" class="form-control input-sm" placeholder="Enter your City. [This field is validation free]"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="author3">Author4</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.book.author3" id="author3" class="form-control input-sm" placeholder="Enter your City. [This field is validation free]"/>
                        </div>
                    </div>
                </div>



                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="categoryName">Category</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.book.categoryName" id="categoryName" class="form-control input-sm" placeholder="Enter your phone. [This field is validation free]"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="price">Price</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.book.price" id="price" class="form-control input-sm" placeholder="Enter your Address. [This field is validation free]"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="photo">Photo</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.book.photo" id="photo" class="form-control input-sm" placeholder="Enter your Address. [This field is validation free]"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="releaseYear">Release Year</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.book.releaseYear" id="releaseYear" class="form-control input-sm" placeholder="Enter your Address. [This field is validation free]"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="rentalDuration">Rental Duration</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.book.rentalDuration" id="rentalDuration" class="form-control input-sm" placeholder="Enter your Address. [This field is validation free]"/>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="replacementCost">Replacement Cost</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.book.replacementCost" id="replacementCost" class="form-control input-sm" placeholder="Enter your Address. [This field is validation free]"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-lable" for="shortTitle">Short Title</label>
                        <div class="col-md-7">
                            <input type="text" ng-model="ctrl.book.shortTitle" id="shortTitle" class="form-control input-sm" placeholder="Enter your Address. [This field is validation free]"/>
                        </div>
                    </div>
                </div>




                <div class="row">
                    <div class="form-actions floatRight">
                        <input type="submit"  value="Add" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                    </div>
                </div>


            </form>


        </div>
    </div>



    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Books </span></div>
        <div class="tablecontainer">
            <table class="table table-hover">

                <thead>
                <tr>
                    <th>ID.</th>
                    <th>Name</th>
                    <th>Country</th>
                    <th>City</th>
                    <th>District</th>
                    <th>Postal Code</th>
                    <th>Address</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th width="20%"></th>
                </tr>
                </thead>

                <tbody>
                <tr ng-repeat="u in ctrl.users">
                    <td><span ng-bind="u.id"></span></td>
                    <td><span ng-bind="u.username"></span></td>
                    <td><span ng-bind="u.address"></span></td>
                    <td><span ng-bind="u.email"></span></td>
                    <td>
                        <button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script src="<c:url value='/resources/dir/js/app.js' />"></script>
<script src="<c:url value='/resources/dir/js/user_service.js' />"></script>
<script src="<c:url value='/resources/dir/js/user_controller.js' />"></script>
</body>
</html>
