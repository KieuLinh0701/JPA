<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/admin/user/update"
		method="post" enctype="multipart/form-data">

		<input type="hidden" id="userId" name="userId" value="${user.userId}"><br>

		<label for="username">User Name:</label><br> <input type="text"
			id="username" name="username" value="${user.username }"><br>

		<label for="fullname">Full Name:</label><br> <input type="text"
			id="fullname" name="fullname" value="${user.fullname }"><br>

		<label for="email">Email:</label><br> <input type="text"
			id="email" name="email" value="${user.email }"><br> <label
			for="password">Password:</label><br> <input type="password"
			id="password" name="password" value="${user.password }"><br>

		<label for="images">Image:</label><br>
		<c:if test="${user.images.substring(0,5) !='https'}">
			<c:url value="/image?fname=${user.images}" var="imgUrl"></c:url>
		</c:if>
		<c:if test="${user.images.substring(0,5) =='https'}">
			<c:url value="${user.images}" var="imgUrl"></c:url>
		</c:if>
		<img height="150" width="200" src="${imgUrl}" id="images" /> <input
			type="file" onchange="chooseFile(this)" id="images" name="images"
			value="${user.images}"><br> <label for="phone">Phone:</label><br>
		<input type="text" id="phone" name="phone" value="${user.phone }"><br>

		<label for="roleId">Role Id:</label><br> <input type="text"
			id="roleId" name="roleId" value="${user.role.roleId }"><br>
		<br> <input type="submit" value="Submit">
	</form>
</body>
</html>