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
        <h2>Create a Drink</h2>
        <form:form action="/drink/create" method="post" modelAttribute="modelForm" class="d-flex flex-column">
            <!-- String name -->                
            <div class="my-2">
                <label for="name">Name: </label>
                <input type="text" name="name">
                <!-- Validation Error -->
                <form:errors path="name" class="text-warning"/>
            </div>
            <!-- String description --> 
            <div class="my-2">
                <label for="description">Description:</label>
                <input type="text" name="description">
                <!-- Validation Error -->
                <form:errors path="description" class="text-warning"/>
            </div>
            <!-- String ingredients --> 
            <div class="my-2">
                <label for="ingredients">Ingredients:</label>
                <input type="text" name="ingredients">
                <!-- Validation Error -->
                <form:errors path="ingredients" class="text-warning"/>
            </div>
            <!-- Boolean isHot --> 
            <div class="d-flex flex-row justify-content-between align-items-center my-2">
                <label for="cafe">Temperature of the drink: </label>
                <fieldset class="my-2">
                    <label for="isHot">Hot</label>
                    <input type="radio" name="isHot" value="${true}">
                    <label for="isHot">Cold</label>
                    <input type="radio" name="isHot" value="${false}">
                </fieldset>
                <!-- Validation Error -->
                <form:errors path="isHot" class="text-warning"/>
            </div>
            <!-- Double price --> 
            <div class="my-2">
                <label for="price">Price:</label>
                <input type="number" step=".01" name="price">
                <!-- Validation Error -->
                <form:errors path="price" class="text-warning"/>
            </div>
            <!-- Coffee coffee --> 
            <div class="my-2">
                <label for="coffee">Coffee:</label>
                <select name="coffee" id="">
                    <option selected hidden disabled>--- Select Coffee ---</option>
                    <c:forEach items="${allCoffee}" var="c">
                        <option value="${c.id}">${c.region}; ${c.flavors}; ${c.roastType()}</option>
                    </c:forEach>
                </select>
                <!-- Validation Error -->
                <form:errors path="coffee" class="text-warning"/>
            </div>
            <!-- Cafe cafe --> 
            <div class="d-flex flex-row justify-content-between align-items-center my-2">
                <label for="cafe">Do you want to add to the avaliable menu?</label>
                <fieldset class="my-2">
                    <label for="cafe">Yes</label>
                    <input type="radio" name="cafe" value="${cafeID}">
                    <label for="cafe">No</label>
                    <input type="radio" name="cafe" value="${null}" checked>
                </fieldset>
            </div>
            <!-- Submission button -->
            <button class="btn btn-primary">Add Drink</button>
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