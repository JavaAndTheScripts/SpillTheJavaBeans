<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- for forms -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- for validation -->
<%@ page isErrorPage="true"%>


<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<!-- My CSS -->
<link rel='stylesheet' href='/css/styles.css'>
<!-- JS for Bootstrap / jQuery -->
<script src='/webjars/jquery/jquery.min.js'>
	
</script>
<script src='/webjars/bootstrap/js/boostrap.min.js'>
	
</script>
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
	crossorigin="anonymous">
	
</script>
<!-- My JS -->
<script type="text/javascript" src='/js/scripts.js'></script>
<meta charset="UTF-8">

<!-- Title -->
<title>Create Coffee</title>
</head>
<body>
	<!-- HEADER -->
    <header class="navHead">
        <!-- add logo here ishhh - apv -->
        <img  src="/images/logo.png" class="logoPic" alt="logo">
        <h1><a class="title" href="/cafe" style="text-decoration: none;">${cafe.name}</a></h1>
        <div><br>
        <ul class="navTabs nav-pills">
            <li class="nav-item">
                <a class="nav-link" href="/coffee">Featured Coffee</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/cafe/menu">Menu</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/cafe/puzzle">Monthly Puzzle</a>
            </li>
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
                            <a class="dropdown-item" href="/cafe/coupons">Avaliable Coupons</a>
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
    </div>
    </header>
	<!-- MAIN -->
	<main >
	<div class="container">
		
		<div class="bg-light">
				<h1 class="text-center">Add a New Coffee</h1>
				<!-- register box -->
				<form:form action="/coffee/create" method="POST"
					modelAttribute="coffeeForm" class="mx-5 my-2">
		
					<!-- Attribute Information -->
					<div class="mb-3">
						<label for="region">Region</label> 
						<input type="text" name="region" class="form-control">
					</div>
		
					<!-- Validation Error -->
					<form:errors path="region" class="text-warning" />
					<div class="mb-3">
						<label for="roast">Roast</label>
						<select name="roast">
							<option value="0">Light Roast</option>
							<option value="1">Medium Roast</option>
							<option value="2">Dark Roast</option>
							<option value="-1" selected="true" disabled hidden>Select from the options listed</option>	
						</select>
					</div>
					<!-- Validation Error -->
					<form:errors path="roast" class="text-warning" />
					
					<!-- Attribute Information -->
					<div class="mb-3">
						<label for="flavors">Flavors</label> 
						<input type="text" name="flavors" class="form-control">
					</div>
					<!-- Validation Error -->
					<form:errors path="flavors" class="text-warning" />
		
					<button class="btn btn-dark my-1">Create</button>
					<a class="btn btn-secondary" href="/coffee">Cancel</a>
				</form:form>
		</div>
		
	
	</div>
	</main>
	<!-- FOOTER -->
    <footer class="m-3">
        <ul class="nav nav-pills justify-content-end">
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
                            <a class="dropdown-item" href="/puzzle/edit">Update Cafe Puzzle</a>
                            <a class="dropdown-item" href="/drink/create">Create a new Drink</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="/logout">Logout</a>
                        </c:if>
                        <!-- <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Separated link</a> -->
                    </div>
                </li>
            </c:if>            
        </ul>
    </footer>
</body>
</html>