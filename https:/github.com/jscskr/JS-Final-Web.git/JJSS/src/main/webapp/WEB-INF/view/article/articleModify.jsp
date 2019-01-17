<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/modify/${modifingArticle.rv_board_index}" method="post">
	content<input type="text" name="rv_board_content">
	district<input type="text" name="district">
	<input type="submit" value="modify">
</form>
</body>
</html>