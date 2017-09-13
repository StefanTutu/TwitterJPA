<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Twitter :: Login</title>
 
<script
src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
</script>
</head>
<body style="margin: 0px;">
	<jsp:include page="_menu.jsp" />

	<div style="margin-left: 30px; margin-top: 30px;">

		<h1>Login</h1>

		<c:if test="${param.error == 'true'}">
			<div style="color: grey; margin: 10px 0px;">
				Login Failed!!!<br /> Reason : ${error}
			</div>
		</c:if>
	</div>
	<fieldset>
		<legend>Login</legend>
		${error} <br>
		<table cellpadding="2" cellspacing="2">
			<tr>
				<td>Username</td>
				<td><input id="username" type="text" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input id="password" type="password" /></td>
			</tr>
			<tr>
				<td>Remember me?</td>
				<td><input type="checkbox" name="remember" value="true"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input id="Login" type="submit" value="Login"></td>
			</tr>
			<tr>
			 <h4 class="text-center"><a href="http://localhost:8080/twitter/account/register">Create an account</a></h4>
			</tr>
		</table>
	</fieldset>
	
	<script>
$('#Login').click(function(){
	var username = $("#username").val();
	var password = $("#password").val();
	var user ={"username":username, "password":password};

	$.ajax({
		url:"http://localhost:8080/twitter/account/login",
		type:"POST",
		dataType:"JSON",
		contentType:"application/json",
		async:true,
		data:JSON.stringify(user),
		success:function(data){
			if(data.code==200){
				window.location.replace("http://localhost:8080/twitter/index");
			}
			else {
				$("#error").text(data.message);
			}
		},
		failure:function(data){
		}
	});
})
</script>

</body>
</html>