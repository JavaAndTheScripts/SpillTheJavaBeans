<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<!-- formatting decimal values -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- for forms -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
		<!-- CSS only -->
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
			crossorigin="anonymous"
		/>
		<!-- My CSS -->
		<link rel="stylesheet" href="/css/styles.css" />
		<!-- JS for Bootstrap / jQuery -->
		<script src="/webjars/jquery/jquery.min.js"></script>
		<script src="/webjars/bootstrap/js/boostrap.min.js"></script>
		<!-- JavaScript Bundle with Popper -->
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
			crossorigin="anonymous"
		></script>
		<!-- My JS -->
		<script type="text/javascript" src="/js/scripts.js"></script>
		<meta charset="UTF-8" />

		<!-- Title -->
		<title>${cafe.name}</title>
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
    <main class="drinkSize m-3">
        <div class="container">
            <table class="table table-hover">
                <thead>
                    <tr>
                    <th>Drink Menu</th>
                    <th></th>
                    <th><img src="/images/byteSize.png" alt="byteSize" style="max-height: 60px;"></th>
                    <th><img src="/images/intSize.png" alt="intSize" style="max-height: 60px;"></th>
                    <th><img src="/images/longSize.png" alt="longSize" style="max-height: 60px;"></th>
                    <th>Action</th>
                    </tr>
                </thead>
                <tbody class="">
                    <c:forEach items="${allDrinks}" var="d">
                        <tr>
                        <td class="">
                            <!-- Drink name -->
                            <div class="">
                                ${d.name}
                            </div>
                            <!-- Drink description -->
                            <div class="">
                                ${d.description}
                            </div>
                            <!-- Drink ingredients -->
                            <div class="">
                                ${d.ingredients}
                            </div>
                        </td>
                        <!-- Drink temperature -->
                        <td class="">
                            <!-- Hot -->
                            <c:if test="${ d.isHot }">
                                <img src="/images/hotIcon.png" alt="hotIcon" style="max-width: 40px;"/>
                            </c:if>
                            <!-- Cold -->
                            <c:if test="${ d.isCold }">
                                <img src="/images/coldIcon.png" alt="coldIcon" style="max-width: 40px;"/>
                            </c:if>
                        </td>
                        <!-- Drink price - byte (small) -->
                        <td><fmt:formatNumber value="${d.bytePrice}" type="currency"/></td>
                        <!-- Drink price - int (medium) -->
                        <td><fmt:formatNumber value="${d.intPrice}" type="currency" /></td>
                        <!-- Drink price - Long (Large) -->
                        <td><fmt:formatNumber value="${d.longPrice}" type="currency" /></td>
                         <c:if test="${ userTYPE.equals('Manager') }">
                            <td>
                                <a href="/drink/${d.id}/updateDrink" class="mx-1">  Edit</a> |
                                
                                <form:form id="deleteForm" action="/drink/${d.id}/deleteDrink" method="post" modelAttribute="deleteForm">
                                	<input type="hidden" name="_method" value="delete">
                                	<input type="submit" value="Delete" class="btn btn-link" onclick="return confirm('Are you sure you want to delete this drink?')" />
                                </form:form>
                                
                            </td>
                        </c:if>
                        </tr>
                       
                    </c:forEach>
                </tbody>
            </table>
        </div>
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
