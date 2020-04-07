<%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>Home</title>
	<style>
		table, th, td {border: 1px solid black; border-collapse: collapse;}
	</style> 
</head>

<body>
<!-- <P>  The time on the server is ${serverTime}. </P>  -->
<P> Search Mail (v2.5)</P>

<form name="form1" method="post" action="file.do">
	<input name="keyword" value="${map.keyword}" style="width:900px">
	<input type="submit" value="검색" style="width:80px">
	<br/><br/>
	<table boarder="1" cellpadding="0" cellspacing="0" width="1000">
			<tr>
				<th bgcolor="lightblue" width="500"></th>
				<th bgcolor="skyblue" width="500">검색어</th>
				<th bgcolor="skyblue" width="1000">검색결과</th>
			</tr>
			<tr>
				<td align="center">구문 검색</td>
				<td align="center">"발주서 보내드립니다"</td>
				<td align="center">'발주서 보내드립니다' 구문이 포함된 파일들</td>
			</tr>
			<tr>
				<td align="center">여러 단어 검색</td>
				<td align="center">발주서 보내드립니다 <br/> (=발주서 OR 보내드립니다)</td>
				<td align="center">'발주서'와 '보내드립니다' 중 하나 이상 포함된 파일들</td>
			</tr>
			<tr>
				<td align="center">여러 단어 중첩 검색</td>
				<td align="center">발주서 AND 보내드립니다</td>
				<td align="center">'발주서'와 '보내드립니다' 모두 포함된 파일들</td>
			</tr>
			<tr>
				<td align="center">특정글자 포함된 단어 검색</td>
				<td align="center">발주*</td>
				<td align="center">'발주'로 시작하는 단어들이 포함된 파일들</td>
			</tr>
		</table>
	
	
	
	
	
</form>


<!-- form action="upload.do" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
	<input multiple="multiple" type="file" name="file"/>
	<input name="keyword" value="${map.keyword}">
	<input type="submit" value="파일 내 검색">
</form>
-->

</body>
</html>
