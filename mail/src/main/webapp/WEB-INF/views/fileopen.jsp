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

<form>
		<table style="width:100%">
			<tr>
				<th bgcolor="lightpink" width="100"><%= request.getAttribute("result") %></th>
				<th bgcolor="ivory" width="1000"><%= request.getAttribute("file") %></th>
			</tr>
			<tr><td colspan="2"><%= request.getAttribute("show") %></td></tr>
			
		</table>
	



	

	

</form>
</body>
</html>
