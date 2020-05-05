<%@ page import="com.zhangmengcong.www.po.Goods" %>
<%@ page import="com.zhangmengcong.www.po.PageBean" %>
<%@ page import="com.zhangmengcong.www.po.DividePage" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/4/29
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="./bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="./bootstrap/css/bootstrap-theme.css">
<link rel="stylesheet" href="./beautiful.css">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>推送</title>
</head>




<body >

<a href="/login.jsp">返回登录页面</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/DividePageController">返回主页面</a>
<h1 align="center">欢迎<font color="#8a2be2" >   ${sessionScope.username}</font> </h1>
<h1 align="center">亲爱的<font color="#1e90ff" >   ${sessionScope.sendLevel}</font> </h1>
<h1 align="center"><a href="/DividePageController">点击进入主页</a></h1>
<h4 align="center"><font color="#ff1493">今日推送</font></h4>

<%
    List<Goods> list = (List<Goods>)request.getAttribute("goodsList");
    for(Goods goods :list){
%>
<div>


    <div style="float: left;margin-left: 70px;margin-bottom: 50px;margin-top: 30px;" class="box">
        <img src="./img/1.jpg">

        <p class="review"><%=goods.getImformation()%> </p>
        <div class="appraise">来自用户<font color="#ff1493"><%=goods.getSeller()%></font>的二手商品
            信誉分:<font color="red"><%=goods.getSellerReputation()%></font></div>
        <div class="info">
            <h4><font color="green"><%=goods.getGoodsName()%></font></h4>
            <span><%=goods.getPrice()%>元</span>
            <a align="center" href="/ChangePageController?method=setAmount&id=<%=goods.getId()%>">购买</a>
            <a>&nbsp&nbsp&nbsp</a>
            <a href="/AddGoodsToShoppingCarController?&id=<%=goods.getId()%>">加入购物车</a>
        </div>
        <div style="margin-top:50px ">
            <p> </p>
        </div>

    </div>

</div>
<%
    }
%>











<%--</table>--%>



<c:if test="${not empty requestScope.message}">
    <Script Language="JavaScript">
        alert("${requestScope.message}");
    </Script>

</c:if>


</body>
</html>