<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Twitter :: Follow and/or Unfollow</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
	$(document)
			.ready(
					function() {

						$
								.ajax(
										{
											type : "GET",
											url : 'http://localhost:8080/twitter/users/all',
											dataType : "json"

										})
								.then(
										function(data) {
											if (data != null) {
												$("#messages").text("");
												for (var i = 0; i < data.length; i++) {
													console.log(data[i].username + data[i].id + data[i].status);
													var div = document
															.createElement('div');
													div.innerHTML = '<p><strong>User</strong>  '
															+ data[i].username
															+ '</p>';
													var HTML = '';
													if (data[i].status == false) {
														HTML = '<input type="button" value="Follow" style="margin-left: 10px;" onClick="follow(';
														HTML += data[i].id
																+ ')">'
													} else {
														HTML = '<input type="button" value="Unfollow" style="margin-left: 10px;" onClick="unfollow(';
														HTML += data[i].id
																+ ')">'
													}
													div.innerHTML += HTML;
													$('#messages').append(div);
												}
											} else {
												$('#messages').text(
														"You need to login !")
											}
										});
					});
</script>

</head>
<body style="margin: 0px;">
	<jsp:include page="../_menu.jsp" />

	<div style="margin-left: 30px; margin-top: 30px;">
		<h1 style="color: grey;">Follow and/or Unfollow Users</h1>

		<c:if test="${not empty notice}">
      ${notice}
    </c:if>

		<br />



		<div id="messages"></div>
	</div>

	<script>
		function follow(id) {
			
			$.ajax({
				url:"http://localhost:8080/twitter/users/follow/add",
				type:"POST",
				dataType:"JSON",
				contentType:"application/json",
				async:true,
				data:JSON.stringify(id),
				success:function(data){
					if(data.code==200){
						window.location.replace("http://localhost:8080/twitter/index");
						location.reload();
					}
					else {
						$("#error").text(data.message);
						location.reload();
					}
				},
				failure:function(data){
					location.reload();
				}
			});
			
		};

		function unfollow(id) {
			
			$.ajax({
				url:"http://localhost:8080/twitter/users/follow/remove",
				type:"POST",
				dataType:"JSON",
				contentType:"application/json",
				async:true,
				data:JSON.stringify(id),
				success:function(data){
					if(data.code==200){
						window.location.replace("http://localhost:8080/twitter/index");
						location.reload();
					}
					else {
						$("#error").text(data.message);
						location.reload();
					}
				},
				failure:function(data){
					location.reload();
				}
			});
			
		};
	</script>
</body>
</html>