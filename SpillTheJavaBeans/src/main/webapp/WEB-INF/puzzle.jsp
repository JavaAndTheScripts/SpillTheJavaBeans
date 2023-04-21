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
    <link rel='stylesheet' href='/css/puzzle.css'>
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
    <main class="puzzle">
        <c:if test="${ puzzle == null }">
            <div class="column">
                <h2>Monthly Puzzle</h2>
                <h4>No monthly puzzle has been added.</h4>
            </div>
            <p>Please come back when the manager has added the monthly puzzle.</p>
        </c:if>
        <c:if test="${ puzzle != null }">
            <div class="column larger">
                <h4>${puzzle.title}</h4>
                <p>${puzzle.contents}</p>
            </div>
            <!-- Form for guess submission -->
            <form action="/puzzle/guess" method="post" class="column"> 
                <c:if test="${ puzzle.percent == 100 }">
                    <p>Correct guesses will recieve one free ${puzzle.reward}!</p>
                </c:if>
                <c:if test="${ puzzle.percent != 100 }"> 
                    <p>Correct guesses get a reward of ${puzzle.percent}% off of ${puzzle.reward}!</p>
                </c:if>
                <!-- If they are not a user -->
                <c:if test="${ subscriber == null }">
                    <label for="guess">Enter your guess on what the Returned Value will be:</label>
                    <div class="textarea-like">
                        You must be a subscriber and logged in to try solving the puzzle.
                    </div>
                    
                    <div class="button">
                        <button class="button-52" role="button" disabled>Guess</button>
                    </div>
                </c:if>
                <!-- If they are logged in AND have attempted the puzzle -->
                <c:if test="${ subscriber != null && subscriber.solvedPuzzle }">
                    <c:if test="${ subscriber.puzzle == null}">
                        <label for="guess">Enter your guess on what the Returned Value will be:</label>
                        <div class="textarea-like">
                            You have already used your guess for this puzzle.
                        </div>
                        <div class="button">
                            <button class="button-52" role="button" disabled>Guess</button>
                        </div>
                    </c:if>
                    <c:if test="${ subscriber.puzzle != null}">
                        <h4 class="congrats">Congrats you figured it out!</h4>
                        <p>Check out your Available Coupons to use your reward.</p>
                    </c:if>
                </c:if>
                <!-- if they are logged in AND have not attempted the puzzle -->
                <c:if test="${ subscriber != null && !subscriber.solvedPuzzle }">
                    <label for="guess">Enter your guess on what the Returned Value will be:</label>
                    <input type="text" name="guess">
                    
                    <div class="button">
                        <button class="button-52" role="button">Guess</button>
                    </div>
                </c:if>                    
                
            </form>
        </c:if>
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
                            <a class="dropdown-item" href="/puzzle/edit">Update Cafe Puzzle</a>
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