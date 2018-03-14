<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section class="container">

    <form:form method="POST" modelAttribute="newBook" class="form-horizontal" enctype="multipart/form-data">

        <fieldset>
            <legend>Add new product</legend>

            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="isbn">ISBN</label>

                <div class="col-lg-10">
                    <form:input id="productId" path="isbn" type="text" class="form:input-large" />
                </div>
            </div>


            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="title">Title</label>
                <div class="col-lg-10">
                    <form:input id="name" path="title" type="text" class="form:input-large" />
                </div>
            </div>


            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="firstName">Author First Name</label>
                <div class="col-lg-10">
                    <form:input id="unitPrice" path="firstName" type="text" class="form:input-large" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="lastName">Author LAst Name</label>
                <div class="col-lg-10">
                    <form:input id="manufacturer" path="lastName" type="text" class="form:input-large" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="categoryName">Category</label>
                <div class="col-lg-10">
                    <form:input id="category" path="categoryName" type="text" class="form:input-large" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="price">Price</label>
                <div class="col-lg-10">
                    <form:input id="unitsInStock" path="price" type="text" class="form:input-large" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="releaseYear">Year</label>
                <div class="col-lg-10">
                    <form:textarea id="description" path="releaseYear" rows="2" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="rentalDuration">rentalDuration</label>
                <div class="col-lg-10">
                    <form:textarea id="description" path="rentalDuration" rows="2" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="rentalRate">rentalRate</label>
                <div class="col-lg-10">
                    <form:textarea id="description" path="rentalRate" rows="2" />
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="replacementCost">replacementCost</label>
                <div class="col-lg-10">
                    <form:textarea id="description" path="replacementCost" rows="2" />
                </div>
            </div>




            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" id="btnAdd" class="btn btn-primary"
                           value="Add" />
                </div>
            </div>
        </fieldset>
    </form:form>
</section>
