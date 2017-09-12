<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Twitter :: Tweets</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>

<script type="text/javascript">
					function functionSearch() {
						var username = $('#username').val();
						var search = $('#search').val();
						
						console.log(username);

						if (username == "") {
							window.location = 'http://localhost:8080/twitter/tweets/formatted?search='
									+ search;
						} else {
							window.location = 'http://localhost:8080/twitter/tweets/'
									+ username + "/formatted?search=" + search;
						}
						
						var newMessage = document.getElementById('NewMessage');
						newMessage.style.display = 'none';
					};
</script>
</head>
<body style="margin: 0px;">
	<jsp:include page="../_menu.jsp" />

	<div style="margin-left: 30px; margin-top: 30px;">

		<c:choose>
			<c:when test="${not empty username}">
				<h1 style="color: grey;">${username} Tweets</h1>
			</c:when>

			<c:otherwise>
				<h1 style="color: grey;">All Tweets</h1>
			</c:otherwise>
		</c:choose>

		<h3>Search tweets by user and keyword:</h3>

		
			<b>User:</b> <input type="text" id="username" value="${username}"
				placeholder="Insert username" /> <b style="margin-left: 25px;">Keyword:</b>
			<input type="text" id="search" value="${search}"
				placeholder="Insert keyword" /> <input type="button" value="Search" id="searchButton" onClick="functionSearch()"
				style="margin-left: 25px;" />
		


		<c:forEach var="tweetsList" items="${tweets}">
			<div class="list_items">
				<p>
					<b>${tweetsList.user_username}:</b> ${tweetsList.tweet}
			</div>
		</c:forEach>

		<div class="content-holder">
			<div class="padding_container" id="NewMessage"><h4>New Message:</h4></div>
			<c:forEach items="${tweet}" var="map">
				<c:forEach items="${map.value}" var="message">
					<div class="list_items">
						<p>
							<b>${message.user_username}:</b> ${message.tweet}
					</div>
				</c:forEach>
			</c:forEach>
		</div>
		<div><h4>Search</h4>
		
		<c:forEach items="${listTweets}" var="map">
				<p>
				<b>${map.tweet}:</b> ${map.user_username}
				</c:forEach>
				</div>
	</div>
</body>
</html>