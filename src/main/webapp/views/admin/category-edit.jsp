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
	<form action="${pageContext.request.contextPath}/admin/category/update"
		method="post"  enctype="multipart/form-data">
		<input type="hidden" id="categoryId" name="categoryId" value="${cate.categoryId}"><br>
		<label for="categoryName">Category Name:</label><br> <input
			type="text" id="categoryName" name="categoryName"
			value="${cate.categoryName }"><br> <label for="images">Images:</label><br>
		<c:if test="${cate.images.substring(0,5) !='https'}">
			<c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
		</c:if>
		<c:if test="${cate.images.substring(0,5) =='https'}">
			<c:url value="${cate.images}" var="imgUrl"></c:url>
		</c:if>
		<img height="150" width="200" src="${imgUrl}" id="imagess" /> 
		<input type="file" onchange="chooseFile(this)"
			id="images" name="images" value="${cate.images}"><br> <label
			for="status">Status:</label><br> <input type="text" id="status"
			name="status" value="${cate.status }"> <br> <br> <input
			type="submit" value="Submit">
	</form>
</body>
</html>