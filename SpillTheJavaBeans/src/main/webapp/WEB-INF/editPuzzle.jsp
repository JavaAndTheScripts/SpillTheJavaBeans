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
        <div class="container" style="background-color: rgba(131, 85, 44, 0.753);">
            <h2 class="text-center">Monthly Puzzle</h2>
            <!-- FORM -->
            <form:form action="/puzzle/edit" method="post" modelAttribute="modelForm" class="mx-4 my-2">
                <input type="hidden" name="_method" value="put">
                <!-- Cafe cafe -->
                <form:input path="cafe" value="${cafe.id}" type="hidden"/>
                <!-- Long id -->
                <form:input path="id" value="${puzzle.id}" type="hidden"/>
                <!-- String title -->
                <!-- Attribute Information -->
                <div class="mb-3">
                    <label for="title">Title:</label>
                    <form:input type="text" path="title" value="${puzzle.title}" class="form-control"/>
                </div>
                <!-- Validation Error -->
                <form:errors path="title" class="text-warning"/>
                <!-- String contents -->
                <!-- Attribute Information -->
                <div class="mb-3">
                    <label for="contents">Contents:</label>
                    <form:input type="text" path="contents" value="${puzzle.contents}" class="form-control"/>
                </div>
                <!-- Validation Error -->
                <form:errors path="contents" class="text-warning"/>
                <!-- String solution -->
                <!-- Attribute Information -->
                <div class="mb-3">
                    <label for="solution">Solution:</label>
                    <form:input type="text" path="solution" value="${puzzle.solution}" class="form-control"/>
                </div>
                <!-- Validation Error -->
                <form:errors path="solution" class="text-warning"/>
                <!-- String reward -->
                <!-- Attribute Information -->
                <div class="mb-3">
                    <label for="reward">Reward:</label>
                    <form:input type="text" path="reward" value="${puzzle.reward}" class="form-control"/>
                </div>
                <!-- Validation Error -->
                <form:errors path="reward" class="text-warning"/>
                <!-- Float percent -->
                <!-- Attribute Information -->
                <div class="mb-3">
                    <label for="percent">Discount Percentage:</label>
                    <form:input type="number" step="1" path="percent" value="${puzzle.percent}" class="form-control"/>
                </div>
                <!-- Validation Error -->
                <form:errors path="percent" class="text-warning"/>
                <div class="pb-3">
                    <button class="btn btn-dark">Submit</button>
            </div>
            </form:form>
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