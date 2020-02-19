<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method = "post" action = "navigationServlet">
		<table>
	<c:forEach items="${requestScope.allPets}" var="currentpet">
	<tr>
		<td><input type="radio" name="id" value="${currentpet.id}"></td>
		<td>${currentpet.type}</td>
		<td>${currentpet.name}</td>
		<td>${currentpet.owner}</td>
	</tr>
	</c:forEach>
	</table>
		<input type = "submit" value = "edit" name = "doThisToPet">
		<input type = "submit" value = "delete" name = "doThisToPet">
		<input type = "submit" value = "add" name = "doThisToPet">
	</form>
	
	
</body>
</html>