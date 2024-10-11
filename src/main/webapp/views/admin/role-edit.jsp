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
	<form action="${pageContext.request.contextPath}/admin/role/update"
		method="post">
		<input type="hidden" id="roleId" name="roleId" value="${role.roleId}"><br>
		<label for="roleName">Role Name:</label><br> <input
			type="text" id="roleName" name="roleName"
			value="${role.roleName }"><br> <br> 
			<input type="submit" value="Submit">
	</form>
</body>
</html>