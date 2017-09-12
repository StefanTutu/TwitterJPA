<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Twitter :: Login</title>
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
	<s:form method="POST" commandName="account" acction="login">
		<fieldset>
			<legend>Login</legend>
			${error} <br>
			<table cellpadding="2" cellspacing="2">
				<tr>
					<td>Username</td>
					<td><s:input path="username" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><s:input type="password" path="password" /></td>
				</tr>
				<tr>
					<td>Remember me?</td>
					<td><input type="checkbox" name="remember" value="true"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Login"></td>
				</tr>
			</table>
		</fieldset>
	</s:form>

</body>
</html>