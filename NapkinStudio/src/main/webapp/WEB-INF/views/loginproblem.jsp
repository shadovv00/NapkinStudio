<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>NapkinStudio inloggen problemen</title>
    <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
</head>
<header>
    <div class="col-md-1 back-btn-wrapper" style="background-color: white">
        <a class="btn btn-primary custom-width" href="/NapkinStudio/">Back</a>
    </div>
    <div class="col-md-11 border" style="background-color: white">
        <h2>Problemen met inloggen?</h2>
    </div>
</header>
<%--<body onload='document.getElementById("username").focus();'>--%>
<body>

<div id="mainWrapper">
    <div class="container">
        <div class="col-md-5 logo-image"></div>
        <div class="login-container col-md-7">
            <div class="login-card">
                <div class="login-form">
                    <p>Neem contact op met uw Debtor of PVI manager om een wachtwoord opnieuw in te stellen.</p>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
