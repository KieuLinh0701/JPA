<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<form action="${pageContext.request.contextPath}/admin/role/search" method="get">
    <input type="text" name="keyword" value="${keyword}" placeholder="Nhập từ khóa tìm kiếm">
    <button type="submit">Tìm kiếm</button>
</form>

<a href="${pageContext.request.contextPath}/admin/role/add">Add role</a>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>roleId</th>
		<th>roleName</th>
		<th>Action</th>
	</tr>
	<c:forEach items="${listrole}" var="role" varStatus="STT">
		<tr>
			<td>${STT.index+1 }</td>
			<td>${role.roleId }</td>
			<td>${role.roleName }</td>
			<td><a
				href="<c:url value='/admin/role/edit?id=${role.roleId }'/>">Sửa</a>
				| <a
				href="<c:url value='/admin/role/delete?id=${role.roleId }'/>">Xóa</a></td>
		</tr>
	</c:forEach>
</table>