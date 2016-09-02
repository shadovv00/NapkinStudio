<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<%--<link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>--%>
<%--<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>--%>
<%--<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />--%>
	<script src="//code.jquery.com/jquery-3.1.0.min.js"></script>
	<script type="text/javascript" src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.min.js"></script>
</head>
<body>
<a class="btn btn-primary custom-width" href="/">Back</a>
<div class="row">
<div class = "col-md-offset-4 col-md-5">
<div class="row text-center">
	<h2>
		Registration
	</h2>
	</div>
</div>
<c:if test="${param.success eq true }">
	<div class="row text-center">
		<div class="col-md-offset-4 col-md-5">
			<div class="alert alert-success">
				Registration is successful
			</div>
		</div>
	</div>
</c:if>
<form:form commandName="user"
	cssClass="form-horizontal registrationForm">


	<div class="form-group">
		<label for="firstName" class="col-sm-4 control-label">First Name</label>
		<div class="col-sm-5">
			<form:input path="firstName" cssClass="form-control"></form:input>
		</div>
	</div>


	<div class="form-group">
		<label for="lastName" class="col-sm-4 control-label">Last Name</label>
		<div class="col-sm-5">
			<form:input path="lastName" cssClass="form-control"></form:input>
		</div>
	</div>

	<div class="form-group">
		<label for="login" class="col-sm-4 control-label">Login</label>
		<div class="col-sm-5">
			<form:input path="login" cssClass="form-control" />
			<form:errors path="login"/>
		</div>
	</div>

	<div class="form-group">
		<label for="email" class="col-sm-4 control-label">Email</label>
		<div class="col-sm-5">
			<form:input path="email" cssClass="form-control" />
			<form:errors path="email" />
		</div>
	</div>

	<div class="form-group">
		<label for="password" class="col-sm-4 control-label">Password</label>
		<div class="col-sm-5">
			<form:password path="password" cssClass="form-control" />
			<form:errors path="password" />
		</div>
	</div>

	<div class="form-group">
		<label for="password" class="col-sm-4 control-label">Repeat password</label>
		<div class="col-sm-5">
			<input type="password" name="password_again" id="password_again"
				class="form-control" />
		</div>
	</div>


	<div class="form-group modal-footer">
		<div class="col-md-9">
			<input type="submit"
				value="Register"
				class="btn btn-large btn-primary">
		</div>
	</div>
</form:form>
</div>
</body>
</html>
<script type="text/javascript">
	$(document).ready(function() {
		$(".registrationForm").validate({
			rules : {
				login : {
					required : true,
					minlength : 3,
					remote : {
						url : "<c:url value='/register/availableLogin'/>",
						type : "POST",
						data : {
							login : function() {
								return $("#login").val();
							}
						}
					}
				},

				email : {
					required : true,
					email : true,
					remote : {
						url : "<c:url value='/register/availableEmail'/>",
						type : "POST",
						data : {
							login : function() {
								return $("#email").val();
							}
						}
					}
				},
				password : {
					required : true,
					minlength : 4
				},

				password_again : {
					required : true,
					equalTo : "#password"
				}
			},
			messages : {
				login : {
					required: "This field is required!",
					minlength: "min length is 3",
					remote:"User with such login already exist"
				},

				email : {
					required: "This field is required!",
					email : "please put correct email",
					remote: "User with such email already exist"
				},
				password : {
					minlength :"min length is 4",
					required : "This field is required!",
				},
				password_again : {
					equalTo : "Passwords don't macth",
					required :"This field is required!",
				}

			},
//			highlight: function(element) {
//				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
//			},
//			unhighlight: function(element) {
//				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
//			}
		});
	});
</script>
