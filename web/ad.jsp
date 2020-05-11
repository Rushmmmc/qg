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
<link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="/bootstrap/css/bootstrap-theme.css">
<link rel="stylesheet" href="/beautiful.css">

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
                        url: "/GoodsController/addGoodsToShoppingCar",
                        type: "POST",
                        dataType: 'html',
                        data: "id=" + id,
                        success: function (result) {
                            if(result === "您不可以把自己的商品加入购物车┭┮﹏┭┮"){
                                alert(result);
                                return;
                            }
                            alert(result);
                            location.href = "/GoodsController/seleteGoodsByInterest";
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
                        url: "/ChangePageController/buyerSetBuyAmount",
                        type: "POST",
                        dataType: 'html',
                        data: "id=" + id,
                        success: function (result) {
                            if(result === "false"){
                                location.href = "/setAmount.jsp";
                            }else {
                                alert(result);
                            }
                        },
                        error: function (msg) {
                            alert("出错啦")
                        }
                    });
            }

            function goToCommitGoods(event) {
                event.preventDefault();
                $.ajax({
                    url: "/ChangePageController/sellerCommitGoods",
                    type: "POST",
                    dataType: 'html',
                    success: function (result) {
                        if(result === ""){
                            location.href = "/commit.jsp";
                        }else {
                            alert(result);
                        }
                    },
                    error: function (msg) {
                        alert("出错啦")
                    }
                });
            }

            function changePageToManagePersonalGoods(event) {
                event.preventDefault();
                $.ajax({
                    url: "/GoodsController/checkUserIfExistGoods",
                    type: "POST",
                    dataType: 'html',
                    success: function (result) {
                        if(result === "您暂无商品，快去申请卖出您的宝贝吧( •̀ ω •́ )y"){
                            alert(result);
                        }else {
                            location.href = "/ChangePageController/changePageToSellerManageGoodsPage";
                        }
                    },
                    error: function (msg) {
                        alert("出错啦")
                    }
                });
            }


            function changePageToManagePersonalGoods(event) {
                event.preventDefault();
                $.ajax({
                    url: "/GoodsController/checkUserIfExistGoods",
                    type: "POST",
                    dataType: 'html',
                    success: function (result) {
                        if(result === "您暂无商品，快去申请卖出您的宝贝吧( •̀ ω •́ )y"){
                            alert(result);
                        }else {
                            location.href = "/ChangePageController/changePageToSellerManageGoodsPage";
                        }
                    },
                    error: function (msg) {
                        alert("出错啦")
                    }
                });
            }


        </script>











</head>




<body >

<a href="/login.jsp">返回登录页面</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/DividePageController">返回主页面</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController/goCheckBuyIndent">管理买入订单</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController/changePageToShoppingCar">查看购物车</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController/goCheckSalesIndent">管理卖出订单</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="#" onclick="changePageToManagePersonalGoods(event)">管理商品</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="#" onclick="goToCommitGoods(event)">申卖商品</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController/changePageToHelpUser">进行申诉</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController/quit">注销</a>
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

        <br><br>
        <a>&nbsp&nbsp&nbsp</a><a>&nbsp&nbsp&nbsp</a><a>&nbsp&nbsp&nbsp</a><font color="#00bfff" style="margin-top: 100px">商品信息:<%=goods.getImformation()%></font>
        <c:if test='<%=!goods.getRecommend().contains("暂无")%>'>

            <br><br><h4 style="margin-left: 24px"><font color="#9acd32">用户推荐理由:<%=goods.getRecommend()%></font></h4>
        </c:if>
        <c:if test='<%=goods.getRecommend().contains("暂无")%>'>

            <br><br><br>
        </c:if>

        <div class="appraise"><font color="blue">来自用户</font><font color="#ff1493"><%=goods.getSeller()%></font><font color="blue">的二手商品</font>
            <a>&nbsp&nbsp&nbsp</a><font color="#9370db">商品类型:<%=goods.getType()%></font>
            <br><font color="red">信誉分:<%=goods.getSellerReputation()%></font>
            <font color="red">今日销量:<%=goods.getBoughtAmount()%></font></div>
        <div class="info">
            <h4><%=goods.getGoodsName()%></h4>
            <span><%=goods.getPrice()%>元</span>
            <a>&nbsp&nbsp&nbsp</a>
            <span>存货:<%=goods.getAmount()%></span>
        </div>
        <c:if test='<%=goods.getAmount()!=0%>'>
            <a align="center" href="#" style="margin-left: 30px" onclick="fun2(event,<%=goods.getId()%>)">购买</a>
            <a>&nbsp&nbsp&nbsp</a>
            <a href="#" onclick="fun1(event,<%=goods.getId()%>)">加入购物车</a>
        </c:if>
        <c:if test='<%=goods.getAmount()==0%>'>
            <font style="margin-left: 27px" color="red">暂无存货</font>
            <a href="#" onclick="fun1(event,<%=goods.getId()%>)">先加入购物车</a>
        </c:if>
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