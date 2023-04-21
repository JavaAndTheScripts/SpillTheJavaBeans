<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"> 
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <!-- My CSS -->
    <link rel='stylesheet' href='/css/styles.css'>
    <link rel="stylesheet" href="/css/flickity.css" media="screen">
    <!-- JS for Bootstrap / jQuery -->
    <script src='/webjars/jquery/jquery.min.js'>    </script>
    <script src='/webjars/bootstrap/js/boostrap.min.js'>    </script>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous">    </script>
    <!-- My JS -->
    <script type = "text/javascript" src='/js/scripts.js'></script>
    <meta charset="UTF-8">

    <!-- background image ?-->
    <!-- <style class="cafepg">
        body {
            background-image: url('/images/javabeans2.png');
            background-repeat: repeat-y;
            background-attachment: scroll;
            background-size: cover;
        }
    </style> -->

    <!-- Title -->
    <title>Cafe</title>
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
    <main class="mainCafe "> <!--d-flex flex-row m-3-->
        
        <!-- Coffee of the month -->
        <style>
            @keyframes slide-in {
              from {
                transform: translateX(-100%);
              }
              to {
                transform: translateX(0%);
              }
            }
          
            .box {
              animation: slide-in 1000ms;
              animation-timing-function: linear;
            }
          </style>
          
          <div class="box container border">
            <!-- Hello World
          </div>
        <div class="container border text-center m-1"> -->
            <h3>Coffee of the Month</h3>
        </div>
            <!-- com not selected yet -->
        <div class="box container border coffeeMonth">
            <c:if test="${ cafe.monthlyCoffee == null }">
                <br>
                <h5>A Coffee has not been selected as the monthly special yet.</h5>
            </c:if>
            <!-- com has been selected -->
            <c:if test="${ cafe.monthlyCoffee != null }">
                <h5>${cafe.monthlyCoffee.region}</h5>
                <h5>${cafe.monthlyCoffee.flavors}</h5>
                <h5>${cafe.monthlyCoffee.roastType()}</h5>
            </c:if>
        </div>
        <div class="container m-2">

            <div class='wrapper'>
                <div class='carousel'>
                  <div class='carousel__item'>
                    <div class='carousel__item-head'>
                      <img src="/images/coldIcon.png" alt="cold" class="carousel_img">
                    </div>
                    <div class='carousel__item-body'>
                      <p class='caroTitle'>New Item: Java Jolt</p>
                      <p>A strong espresso shot with a hint of chocolate and hazelnut, topped with whipped cream and a drizzle of caramel sauce.</p>
                    </div>
                  </div>
                  <div class='carousel__item'>
                    <div class='carousel__item-head'>
                      <img src="/images/coldIcon.png" alt="cold" class="carousel_img">
                    </div>
                    <div class='carousel__item-body'>
                      <p class='caroTitle'>Try Our Monthly Puzzle</p>
                      <p>Link:</p>
                    </div>
                  </div>
                  <div class='carousel__item'>
                    <div class='carousel__item-head'>
                      <img src="/images/coldIcon.png" alt="cold" class="carousel_img">
                    </div>
                    <div class='carousel__item-body'>
                      <p class='caroTitle'>Coffee of the Month: C# Cappuccino</p>
                      <p>A classic cappuccino with a creamy foam topping and a sprinkle of cocoa powder.</p>
                    </div>
                  </div>
                  <div class='carousel__item'>
                    <div class='carousel__item-head'>
                      <img src="/images/coldIcon.png" alt="cold" class="carousel_img">
                    </div>
                    <div class='carousel__item-body'>
                      <p class='caroTitle'>Join our Monthly Membership for FREE goodies</p>
                      <p>Sign Up Here! </p>
                    </div>
                  </div>
                  <div class='carousel__item'>
                    <div class='carousel__item-head'>
                      <img src="/images/coldIcon.png" alt="cold" class="carousel_img">
                    </div>
                    <div class='carousel__item-body'>
                      <p class='caroTitle'>Try Our NEW Summer Refresher: Python Punch</p>
                      <p>A refreshing blend of tropical fruits and juices with a splash of ginger ale.</p>
                    </div>
                  </div>
                  <div class='carousel__item'>
                    <div class='carousel__item-head'>
                      🐋
                    </div>
                    <div class='carousel__item-body'>
                      <p class='caroTitle'>Get it While it Lasts: CSS Cooler</p>
                      <p>A tangy mix of lemonade and raspberry syrup, garnished with fresh mint leaves.</p>
                    </div>
                  </div>
                  <div class='carousel__item'>
                    <div class='carousel__item-head'>
                      🐬
                    </div>
                    <div class='carousel__item-body'>
                      <p class='caroTitle'>Shop our Merch </p>
                      <p>Cool Stuff</p>
                    </div>
                  </div>
                </div>
              </div>

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