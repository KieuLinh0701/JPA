<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath}/admin/user/search" method="get">
    <input type="text" name="keyword" value="${keyword}" placeholder="Nhập từ khóa tìm kiếm">
    <button type="submit">Tìm kiếm</button>
</form>

<a href="${pageContext.request.contextPath}/admin/user/add">Add user</a>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Image</th>
		<th>fullname</th>
		<th>userId</th>
		<th>username</th>
		<th>password</th>
		<th>email</th>
		<th>phone</th>
		<th>roleId</th>
		<th>creatDate</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${listuser}" var="user" varStatus="STT">
		<tr>
			<td>${STT.index+1 }</td>
			<td>
				<c:if test="${user.images.substring(0,5) !='https'}">
					<c:url value="/image?fname=${user.images}" var="imgUrl"></c:url>
				</c:if> 
				<c:if test="${user.images.substring(0,5) =='https'}">
					<c:url value="${user.images}" var="imgUrl"></c:url>
				</c:if> 
				<img height="150" width="200" src="${imgUrl}" /></td>
				
			<td>${user.fullname }</td>
			<td>${user.userId }</td>
			<td>${user.username }</td>
			<td>${user.password }</td>
			<td>${user.email }</td>
			<td>${user.phone }</td>
			<td>${user.role.roleId }</td>
			<td>${user.createDate }</td>
			<td><a
				href="<c:url value='/admin/user/edit?id=${user.userId }'/>">Sửa</a>
				| <a
				href="<c:url value='/admin/user/delete?id=${user.userId }'/>">Xóa</a></td>
		</tr>
	</c:forEach>
</table>