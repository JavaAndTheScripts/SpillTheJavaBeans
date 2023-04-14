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
    <title>Project Title</title>
</head>
<body>
    <!-- HEADER -->
    <header>
        <nav>

        </nav>
    </header>
    <!-- MAIN -->
    <main>
        <!-- register box -->
        <form:form action="/mana/register" method="POST" modelAttribute="newUser"  class="mx-5 my-2">
            <div class="border text-center py-3">
                <h3>Register</h3>
            </div>
            <!-- firstName and lastName -->
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

            <!-- phoneNum -->
            <div class="border py-2">
                <section class="">
                    <form:label path="phoneNum" class="border-end w-50 ps-2 me-1">Phone Number:</form:label>
                    <input type="text" name="phoneNum" pattern="[0-9]{10}" title="10 digits required.">
                </section>
                <form:errors path="phoneNum" class="text-danger ps-2" />
            </div>

            <!-- email -->
            <div class="border py-2">
                <section>
                    <form:label path="email" class="border-end w-50 ps-2 me-1">Email:</form:label>
                    <form:input type="email" class="input" path="email" />
                </section>
                <form:errors path="email" class="text-danger ps-2" />
            </div>

            <!-- address -->
            <div class="border py-2">
                <section>
                    <form:label path="address" class="border-end w-50 ps-2 me-1">Street Address:</form:label>
                    <form:input type="text" class="input" path="address" />
                </section>
                <form:errors path="address" class="text-danger ps-2" />
            </div>

            <!-- city -->
            <div class="border py-2">
                <section>
                    <form:label path="city" class="border-end w-50 ps-2 me-1">City:</form:label>
                    <form:input type="text" class="input" path="city" />
                </section>
                <form:errors path="city" class="text-danger ps-2" />
            </div>

            <!-- state -->
            <div class="border py-2">
                <section>
                    <form:label path="state" class="border-end w-50 ps-2 me-1">State:</form:label>
                    <form:input type="text" class="input" path="state" />
                </section>
                <form:errors path="state" class="text-danger ps-2" />
            </div>

            <!-- zipcode -->
            <div class="border py-2">
                <section>
                    <form:label path="zipcode" class="border-end w-50 ps-2 me-1">Zip Code:</form:label>
                    <form:input type="text" class="input" path="zipcode" />
                </section>
                <form:errors path="zipcode" class="text-danger ps-2" />
            </div>

            <!-- password -->
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
                    <form:input type="password" class="input" path="confirm" />
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