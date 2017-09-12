<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>

<title>Create an account</title>

<script type="javascript"> console.log="registration"</script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">	
</script>

</head>

<body style="margin: 0px;">
	<jsp:include page="_menu.jsp" />

	<div style="margin-left: 30px; margin-top: 30px;">
		<h1>Register on Twitter</h1>
	</div>
	<div class="container">

		<fieldset>
			<legend>Register</legend>
			${error} <br>
			<table cellpadding="2" cellspacing="2">
				<tr>
					<td>Username</td>
					<td><input id="username" type="text" autofocus="true" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input id="password" type="password" /></td>
				</tr>
				<tr>
					<td>Password Confirm</td>
					<td><input id="passwordConfirm" type="password" placeholder="Confirm you password"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input id="Register" type="submit" value="Submit"></td>
				</tr>
			</table>
		</fieldset>
	</div>
<script>
$('#Submit').click(function(){
	var username = $("#username").val();
	var password = $("#password").val();
	var passwordConfirm = $("#passwordConfirm").val();
	if(password==passwordConfirm){
	var user ={"username":username, "password":password};

	$.ajax({
		url:"http://localhost:8080/twitter/account/register",
		type:"POST",
		dataType:"JSON",
		contentType:"application/json",
		async:true,
		data:JSON.stringify(user),
		success:function(data){
			if(data.code==200){
				window.location.replace("${contextPath}/account/register");
			}
			else {
				$("#error").text(data.message);
			}
		},},
		failure:function(data){
		}
	});
})
</script>
</body>
</html>
