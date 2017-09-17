<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Twitter :: Following</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
	$(document).ready(
			function() {
				$.ajax({
					type : "GET",
					url : 'http://localhost:8080/twitter/users/follow',
					dataType : "json"
				}).then(
						function(data) {
							if (data != null) {
								$("follow").text("");
								for (var i = 0; i < data.length; i++) {
									$("#follow").append(
											"<p>" + data[i].username + ":"
													+ data[i].tweet + "</p>");
								}
							} else {
								$('#follow').text("You need to login !");
							}
						});
			})
</script>

</head>
<body style="margin: 0px;">
	<jsp:include page="../_menu.jsp" />

	<div style="margin-left: 30px; margin-top: 30px;">

		<h1 style="color: grey;">Following</h1>

		<c:forEach var="user" items="${listFollowing}" varStatus="status">
			<b><p>${user.user_followed}:</b>${user.follower}</p>
		</c:forEach>
		<div id="follow"></div>
	</div>
</body>
</html>