<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"  %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>result</title>
	<style>
		table, th, td {border: 1px solid black; border-collapse: collapse;}
	</style>
</head>

<body>

<form method="post" action="fileopen.do">
	<%= request.getAttribute("ORresult") %>
	<table style="width:100%">
			<tr>
				<th bgcolor="ivory" width="100">no</th>
				<th bgcolor="ivory" width="1000">파일</th>
				<th bgcolor="lightgreen" width="800">FW메일 보낸일시</th>
				<th bgcolor="ivory" width="400"></th>
			</tr>
			
			<c:forEach var="fileL" items="${fileList }"  varStatus="status">
				<tr>
					<td align="center">${status.count }</td>
					<td align="center">${fileL }</td>
					<td align="center">${list2[status.index] }</td>
					<td align="center">
						<a>파일열기</a>
						<!--				
						<a href="/mail/fileopen.do?cnt=${status.index }">파일열기</a> -->
					</td>
				</tr>
			</c:forEach>	
			
		</table>
	
</form>
</body>
</html>
