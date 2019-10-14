<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
   <h4>这里是index首页</h4>
   

   
     <form action="${pageContext.request.contextPath}/addUser" method="get"><!-- testConsumes,testJsp2Mvc,addUser -->
       <input type="text" value="aaa" name="username">
       <input type="text" value="10" name="age">
       <input type="submit" value="提交1">
     </form>
     
     <form action="${pageContext.request.contextPath}/testRequestParam/" method="post">
       <input type="text" value="bbb" name="username">
       <input type="text" value="123" name="age">
       <input type="submit" value="提交2">
     </form>
     
     <!-- ModelAttribute的使用 -->
     <form action="${pageContext.request.contextPath}/updateUser" method="get"><!-- updateUser -->
       <input type="hidden" value="ccc" name="username">
      修改age: <input type="text" value="" name="age">
       <input type="submit" value="提交3">
     </form>
     <br>
     
      <!-- 配置国际化资源文件需要新建i18n_en_US 和 i18n_zh_CN 文件，配置fmt -->
      <form action="${pageContext.request.contextPath}/updateUser" method="post"><!-- updateUser -->
      <fmt:message key="i18n.username"/>:<input type="text" name="username"/> <br><br>
      <fmt:message key="i18n.password"/>:<input type="text" name="password"/> <br><br>     
       <input type="submit" value="提交3">
     </form>    
     <br>
     
     <!-- REST风格的四种请求 -->
     <a href="${pageContext.request.contextPath}/testGet">testGet</a> <!-- get and post 两个自带风格案例 -->
        
     <form action="${pageContext.request.contextPath}/testDel" method="post"><!--put和delete借助自带风格（get/post）使用的案例testPut-->
      <input type="hidden" name="_method" value="delete">
      <fmt:message key="i18n.username"/>:<input type="text" name="username"/> <br><br>
      <fmt:message key="i18n.password"/>:<input type="text" name="password"/> <br><br>     
       <input type="submit" value="提交4">
     </form>    
     <br>
     
     <!-- 当使用@RequestBody和@ResponseBody注解时，RequestMappingHandlerAdapter就使用它们来进行读取或者写入相应格式的数据。 -->   
     <form action="${pageContext.request.contextPath }/testConver" method="post" enctype="multipart/form-data">
      文件：<input type="file" name="file"><br>
      说明：<input type="text" name="mark"><br>
      <input type="submit" value="提交"> 
    </form>
    <br>
    <!-- 文件上传下载 --> 
      <form action="${pageContext.request.contextPath }/upLoad" method="post" enctype="multipart/form-data">
      上传的文件：<input type="file" name="file"><br>
      文件的说明：<input type="text" name="mark"><br>
      <input type="submit" value="上传"> 
    </form>
     
      
</body>
</html>