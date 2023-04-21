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
    <link rel='stylesheet' href='/css/coupons.css'>
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
    <main class="m-3">
        <div class="coupons">
            <h2>Welcome to your coupons ${ subscriber.firstName }!</h2>
            <c:if test="${ subscriber.bdayCheck() }">
                <div class="bday">
                    <h2>Happy Birthday ${ subscriber.firstName}!!</h2>
                    <div class="firework"></div>
                    <div class="firework"></div>
                    <div class="firework"></div>
                    <form:form action="/cafe/coupons/useBday" method="post" modelAttribute="subForm">
                        <input type="hidden" name="_method" value="put">
                        <!-- Subscriber Hidden Attributes -->
                        <form:input path="id" value="${ subscriber.id }" type="hidden"/>
                        <form:input path="firstName" value="${ subscriber.firstName }" type="hidden"/>
                        <form:input path="lastName" value="${ subscriber.lastName }" type="hidden"/>
                        <form:input path="email" value="${ subscriber.email }" type="hidden"/>
                        <form:input path="password" value="${ subscriber.password }" type="hidden"/>
                        <form:input path="birthday" value="${ subscriber.birthday }" type="hidden"/>
                        <form:input path="solvedPuzzle" value="${ subscriber.solvedPuzzle }" type="hidden"/>
                        <form:input path="usedBday" value="${ true }" type="hidden"/>
                        <form:input path="puzzle" value="${ subscriber.puzzle.id }" type="hidden"/>
                        <!-- Button -->
                        <div class="button">
                            <button class="button-57" role="button">
                                <span class="text">Get a Free Drink</span>
                                <span>Use Coupon</span>
                            </button>
                        </div>
                    </form:form>
                    <p>From all of us at Spill the Java Beans.</p>
                    <p class="subscript">Coupon expires at the end of the month.</p>
                </div>
            </c:if>
            <!-- Puzzle coupon  -->
            <c:if test="${ subscriber.puzzle != null }">
                <div class="puzzle">
                    <h2>Congratulations</h2>
                    <p>on solving the <br> 
                        <span><c:out value="${subscriber.puzzle.title}"/></span> 
                        <br> puzzle this month!</p>
                    <form:form action="/cafe/coupons/usePuzzle" method="post" modelAttribute="subForm">
                            <input type="hidden" name="_method" value="put">
                            <!-- Subscriber Hidden Attributes -->
                            <form:input path="id" value="${ subscriber.id }" type="hidden"/>
                            <form:input path="firstName" value="${ subscriber.firstName }" type="hidden"/>
                            <form:input path="lastName" value="${ subscriber.lastName }" type="hidden"/>
                            <form:input path="email" value="${ subscriber.email }" type="hidden"/>
                            <form:input path="password" value="${ subscriber.password }" type="hidden"/>
                            <form:input path="birthday" value="${ subscriber.birthday }" type="hidden"/>
                            <form:input path="solvedPuzzle" value="${ subscriber.solvedPuzzle }" type="hidden"/>
                            <form:input path="usedBday" value="${ subscriber.usedBday }" type="hidden"/>
                            <form:input path="puzzle" value="${ null }" type="hidden"/>
                            <!-- Button -->
                            <div class="button">
                                <button class="button-57" role="button">
                                        <span class="text">Get
                                        <c:if test="${ subscriber.puzzle.percent == 100 }">
                                            one free <c:out value="${subscriber.puzzle.reward}"/>
                                        </c:if>
                                        <c:if test="${ subscriber.puzzle.percent != 100 }">
                                            <c:out value="${subscriber.puzzle.percent}"/> % off of
                                            <c:out value="${subscriber.puzzle.reward}"/>
                                        </c:if>
                                        on us!</span>
                                        <span>Use Coupon</span>
                                </button>
                            </div>
                        </form:form>
                        <p class="subscript">Coupon expires when a new puzzle is posted.</p>
                </div>
            </c:if>
            <c:if test="${ !subscriber.bdayCheck() && subscriber.puzzle == null }">
                <div class="none">
                    <h2>Unfortunately, it seems like you do not have any coupons available. <br> :(</h2>
                </div>
            </c:if>
        </div>
    </main>
    <!-- FOOTER -->
    <footer class="m-3">
        <ul class="nav nav-pills justify-content-end">
            <!-- Make sure subscriber is not signed in -->
            <c:if test="${ !userTYPE.equals('Subscriber') }">
                <li class="nav-item dropdown">
                    <a
                        class="nav-link dropdown-toggle"
                        data-bs-toggle="dropdown"
                        href="#"
                        role="button"
                        aria-haspopup="true"
                        aria-expanded="false"
                        >Manager</a
                    >
                    <div class="dropdown-menu">
                        <c:if test="${ userID == null }">
                            <a class="dropdown-item" href="/mana/login">Login</a>
                            <a class="dropdown-item" href="/mana/register">Register</a>
                        </c:if>
                        <c:if test="${ userID != null }">
                            <!-- Allow manager to add a new coffee -->
                            <a class="dropdown-item" href="/coffee/create">Add a New Coffee</a>
                            <!-- && userTYPE.equals('Manager') -->
                            <a class="dropdown-item" href="/cafe/coffee/edit"
                                >Change Coffee of the Month</a
                            >
                            <a class="dropdown-item" href="/drink/create"
                                >Create a new Drink</a
                            >
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