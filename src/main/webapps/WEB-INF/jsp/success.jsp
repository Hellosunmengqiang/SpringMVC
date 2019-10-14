<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成功</title>
</head>
<body>
<h4>成功页面！</h4>

<!-- 从IndexController中获取数据 -->
       request.name : ${requestScope.name}   <br><br>
      
       user : ${requestScope.user}
        <br><br>
    上传的图片：<img style="width: 260px" src="resources/uploadfile/${filename }">
     <c:forEach var="i" items="${list}"><!--mv.addObject("list",list) 对于map.put("list",list) 写法同样可以 -->
         ${i} <br>
     <c:out value="${i }"></c:out><br>
     </c:forEach>
     

</body>
</html>