<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Twitter :: New Tweet</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
<script>
    function functionTweet(){
    	var content = document.getElementById("tweet").value;
    	var user = "<%=session.getAttribute("username")%>";
		var Data = {
			"id" : 1,
			"tweet" : content,
			"user_username" : user
		};

		$.ajax({
			type : "PUT",
			url : "http://localhost:8080/twitter/mesaj/message",
			data : JSON.stringify(Data),
			processData : true,
			cache : false,
			async : true,
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			success : function(data) {
				console.log(data);
				location.reload();
			},
			failure : function(errMsg) {
				alert(errMsg);
			}
		});
	};
</script>

</head>
<body style="margin: 0px;">
	<jsp:include page="../_menu.jsp" />
	<div style="margin-left: 30px; margin-top: 30px;">
		<h1 style="color: grey;">Compose New Tweet</h1>
		<br />
		<table>
			<tr>
				<td><b>New Tweet:</b></td>
				<td><textarea id="tweet" name="tweet" maxlength="140" rows="3"
						cols="50" /></textarea></td>
			</tr>
			<tr>
				<td></td>
				<td><span style="font-size: 10pt; color: grey;">*
						Maximum 140 characters</span></td>
			</tr>
			<tr>
				<td><br /> <input type="button" value="Tweet"
					style="margin-left: 10px;" onClick="functionTweet()"></td>
			</tr>
		</table>
	</div>

	<div class="container">
		<c:forEach items="${tweet}" var="map">
			<c:forEach items="${map.value}" var="message">
				<div class="list_items">
					<p>
						<b>${message.user_username}:</b> ${message.tweet}
				</div>
			</c:forEach>
		</c:forEach>
	</div>

	<div id="newMessage"></div>
</body>
</html>
