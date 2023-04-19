<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- for forms -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!-- for validation -->
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"> 
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <!-- My CSS -->
    <link rel='stylesheet' href='/css/styles.css'>
    <!-- JS for Bootstrap / jQuery -->
    <script src='/webjars/jquery/jquery.min.js'>    </script>
    <script src='/webjars/bootstrap/js/boostrap.min.js'>    </script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous">    </script>
    <!-- My JS -->
    <script type = "text/javascript" src='/js/scripts.js'></script>
    <meta charset="UTF-8">

    <!-- Title -->
    <title>${cafe.name}</title>
</head>
<body>
    <!-- HEADER -->
    <header class="text-center m-3">
        <h1><a class="" href="/cafe" style="text-decoration: none;">${cafe.name}</a></h1>
        <ul class="nav nav-pills">
            <li class="nav-item">
                <a class="nav-link" href="/coffee">Featured Coffee</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/cafe/menu">Menu</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/cafe/puzzle">Monthly Puzzle</a>
            </li>
            <!-- Make sure manager is not signed in -->
            <c:if test="${ !userTYPE.equals('Manager') }">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Subscription</a>
                    <div class="dropdown-menu">
                        <!-- Not logged in -->
                        <c:if test="${ userID == null }">
                            <a class="dropdown-item" href="/subs/login">Login</a>
                            <a class="dropdown-item" href="/subs/register">Sign Up</a>
                        </c:if>
                        <!-- Logged in -->
                        <c:if test="${ userID != null }">
                            <a class="dropdown-item" href="/cafe/coupons">See Avaliable Coupons</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/logout">Logout</a>
                        </c:if>
                    </div>
                </li>
            </c:if>
            <!-- <li class="nav-item">
                <a class="nav-link disabled" href="#">Disabled</a>
            </li> -->
        </ul>
    </header>
    <!-- MAIN -->
    <main class="m-3">
        <h2>Update a Drink</h2>
        <form:form action="/drink/${drink.id}/updateDrink" method="post" modelAttribute="updateForm" class="d-flex flex-column ms-3 w-75">
            <input type="hidden" name="_method" value="put" />
            <!-- String name -->                
            <div class="my-2">
                <legend class="w-auto me-4">Name:</legend>
                <input type="text" name="name" value="${ drink.name }">
                <!-- Validation Error -->
                <form:errors path="name" class="text-warning ms-2"/>
            </div>
            <!-- String description --> 
            <div class="my-2">
                <legend class="w-auto me-4">Description:</legend>
                <input type="text" name="description" value="${ drink.description }">
                <!-- Validation Error -->
                <form:errors path="description" class="text-warning ms-2"/>
            </div>
            <!-- String ingredients --> 
            <div class="d-flex flex-row my-2">
                <legend class="w-auto me-4">Ingredients:</legend>
                <input type="text" name="ingredients" value="${ drink.ingredients }">
                <!-- Validation Error -->
                <form:errors path="ingredients" class="text-warning ms-2"/>
            </div>
            <!-- Boolean --> 
            <div class="d-flex flex-row align-items-center my-2">
                <legend class="w-auto me-4">Temperature:</legend>
                <!-- Boolean isHot -->
                <div class="d-flex flex-column border p-1 me-2">
                    <label for="">Hot?</label>
                    <fieldset class="my-2">
                    <!-- This one works: -->
                    <c:if test="${drink.isHot}">
			            <label for="isHot">Yes</label>
			            <input type="radio" name="isHot" value="${true}" checked>
			            <label for="isHot">No</label>
			            <input type="radio" name="isHot" value="${false}">
			        </c:if>
			        <c:if test="${!drink.isHot}">
			            <label for="isHot">Yes</label>
			            <input type="radio" name="isHot" value="${true}">
			            <label for="isHot">No</label>
			            <input type="radio" name="isHot" value="${false}" checked>
			        </c:if>
                     <!-- End of, this one works: -->
                    </fieldset>
                    <!-- Validation Error -->
                    <form:errors path="isHot" class="text-warning ms-2"/>
                </div>
                <!-- Boolean isCold -->
                <div class="d-flex flex-column border p-1 ms-2">
                    <label for="">Cold?</label>
                    <fieldset class="my-2">
                    <!-- This one works: -->
                    <c:if test="${drink.isCold}">
			            <label for="isCold">Yes</label>
			            <input type="radio" name="isCold" value="${true}" checked>
			            <label for="isCold">No</label>
			            <input type="radio" name="isCold" value="${false}">
			        </c:if>
			        <c:if test="${!drink.isCold}">
			            <label for="isCold">Yes</label>
			            <input type="radio" name="isCold" value="${true}">
			            <label for="isCold">No</label>
			            <input type="radio" name="isCold" value="${false}" checked>
			        </c:if>
                     <!-- End of, this one works: -->
				</fieldset>
                    <!-- Validation Error -->
                    <form:errors path="isCold" class="text-warning ms-2"/>
                </div>
            </div>
            <!-- Double price --> 
            <div class="d-flex flex-row align-items-center my-2">
                <legend class="me-2">Prices:</legend>
                <div class="d-flex flex-column mx-1">
                    <label for="bytePrice">Byte</label>
                    <input type="number" step=".01" name="bytePrice" value="${ drink.bytePrice }">
                    <!-- Validation Error -->
                    <form:errors path="bytePrice" class="text-warning ms-2"/>
                </div>
                <div class="d-flex flex-column mx-1">
                    <label for="intPrice">Integer</label>
                    <input type="number" step=".01" name="intPrice" value="${ drink.intPrice }">
                    <!-- Validation Error -->
                    <form:errors path="intPrice" class="text-warning"/>
                </div>
                <div class="d-flex flex-column mx-1">
                    <label for="longPrice">Long</label>
                    <input type="number" step=".01" name="longPrice" value="${ drink.longPrice }">
                    <!-- Validation Error -->
                    <form:errors path="longPrice" class="text-warning ms-2"/>
                </div>
                
            </div>
			<!-- Coffee coffee --> 
            <div class="my-2">
                <legend class="w-auto me-4">Coffee:</legend>
                <select name="coffee" id=""> 
                    <option name="coffee" selected hidden disabled > <c:out value="${ drink.coffee.region }; ${ drink.coffee.flavors }; ${ drink.coffee.roastType() } "></c:out> </option>                      
                    <c:forEach items="${allCoffee}" var="c">
                        <option value="${c.id}">${c.region}; ${c.flavors}; ${c.roastType()}</option>
                    </c:forEach>
                </select>
                <!-- Validation Error -->
                <form:errors path="coffee" class="text-warning ms-2"/>
            </div>
            <!-- Cafe cafe --> 
            <!-- <div class="d-flex flex-row justify-content-between align-items-center my-2">
                <label for="cafe">Do you want to add to the avaliable menu?</label>
                <fieldset class="my-2">
                    <label for="cafe">Yes</label>
                    <input type="radio" name="cafe" value="${cafeID}">
                    <label for="cafe">No</label>
                    <input type="radio" name="cafe" value="${null}" checked>
                </fieldset>
            </div> -->
            <input type="hidden" name="cafe" value="${cafeID}" />
            <!-- Submission button -->
            <div class=""><button class="btn btn-primary">Update Drink</button></div>
        </form:form>
    </main>
    <!-- FOOTER -->
    <footer class="m-3">
        <ul class="nav nav-pills justify-content-end">
            <!-- Make sure subscriber is not signed in -->
            <c:if test="${ !userTYPE.equals('Subscriber') }">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Manager</a>
                    <div class="dropdown-menu">
                        <c:if test="${ userID == null }">
                            <a class="dropdown-item" href="/mana/login">Login</a>
                            <a class="dropdown-item" href="/mana/register">Register</a>
                        </c:if>
                        <c:if test="${ userID != null }"> <!-- && userTYPE.equals('Manager') -->
                            <a class="dropdown-item" href="/cafe/coffee/edit">Change Coffee of the Month</a>
                            <a class="dropdown-item" href="/drink/create">Create a new Drink</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/logout">Logout</a>
                        </c:if>
                    </div>
                </li>
            </c:if>            
        </ul>
    </footer>
</body>
</html>