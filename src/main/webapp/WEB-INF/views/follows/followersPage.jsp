<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Twitter :: Followers</title>
</head>
<body style="margin: 0px;">
	<jsp:include page="../_menu.jsp" />

	<div style="margin-left: 30px; margin-top: 30px;">

		<h1 style="color: grey;">Followers</h1>

		<c:forEach var="user" items="${listFollowers}" varStatus="status">
			<p>${user.user_followed}</p>
		</c:forEach>
	</div>
</body>
</html>