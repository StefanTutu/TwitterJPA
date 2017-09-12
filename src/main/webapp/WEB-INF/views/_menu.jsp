<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="background-color: lightgrey; height: 9%;">
	<h2 style="display: inline-block; margin-left: 30px;">
		<c:choose>
			<c:when test="${sessionScope.username != null}">
				<a href="${pageContext.request.contextPath}/index"
					style="text-decoration: none; color: black;"> Twitter</a>
			</c:when>

			<c:otherwise>
				<a href="${pageContext.request.contextPath}/"
					style="text-decoration: none; color: black;">Twitter</a>
			</c:otherwise>
		</c:choose>
	</h2>

	<div style="float: right; line-height: 65px; margin-right: 30px;">
		<c:choose>
			<c:when test="${sessionScope.username != null}">
				<a href="${pageContext.request.contextPath}/account/logout"
					style="text-decoration: none; color: black;">Logout</a>
			</c:when>

			<c:otherwise>
				<a href="${pageContext.request.contextPath}/account/login"
					style="text-decoration: none; color: black;">Login</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>