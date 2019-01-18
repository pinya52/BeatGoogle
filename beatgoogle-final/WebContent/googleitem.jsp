<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>
</head>
<body bgcolor="#277ed7">
	<center>
		<img
			src="https://thumbs.gfycat.com/ThisAbandonedIsabellineshrike-max-1mb.gif"
			width="400" height="200"> <font color="FDFEFE" size="20"><b>
				<div style="text-align: center;">RESULT</div>
		</b></big></font> <br>
		<%
			String[][] orderList = (String[][]) request.getAttribute("query");
			for (int i = 0; i < orderList.length; i++) {
		%>
		<div style="text-align: center;"#FDFEFE"" "><%=orderList[i][0]%></div>
		<br>
		<div style="text-align: center;">
			<h style="font-size:15px; ;">
			<a href='<%=orderList[i][1]%>'><%=orderList[i][1]%></a></h>
		</div>
		<br>
		<div style="text-align: center;">
			<h style="font-size:15px; ;"><%="Score: " + orderList[i][2]%></h>
		</div>
		<br> <br>
		<%
			}
		%>
	</center>
</body>
</html>