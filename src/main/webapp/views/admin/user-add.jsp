<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/admin/user/insert" method="post" enctype="multipart/form-data">
  
  <label for="username">User Name:</label><br>
  <input type="text" id="username" name="username"><br>
  
  <label for="fullname">Full Name:</label><br>
  <input type="text" id="fullname" name="fullname"><br>
  
  <label for="email">Email:</label><br>
  <input type="text" id="email" name="email"><br>
  
  <label for="password">Password:</label><br>
  <input type="password" id="password" name="password"><br>
  
  <label for="images">Image:</label><br>
  <img id="images" height="150" width="200" src="" />
  <input type="file" onchange="chooseFile(this)" id="images" name="images"><br>
  
  <label for="phone">Phone:</label><br>
  <input type="text" id="phone" name="phone"><br>
  
  <label for="roleId">Role Id:</label><br>
  <input type="text" id="roleId" name="roleId">
  <br>
  <br>
  <input type="submit" value="Submit">
</form> 
</body>
</html>