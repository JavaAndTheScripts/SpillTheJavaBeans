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
    <title>Subscribe</title>
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
    <main>
        <!-- register box -->
        <form:form action="/subs/register" method="POST" modelAttribute="newUser"  class="mx-5 my-2">
            <div class="border text-center py-3">
                <h3>Register</h3>
            </div>
            <div class="d-flex flex-row border py-2">
                <div class="d-flex flex-column w-50">
                    <section class="d-flex flex-row">
                        <form:label path="firstName" class="border-end w-50 ps-2 me-1">First Name:</form:label>
                        <form:input type="text" class="input" path="firstName" />
                    </section>
                    <form:errors path="firstName" class="text-danger ps-2" />
                </div>
                <div class="d-flex flex-column">
                    <section class="d-flex flex-row">
                        <form:label path="lastName" class="border-end w-50 ps-2 me-1">Last Name:</form:label>
                        <form:input type="text" class="input" path="lastName" />
                    </section>
                    <form:errors path="lastName" class="text-danger ps-2" />
                </div>
            </div>

            <div class="border py-2">
                <section class="">
                    <form:label path="birthday" class="border-end w-50 ps-2 me-1">Birthday:</form:label>
                    <input type="date" name="birthday">
                </section>
                <form:errors path="birthday" class="text-danger ps-2" />
            </div>

            <div class="border py-2">
                <section>
                    <form:label path="email" class="border-end w-50 ps-2 me-1">Email:</form:label>
                    <form:input type="email" class="input" path="email" />
                </section>
                <form:errors path="email" class="text-danger ps-2" />
            </div>

            <div class="border py-2">
                <section>
                    <form:label path="password" class="border-end w-50 ps-2 me-1">Password:</form:label>
                    <form:input type="password" class="input" path="password" />
                </section>
                <form:errors path="password" class="text-danger text-right" />
            </div>

            <div class="border py-2">
                <section>
                    <form:label path="confirm" class="border-end w-50 ps-2 me-1">Confirm Password:</form:label>
                    <form:input type="password" class="input" path="confirm" required="true"/>
                </section>
                <form:errors path="confirm" class="text-danger ps-2" />
            </div>

            <button class="btn btn-secondary my-1 w-100">Register</button>
        </form:form>
    </main>
    <!-- FOOTER -->
    <footer>

    </footer>
</body>
</html>