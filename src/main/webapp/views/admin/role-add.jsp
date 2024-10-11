<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/admin/role/insert" method="post">
  <label for="roleName">Role Name:</label><br>
  <input type="text" id="roleName" name="roleName">
  <br>
  <br>
  <input type="submit" value="Submit">
</form> 
</body>
</html>