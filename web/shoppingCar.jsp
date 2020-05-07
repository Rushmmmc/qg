<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.zhangmengcong.www.po.Indent" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rush
  Date: 2020/5/1
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="./bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="./bootstrap/css/bootstrap-theme.css">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="./bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="./bootstrap/css/bootstrap-theme.css">
<script src="https://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<html>
<head>
    <title>用户订单中心</title>
    <script>

        $(function(){
            $(".check").click(function(){
                $(".text").show();
            })
        })

    </script>
</head>
<body>

<a href="/login.jsp">返回登录页面</a><a>&nbsp&nbsp&nbsp</a>
<a href="/DividePageController">返回主页面</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController?method=commit">申卖商品</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController?method=manageIndent&ifSeller=1">管理卖出订单</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageController?method=manageBuyerPersonalIndent">管理买入订单</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageToShoppingCarController">查看购物车</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/ChangePageToHelpUser">进行申诉</a>
<a>&nbsp&nbsp&nbsp</a>
<a href="/Quit">注销</a>




    <STRONG><p align="center">用户购物车</p></STRONG>
</div>
<div class="font">
    <table  border="0px" width="70%" align="center" cellspacing="0px" class="table">
        <tr>
            <th>Id</th>
            <th>商品名称</th>
            <th>购买者</th>
            <th>出售者</th>
            <th>单价</th>
            <th>数量</th>
            <th>使用积分</th>
            <th>实际付款</th>
            <th>订单状态</th>
            <th>管理</th>
        </tr>
        <!--通过循环 显示信息-->


        <%
            List<Indent> emps = (List<Indent>)request.getAttribute("emps");
            for(Indent indent : emps){
        %>
        <tr>
            <td><%=indent.getId()%></td>
            <td><%=indent.getGoodsName()%></td>
            <td><%=indent.getBuyer()%></td>
            <td><%=indent.getSeller()%></td>
            <td><%=indent.getPrice()%></td>
            <form method="post" action="/BuyGoodsFromShoppingCarController?method=buyGoodsFromShoppingCar&id=<%=indent.getId()%>&price=<%=indent.getPrice()%>">
            <td><input type="text" name="amount" required value="1"></td>
                <td><input type="text" name="integral" required value="0"></td>
            <td><%=indent.getTotalPrice()%></td>
            <td><%=indent.getStatus()%></td>
            <td><a href="/ChangeIndentController?method=deleteShoppingCarIndent&id=<%=indent.getId()%>">从购物车删除</a>
                <input type="submit" value="购买">
            </form>
            </td>
        <tr>
                <%
        }
    %>




    </table>

</div>




</body>
</html>
