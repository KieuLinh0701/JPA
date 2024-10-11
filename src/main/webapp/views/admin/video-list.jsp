<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath}/admin/video/search" method="get">
    <input type="text" name="keyword" value="${keyword}" placeholder="Nhập từ khóa tìm kiếm">
    <button type="submit">Tìm kiếm</button>
</form>

<a href="${pageContext.request.contextPath}/admin/video/add">Add video</a>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Poster</th>
		<th>VideoId</th>
		<th>Title</th>
		<th>Description</th>
		<th>Category Id</th>
		<th>Views</th>
		<th>Status</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${listvideo}" var="vid" varStatus="STT">
		<tr>
			<td>${STT.index+1 }</td>
			<td>
				<c:if test="${vid.poster.substring(0,5) !='https'}">
					<c:url value="/image?fname=${vid.poster}" var="imgUrl"></c:url>
				</c:if> 
				<c:if test="${vid.poster.substring(0,5) =='https'}">
					<c:url value="${vid.poster}" var="imgUrl"></c:url>
				</c:if> 
				<img height="150" width="200" src="${imgUrl}" /></td>
				
			<td>${vid.videoId }</td>
			<td>${vid.title }</td>
			<td>${vid.description }</td>
			<td>${vid.category.categoryId }</td>
			<td>${vid.views }</td>
			<td><c:if test="${vid.active == 1}">
			<span>Hoạt động</span> 
			</c:if> 
			<c:if test="${vid.active != 1}">
				<span>Khóa</span>
			</c:if> 
			</td>
			<td><a
				href="<c:url value='/admin/video/edit?id=${vid.videoId }'/>">Sửa</a>
				| <a
				href="<c:url value='/admin/video/delete?id=${vid.videoId }'/>">Xóa</a></td>
		</tr>
	</c:forEach>
</table>