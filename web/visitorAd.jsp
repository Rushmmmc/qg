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
    <script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

    <script>
        function fun1(event,id) {
            event.preventDefault();
            var log_id = /^\d{1,8}$/;
            var flag_i = log_id.test(id);
            if (!flag_i) {
                alert("请注意id格式┭┮﹏┭┮");
                return;
            }
            if (flag_i) {
                $.ajax({
                    url: "/AddGoodsToShoppingCarController",
                    type: "POST",
                    dataType: 'html',
                    data: "id=" + id,
                    success: function (result) {
                        alert(result);
                        location.href = "/SeleteGoodsByInterestController";
                    },
                    error: function (msg) {
                        alert("出错啦")
                    }
                });
            }
        }
        function fun2(event, id) {
            event.preventDefault();
            var log_id = /^\d{1,8}$/;
            var flag_i = log_id.test(id);
            if (!flag_i) {
                alert("请注意id格式┭┮﹏┭┮");
                return;
            }
            $.ajax({
                url: "/ChangePageController",
                type: "POST",
                dataType: 'html',
                data: "id=" + id + "&method=setAmount",
                success: function (result) {
                    location.href = "/setAmount.jsp";
                },
                error: function (msg) {
                    alert("出错啦")
                }
            });
        }
    </script>











</head>



<body>

<a href="/login.jsp">登录</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/DividePageController">返回主页面</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/Quit">注销</a>
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
        <c:if test='<%=!goods.getPhotoPath().contains("暂无")%>'>
            <img src="/photo/<%=goods.getPhotoPath()%>" height="400" width="200">
        </c:if>
        <c:if test='<%=goods.getPhotoPath().contains("暂无")%>'>
            <img src="./img/1.jpg" height="400" width="200">
        </c:if>
        <p class="review"><%=goods.getImformation()%> </p>
        <c:if test='<%=!goods.getRecommend().contains("暂无")%>'>
            <h4 style="margin-left: 24px"><font color="#9acd32">用户推荐理由:<%=goods.getRecommend()%></font></h4>
        </c:if>
        <div class="appraise">来自用户<font color="#ff1493"><%=goods.getSeller()%></font>的二手商品
            信誉分:<font color="red"><%=goods.getSellerReputation()%></font></div>
        <div class="info">
            <h4><font color="green"><%=goods.getGoodsName()%></font></h4>
            <span><%=goods.getPrice()%>元</span>
            <a align="center" href="/login.jsp" >登录后购买</a>
            <a>&nbsp&nbsp&nbsp</a>
            <a href="/login.jsp">登录后加入购物车</a>
        </div>
        <div style="margin-top:50px ">
            <p> </p>
        </div>

    </div>

</div>
<%
    }
%>











</body>
</html>