<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在此处插入标题</title>
</head>
<body>

 <form action="${pageContext.request.contextPath }/testConver" method="post" enctype="multipart/form-data">
      文件：<input type="file" name="file"><br>
      说明：<input type="text" name="mark"><br>
      <input type="submit" value="提交"> 
    </form>

</body>
</html>