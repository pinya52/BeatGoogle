<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="pinya.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>
</head>
<body bgcolor="#3084d3">
	<center>
		<form action='${requestUri}' method='get'>
			<img id="cover-img"
				src="https://i.makeagif.com/media/1-24-2018/f9sqTh.gif" width="400"
				height="200">
			<div>
				<input id="keywordText" type='text' name='keyword'
					placeholder='enter your keyword'
					style="width: 300px; height: 15px;" /> <input type='number'
					name='weight' placeholder='weight'
					style="width: 100px; height: 13px"> <input type="submit"
					name='add' value='add'> <br> <input type='submit'
					value='START' onclick="onWebLoaded()" /> <br>
			</div>
		</form>
		<script>
			function onWebLoaded() {
				document.getElementById("cover-img").src = '/pinya/img/Tata.gif';
			}
		</script>
	</center>
</body>

</html>