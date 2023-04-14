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
<title>Project Title</title>
</head>
<body>
	<!-- HEADER -->
	<header>
		<nav></nav>
	</header>
	<!-- MAIN -->
	<main>
		<h1>Create Coffee</h1>
		<!-- register box -->
		<form:form action="/coffee/create" method="POST"
			modelAttribute="coffeeForm" class="mx-5 my-2">

			<!-- Attribute Information -->
			<div class="d-flex">
				<label for="region">Region</label> 
				<input type="text" name="region">
			</div>

			<!-- Validation Error -->
			<form:errors path="region" class="text-warning" />

			<label for="roast">Roast</label>
			<select name="roast">
				<option value="0">Light Roast</option>
				<option value="1">Medium Roast</option>
				<option value="2">Dark Roast</option>
				<option value="-1" selected="true" disabled hidden>Select from the options listed</option>	
			</select>
			<!-- Validation Error -->
			<form:errors path="roast" class="text-warning" />
			
			<!-- Attribute Information -->
			<div class="d-flex">
				<label for="flavors">Flavors</label> 
				<input type="text" name="flavors">
			</div>
			<!-- Validation Error -->
			<form:errors path="flavors" class="text-warning" />


			<button class="btn btn-secondary my-1 w-100">Create</button>
		</form:form>
	</main>
	<!-- FOOTER -->
	<footer> </footer>
</body>
</html>