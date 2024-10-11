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
	<form action="${pageContext.request.contextPath}/admin/video/update"
		method="post"  enctype="multipart/form-data">
		
		<input type="hidden" id="videoId" name="videoId" value="${vid.videoId}"><br>
		
		<label for="title">Title:</label><br> <input
			type="text" id="title" name="title"
			value="${vid.title }"><br> 
		
		<label for="poster">Poster:</label><br>	
		<c:if test="${vid.poster.substring(0,5) !='https'}">
			<c:url value="/image?fname=${vid.poster}" var="imgUrl"></c:url>
		</c:if>
		<c:if test="${vid.poster.substring(0,5) =='https'}">
			<c:url value="${vid.poster}" var="imgUrl"></c:url>
		</c:if>
		<img height="150" width="200" src="${imgUrl}" id="posters" /> 
		<input type="file" onchange="chooseFile(this)"
			id="poster" name="poster" value="${vid.poster}"><br>
		
		<label for="description">Description:</label><br> <input
			type="text" id="description" name="description"
			value="${vid.description }"><br>
			
		<label for="categoryId">Category Id:</label><br> <input
			type="text" id="categoryId" name="categoryId"
			value="${vid.category.categoryId }"><br> 
		
		 <label for="active">Active:</label><br> <input type="text" id="active"
			name="active" value="${vid.active }"> <br> <br> <input
			type="submit" value="Submit">
	</form>
</body>
</html>