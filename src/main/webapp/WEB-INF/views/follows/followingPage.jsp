<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
  <title>Twitter :: Following</title>
</head>
<body style="margin: 0px;">
  <jsp:include page="../_menu.jsp" />

  <div style="margin-left:30px; margin-top: 30px;">

    <h1 style="color: grey;">Following</h1>

    <c:forEach var="user" items="${listFollowing}" varStatus="status">
      <b><p>${user.user_followed}:</b>${user.follower}</p>
    </c:forEach>
  </div>
</body>
</html>