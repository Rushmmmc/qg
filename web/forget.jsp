<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/4/30
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="./bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="./bootstrap/css/bootstrap-theme.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>mc的救赎系统</title>
</head>
<body>
<a href="/login.jsp">返回登录页面</a>
<form   action="/ForgetPasswordController" method="post" align="center"  >

    邮箱地址 :<input type="text" name="address" pattern="^[0-9A-Za-z][\.-_0-9A-Za-z]*@[0-9A-Za-z]+(?:\.[0-9A-Za-z]+)+$" required/>

    </br>
    <input type="submit"  value="Submit" style="width:70px; height:30px;"/>


</form>


<c:if test="${not empty requestScope.message}">
    <Script Language="JavaScript">
        alert("${requestScope.message}");
    </Script>

</c:if>
</body>
</html>
