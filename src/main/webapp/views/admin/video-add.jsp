<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/admin/video/insert" method="post" enctype="multipart/form-data">
  <label for="title">Title:</label><br>
  <input type="text" id="title" name="title"><br>
  
  <label for="poster">Poster:</label><br>
  <img id="posters" height="150" width="200" src="" />
  <input type="file" onchange="chooseFile(this)" id="poster" name="poster"><br>
  
  <label for="description">Description:</label><br>
  <input type="text" id="description" name="description"><br>
  
  <label for="categoryId">Category Id:</label><br>
  <input type="text" id="categoryId" name="categoryId"><br>
  
  <label for="active">Active:</label><br>
  <input type="text" id="active" name="active">
  <br>
  <br>
  <input type="submit" value="Submit">
</form> 
</body>
</html>