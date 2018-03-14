<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<page:template>
    <jsp:attribute name="title">Java Books</jsp:attribute>

    <jsp:body>

        <section class="container">

            <form:form action="createBook" method="post" modelAttribute="newBook">

                <fieldset>
                    <legend>Add new product</legend>

                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="isbn">ISBN</label>

                        <div class="col-lg-10">
                            <form:input id="isbn" path="isbn" type="text" class="form:input-large" />
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="title">Title</label>
                        <div class="col-lg-10">
                            <form:input id="title" path="title" type="text" class="form:input-large" />
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="author">Author</label>
                        <div class="col-lg-10">
                            <form:input id="author" path="author" type="text" class="form:input-large" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="author1">Author2</label>
                        <div class="col-lg-10">
                            <form:input id="author1" path="author1" type="text" class="form:input-large" />
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="control-label col-lg-2" for="author2">Author3</label>
                        <div class="col-lg-10">
                            <form:input id="author2" path="author2" type="text" class="form:input-large" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2" for="author3">Author 4</label>
                        <div class="col-lg-10">
                            <form:input id="author3" path="author3" type="text" class="form:input-large" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2" for="releaseYear">Year</label>
                        <div class="col-lg-10">
                            <form:input id="releaseYear" path="releaseYear" type="text" class="form:input-large" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="categoryName">Category</label>
                        <div class="col-lg-10">
                            <form:input id="categoryName" path="categoryName" type="text" class="form:input-large" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="shortTitle">Short Title</label>
                        <div class="col-lg-10">
                            <form:input id="shortTitle" path="shortTitle" type="text" class="form:input-large" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="rentalDuration">Rental Duration</label>
                        <div class="col-lg-10">
                            <form:input id="rentalDuration" path="rentalDuration" type="text" class="form:input-large" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="rentalRate">Rental Rate</label>
                        <div class="col-lg-10">
                            <form:input id="rentalRate" path="rentalRate" type="text" class="form:input-large" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="replacementCost">Replacement Cost</label>
                        <div class="col-lg-10">
                            <form:input id="replacementCost" path="replacementCost" type="text" class="form:input-large" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="price">Price</label>
                        <div class="col-lg-10">
                            <form:input id="price" path="price" type="text" class="form:input-large" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2 col-lg-2" for="photo">photo</label>
                        <div class="col-lg-10">
                            <form:input id="photo" path="photo" type="text" class="form:input-large" />
                        </div>
                    </div>



                    <tr>
                        <td><input type="submit" value="Create" /></td>
                        <td></td>
                        <td></td>
                    </tr>
                </fieldset>
            </form:form>
        </section>

    </jsp:body>

</page:template>