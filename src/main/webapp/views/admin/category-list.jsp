<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath}/admin/category/search" method="get">
    <input type="text" name="keyword" value="${keyword}" placeholder="Nhập từ khóa tìm kiếm">
    <button type="submit">Tìm kiếm</button>
</form>

<a href="${pageContext.request.contextPath}/admin/category/add">Add category</a>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>images</th>
		<th>categoryId</th>
		<th>categoryName</th>
		<th>status</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${listcate}" var="cate" varStatus="STT">
		<tr>
			<td>${STT.index+1 }</td>
			<td>
				<c:if test="${cate.images.substring(0,5) !='https'}">
					<c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
				</c:if> 
				<c:if test="${cate.images.substring(0,5) =='https'}">
					<c:url value="${cate.images}" var="imgUrl"></c:url>
				</c:if> 
				<img height="150" width="200" src="${imgUrl}" /></td>
			<td>${cate.categoryId }</td>
			<td>${cate.categoryName }</td>
			<td><c:if test="${cate.status == 1}">
			<span>Hoạt động</span> 
			</c:if> 
			<c:if test="${cate.status != 1}">
				<span>Khóa</span>
			</c:if> 
			</td>
			<td><a
				href="<c:url value='/admin/category/edit?id=${cate.categoryId }'/>">Sửa</a>
				| <a
				href="<c:url value='/admin/category/delete?id=${cate.categoryId }'/>">Xóa</a></td>
		</tr>
	</c:forEach>
</table>